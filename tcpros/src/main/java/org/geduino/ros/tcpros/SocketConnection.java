package org.geduino.ros.tcpros;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.UUID;

import org.geduino.ros.core.transport.model.Connection;

public abstract class SocketConnection implements Connection {

	private final Socket socket;

	private final String id;

	private InputStreamDataReader inputStreamDataReader;
	private OutputStreamDataWriter outputStreamDataWriter;

	public SocketConnection(Socket socket) {

		this.socket = socket;

		// Create connection id
		this.id = socket.getInetAddress().getHostName() + "-"
				+ UUID.randomUUID();

	}

	@Override
	public String getConnectionId() {
		return id;
	}

	@Override
	public boolean isClosed() {
		return socket.isClosed();
	}

	@Override
	public void close() throws IOException {
		socket.close();
	}

	public int getByteSent() {
		return (outputStreamDataWriter == null) ? 0 : outputStreamDataWriter.getByteSent();
	}

	public int getByteReceived() {
		return (inputStreamDataReader == null) ? 0 : inputStreamDataReader.getByteReceived();
	}

	public boolean getTcpNoDelay() throws SocketException {
		return socket.getTcpNoDelay();
	}

	public void setTcpNoDelay(boolean tcpNoDelay) throws SocketException {
		socket.setTcpNoDelay(tcpNoDelay);
	}

	public boolean isConnected() {

		// Get is connected
		boolean connected = !socket.isConnected();

		return connected;

	}

	protected InputStreamDataReader getDataReader() throws IOException {

		if (inputStreamDataReader == null) {

			// Create data reader
			inputStreamDataReader = new InputStreamDataReader(
					socket.getInputStream());

		}

		return inputStreamDataReader;

	}

	protected OutputStreamDataWriter getDataWriter() throws IOException {

		if (outputStreamDataWriter == null) {

			// Create data writer
			outputStreamDataWriter = new OutputStreamDataWriter(
					socket.getOutputStream());

		}

		return outputStreamDataWriter;

	}

}
