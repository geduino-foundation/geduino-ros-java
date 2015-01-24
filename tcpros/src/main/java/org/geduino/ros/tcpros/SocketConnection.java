package org.geduino.ros.tcpros;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.UUID;

import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.transport.model.Connection;

public abstract class SocketConnection implements Connection {

	private final Socket socket;

	private final String id;

	private int byteSent;
	private int numSent;
	private int byteReceived;
	private int numReceived;

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
		return byteSent;
	}

	public int getNumSent() {
		return numSent;
	}

	public int getByteReceived() {
		return byteReceived;
	}

	public int getNumReceived() {
		return numReceived;
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

	protected DataReader getDataReader() throws IOException {

		// Create data reader
		DataReader dataReader = new InputStreamDataReader(
				socket.getInputStream());

		return dataReader;

	}

	protected DataWriter getDataWriter() throws IOException {

		// Create data writer
		DataWriter dataWriter = new OutputStreamDataWriter(
				socket.getOutputStream());

		return dataWriter;

	}

}
