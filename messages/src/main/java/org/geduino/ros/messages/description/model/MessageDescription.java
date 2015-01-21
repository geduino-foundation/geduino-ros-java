package org.geduino.ros.messages.description.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.geduino.ros.core.messages.model.FieldType;
import org.geduino.ros.core.messages.model.MessageFieldType;
import org.geduino.ros.core.naming.model.MessageName;
import org.geduino.ros.core.util.CollectionFilter;

public class MessageDescription implements Serializable {

	private static final long serialVersionUID = 1L;

	private final MessageName name;
	private final List<FieldDescription> fieldDescriptions;

	public MessageDescription(MessageName name,
			List<FieldDescription> fieldDescriptions) {

		this.name = name;
		this.fieldDescriptions = fieldDescriptions;

	}

	public MessageName getName() {
		return name;
	}

	public List<FieldDescription> getFieldDescriptions() {
		return fieldDescriptions;
	}

	public List<FieldDescription> getConstantFieldDescriptions() {

		List<FieldDescription> constantFieldDescriptions = new ArrayList<FieldDescription>();

		// Apply filter
		new CollectionFilter<FieldDescription>(fieldDescriptions).filter(
				constantFieldDescriptions, new ConstantFieldFilter(true));

		return constantFieldDescriptions;

	}

	public List<FieldDescription> getNoConstantFieldDescriptions() {

		List<FieldDescription> constantFieldDescriptions = new ArrayList<FieldDescription>();

		// Apply filter
		new CollectionFilter<FieldDescription>(fieldDescriptions).filter(
				constantFieldDescriptions, new ConstantFieldFilter(false));

		return constantFieldDescriptions;

	}

	public Set<MessageName> getDependencies() {

		Set<MessageName> dependencies = new HashSet<MessageName>();

		for (Iterator<FieldDescription> iterator = fieldDescriptions.iterator(); iterator
				.hasNext();) {

			// Get next field description
			FieldDescription fieldDescription = iterator.next();

			// Get field type
			FieldType fieldType = fieldDescription.getType();

			if (fieldType instanceof MessageFieldType) {

				// Cast to message field type
				MessageFieldType messageFieldType = (MessageFieldType) fieldType;

				// Get message name
				MessageName messageName = messageFieldType.getMessageName();

				// Add to dependencies
				dependencies.add(messageName);

			}

		}

		return dependencies;

	}

	public Set<MessageName> getUnmeetDependencies(
			Set<MessageName> availableDependencies) {

		Set<MessageName> unmeetDependencies = new HashSet<MessageName>();

		for (Iterator<FieldDescription> iterator = fieldDescriptions.iterator(); iterator
				.hasNext();) {

			// Get next field definition
			FieldDescription fieldDefinition = iterator.next();

			// Get field type
			FieldType fieldType = fieldDefinition.getType();

			if (fieldType instanceof MessageFieldType) {

				// Cast to message field type
				MessageFieldType messageFieldType = (MessageFieldType) fieldType;

				// Get message name
				MessageName messageName = messageFieldType.getMessageName();

				if (!availableDependencies.contains(messageName)) {

					// Add to dependencies
					unmeetDependencies.add(messageName);

				}

			}

		}

		return unmeetDependencies;

	}

	@Override
	public String toString() {
		return name.toString();
	}

}
