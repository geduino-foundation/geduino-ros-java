package org.geduino.ros.core.naming.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.geduino.ros.core.naming.exception.RosNamingRuntimeException;

public abstract class Name implements Named {

	private static final long serialVersionUID = 1L;

	private static final Pattern BASE_NAME_PATTERN = Pattern
			.compile("^[a-zA-Z][a-zA-Z0-9_]*$");

	private static final Pattern RELATIVE_NAME_PATTERN = Pattern
			.compile("^[a-zA-Z][a-zA-Z0-9_]*\\/[a-zA-Z][a-zA-Z0-9_]*$");

	private static final Pattern PRIVATE_NAME_PATTERN = Pattern
			.compile("^~[a-zA-Z][a-zA-Z0-9_]*(\\/[a-zA-Z][a-zA-Z0-9_]*)*$");

	private static final Pattern GLOBAL_NAME_PATTERN = Pattern
			.compile("^\\/[a-zA-Z][a-zA-Z0-9_]*(\\/[a-zA-Z][a-zA-Z0-9_]*)*$");

	private static final Pattern MESSAGE_NAME_PATTERN = Pattern
			.compile("^[a-zA-Z][a-zA-Z0-9_]*\\/[a-zA-Z_][a-zA-Z0-9_]*$");

	public static boolean isBase(String string) {

		// Get matcher
		Matcher matcher = BASE_NAME_PATTERN.matcher(string);

		// Get is base
		boolean isBase = matcher.matches();

		return isBase;

	}

	public static boolean isRelative(String string) {

		// Get matcher
		Matcher matcher = RELATIVE_NAME_PATTERN.matcher(string);

		// Get is relative
		boolean isRelative = matcher.matches();

		return isRelative;

	}

	public static boolean isPrivate(String string) {

		// Get matcher
		Matcher matcher = PRIVATE_NAME_PATTERN.matcher(string);

		// Get is private
		boolean isPrivate = matcher.matches();

		return isPrivate;

	}

	public static boolean isGlobal(String string) {

		// Get matcher
		Matcher matcher = GLOBAL_NAME_PATTERN.matcher(string);

		// Get is global
		boolean isGlobal = matcher.matches();

		return isGlobal;

	}

	public static boolean isRoot(String string) {

		// Get is root
		boolean isRoot = "/".equals(string);

		return isRoot;

	}

	public static boolean isMessage(String string) {

		// Get matcher
		Matcher matcher = MESSAGE_NAME_PATTERN.matcher(string);

		// Get is message
		boolean isMessage = matcher.matches();

		return isMessage;

	}

	public static Name parseName(String string) {

		if (isBase(string)) {

			// Create name
			Name name = new BaseName(string);

			return name;

		} else if (isRelative(string)) {

			// Create name
			Name name = new RelativeName(string);

			return name;

		} else if (isPrivate(string)) {

			// Create name
			Name name = new PrivateName(string);

			return name;

		} else if (isGlobal(string)) {

			// Create name
			Name name = new GlobalName(string);

			return name;

		} else if (isRoot(string)) {

			// Create name
			Name name = new RootName();

			return name;

		} else {

			// Throw
			throw new RosNamingRuntimeException("invalid name: " + string);

		}

	}

	public static MessageName parseMessageName(BaseName packageName,
			String string) {

		if (!string.contains("/")) {

			// Use package name
			string = packageName.name.concat("/").concat(string);

		}

		// Create name
		MessageName messageName = new MessageName(string);

		return messageName;

	}

	public static MessageName parseMessageName(String string) {

		if (!string.contains("/")) {

			// Throw
			throw new RosNamingRuntimeException("invalid message name: "
					+ string);

		}

		// Create name
		MessageName messageName = new MessageName(string);

		return messageName;

	}

	protected final String name;

	protected Name(String name) {

		// Validate name
		validate(name);

		this.name = name;
	}

	protected abstract void validate(String string);

	public Name getName() {
		return this;
	}

	public Name getLastChild() {

		int index = name.lastIndexOf("/");

		if (index != -1) {

			// Get last child
			Name lastChild = parseName(name.substring(index));

			return lastChild;

		} else {
			return this;
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Name other = (Name) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

}
