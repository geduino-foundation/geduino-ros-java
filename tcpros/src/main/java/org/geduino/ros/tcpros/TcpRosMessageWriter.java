package org.geduino.ros.tcpros;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageWriter;

public class TcpRosMessageWriter<M extends Message> implements MessageWriter<M> {

	private static final Logger LOGGER = Logger
			.getLogger(TcpRosMessageWriter.class);

	private final OutputStreamDataWriter ouputStreamDataWriter;

	private int numSent;

	public TcpRosMessageWriter(OutputStreamDataWriter outputStreamDataWriter) {

		this.ouputStreamDataWriter = outputStreamDataWriter;

		// Set num sent to zero
		numSent = 0;

	}

	@Override
	public void write(M message) throws IOException,
			RosMessageSerializationException {

		// Get message length
		long messageLength = message.getLength();

		// Log
		LOGGER.trace("writing: " + messageLength + " bytes message...");

		// Write message length
		ouputStreamDataWriter.writeUInt32(messageLength);

		// Serialize message
		message.serialize(ouputStreamDataWriter);

		// Increase num sent
		numSent++;

	}

	public int getNumSent() {
		return numSent;
	}

}
