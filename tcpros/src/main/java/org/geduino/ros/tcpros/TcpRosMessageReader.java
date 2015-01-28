package org.geduino.ros.tcpros;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageReader;

public class TcpRosMessageReader<M extends Message> implements MessageReader<M> {

	private static final Logger LOGGER = Logger
			.getLogger(TcpRosMessageReader.class);

	private final Class<M> messageClass;
	private final InputStreamDataReader inputStreamDataReader;

	private int numReceived;

	public TcpRosMessageReader(Class<M> messageClass,
			InputStreamDataReader inputStreamDataReader) {

		this.messageClass = messageClass;
		this.inputStreamDataReader = inputStreamDataReader;

		// Set num received to zero
		numReceived = 0;

	}

	@Override
	public M read() throws IOException, RosMessageSerializationException {

		try {

			// Create new message instance
			M message = newMessageInstance(messageClass);

			// Get message length
			long messageLength = inputStreamDataReader.readUInt32();

			// Reset byte limit
			inputStreamDataReader.resetByteLimit((int) messageLength);

			// Log
			LOGGER.trace("reading: " + messageLength + " bytes message...");

			// Deserialize message
			message.deserialize(inputStreamDataReader);

			if (inputStreamDataReader.getByteLimit() != 0) {

				// Throw exception
				throw new RosMessageSerializationException(
						"expected message length was: " + messageLength
								+ " but: "
								+ inputStreamDataReader.getByteLimit()
								+ " was not read");

			}

			// Increase num received
			numReceived++;

			return message;

		} catch (InstantiationException ex) {

			// Throw exception
			throw new RosMessageSerializationException(
					"could not read message", ex);

		} catch (IllegalAccessException ex) {

			// Throw exception
			throw new RosMessageSerializationException(
					"could not read message", ex);

		} finally {

			// Reset byte limit
			inputStreamDataReader.resetByteLimit();

		}

	}

	public int getNumReceived() {
		return numReceived;
	}

	private M newMessageInstance(Class<M> messageClass)
			throws InstantiationException, IllegalAccessException {

		// Create new message instance
		M message = messageClass.newInstance();

		return message;

	}

}
