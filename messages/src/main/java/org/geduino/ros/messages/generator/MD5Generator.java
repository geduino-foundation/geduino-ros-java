package org.geduino.ros.messages.generator;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.geduino.ros.core.naming.model.MessageName;
import org.geduino.ros.messages.description.model.FieldDescription;
import org.geduino.ros.messages.description.model.MessageDescription;
import org.geduino.ros.messages.description.model.MessageFieldType;
import org.geduino.ros.messages.description.model.PrimitiveFieldType;
import org.geduino.ros.messages.generator.exception.RosMessageGeneratorException;

class MD5Generator {

	static String generate(MessageDescription messageDescription,
			Map<MessageName, MessageDescription> messageDescriptions)
			throws RosMessageGeneratorException {

		// Get md5 text
		String md5Text = getMD5Text(messageDescription, messageDescriptions);

		try {

			// Create message digest
			MessageDigest md5MessageDigest = MessageDigest.getInstance("MD5");

			// Compute digest
			byte[] digest = md5MessageDigest.digest(md5Text.getBytes());

			// Get md5
			String md5 = String.format("%032X", new BigInteger(1, digest));

			return md5.toLowerCase();

		} catch (NoSuchAlgorithmException ex) {

			// Throw exception
			throw new RosMessageGeneratorException("could not compute md5",
					ex);

		}

	}

	private static String getMD5Text(MessageDescription messageDescription,
			Map<MessageName, MessageDescription> messageDescriptions)
			throws RosMessageGeneratorException {

		StringBuffer md5TextStringBuffer = new StringBuffer();

		// Get constant field description
		List<FieldDescription> constantFieldDescriptions = messageDescription
				.getConstantFieldDescriptions();

		for (Iterator<FieldDescription> iterator = constantFieldDescriptions
				.iterator(); iterator.hasNext();) {

			// Get next field
			FieldDescription fieldDescription = iterator.next();

			// Get md5 text
			String md5Text = MD5Generator.getMD5Text(fieldDescription,
					messageDescriptions);

			// Add md5 text
			md5TextStringBuffer.append(md5Text).append(
					(iterator.hasNext()) ? "\n" : "");

		}

		// Get no constant field description
		List<FieldDescription> noConstantFieldDescriptions = messageDescription
				.getNoConstantFieldDescriptions();

		// Append separator
		md5TextStringBuffer
				.append((constantFieldDescriptions.size() > 0 && noConstantFieldDescriptions
						.size() > 0) ? "\n" : "");

		for (Iterator<FieldDescription> iterator = noConstantFieldDescriptions
				.iterator(); iterator.hasNext();) {

			// Get next field
			FieldDescription fieldDescription = iterator.next();

			// Get md5 text
			String md5Text = MD5Generator.getMD5Text(fieldDescription,
					messageDescriptions);

			// Add md5 text
			md5TextStringBuffer.append(md5Text).append(
					(iterator.hasNext()) ? "\n" : "");

		}

		return md5TextStringBuffer.toString();

	}

	private static String getMD5Text(FieldDescription fieldDescription,
			Map<MessageName, MessageDescription> messageDescriptions)
			throws RosMessageGeneratorException {

		if (fieldDescription.isConstant()) {

			// Compute md5text
			String md5Text = fieldDescription.toString();

			return md5Text;

		} else {

			if (fieldDescription.getType() instanceof PrimitiveFieldType) {

				// Compute md5text
				String md5Text = fieldDescription
						.getType()
						.toString()
						.concat((fieldDescription.isArray()) ? "[" : "")
						.concat((fieldDescription.getSize() != -1) ? Integer
								.toString(fieldDescription.getSize()) : "")
						.concat((fieldDescription.isArray()) ? "]" : "")
						.concat(" ").concat(fieldDescription.getName());

				return md5Text;

			} else if (fieldDescription.getType() instanceof MessageFieldType) {

				// Get message field type
				MessageFieldType messageFieldType = (MessageFieldType) fieldDescription
						.getType();

				// Get message name
				MessageName messageName = messageFieldType.getMessageName();

				// Get message descritption
				MessageDescription messageDescription = messageDescriptions
						.get(messageName);

				if (messageDescription != null) {

					// Compute md5
					String md5 = generate(messageDescription,
							messageDescriptions);

					// Compute md5text
					String md5Text = md5.concat(" ").concat(
							fieldDescription.getName());

					return md5Text;

				} else {

					// Throw exception
					throw new RosMessageGeneratorException(
							"missing dependency: " + messageName);

				}

			} else {

				// Throw exception
				throw new RosMessageGeneratorException("unknow field type: "
						+ fieldDescription.getType());

			}

		}

	}

}
