package org.geduino.ros.core.naming.model;

import junit.framework.TestCase;

import org.geduino.ros.core.naming.exception.RosNamingRuntimeException;

public class NameTest extends TestCase {

	public void testParse() {

		try {

			// Create names
			BaseName baseName = (BaseName) Name.parseName("base");
			RelativeName relativeName = (RelativeName) Name
					.parseName("relative/foo");
			GlobalName globalName = (GlobalName) Name.parseName("/global/foo");
			PrivateName privateName = (PrivateName) Name
					.parseName("~private/foo");
			RootName rootName = (RootName) Name.parseName("/");
			MessageName messageName1 = Name.parseMessageName(baseName, "message");
			MessageName messageName2 = Name.parseMessageName(baseName,
					"test/message");

			// Asserts
			assertEquals("base", baseName.toString());
			assertEquals("relative/foo", relativeName.toString());
			assertEquals("/global/foo", globalName.toString());
			assertEquals("~private/foo", privateName.toString());
			assertEquals("/", rootName.toString());
			assertEquals("base/message", messageName1.toString());
			assertEquals("test/message", messageName2.toString());

		} catch (RosNamingRuntimeException ex) {

			// Fail
			fail("unexpected error parsing name: " + ex.getMessage());

		} catch (ClassCastException ex) {

			// Fail
			fail("parsed name of unexpected class: " + ex.getMessage());

		}

	}

	public void testResolve() {

		// Create names
		UnresolvedName baseName = (UnresolvedName) Name.parseName("base");
		UnresolvedName relativeName = (UnresolvedName) Name
				.parseName("relative/foo");
		UnresolvedName privateName = (UnresolvedName) Name
				.parseName("~private/foo");
		ResolvedName globalName = (ResolvedName) Name.parseName("/global/foo");
		ResolvedName rootName = (ResolvedName) Name.parseName("/");

		// Assert
		assertEquals("/global/base", baseName.resolve(globalName).toString());
		assertEquals("/base", baseName.resolve(rootName).toString());
		assertEquals("/global/relative/foo", relativeName.resolve(globalName)
				.toString());
		assertEquals("/relative/foo", relativeName.resolve(rootName).toString());
		assertEquals("/global/foo/private/foo", privateName.resolve(globalName)
				.toString());
		assertEquals("/private/foo", privateName.resolve(rootName).toString());

	}

}
