package org.geduino.ros.core.transport;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.URI;
import java.util.UUID;

import org.geduino.ros.core.api.model.Direction;
import org.geduino.ros.core.api.model.Transport;

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
		boolean connected = socket.isConnected();

		return connected;

	}

	public int read(byte[] bytes) throws IOException {

		// Get socket input stream
		InputStream inputStream = socket.getInputStream();

		// Read bytes
		int length = inputStream.read(bytes);

		// Increase byte received
		byteReceived += length;

		return length;

	}

	public int read(byte[] bytes, int length) throws IOException {

		// Get socket input stream
		InputStream inputStream = socket.getInputStream();

		// Read buytes
		inputStream.read(bytes, 0, length);

		// Increase byte received
		byteReceived += length;

		return length;

	}

	public void write(byte[] bytes) throws IOException {

		// Get socket output stream
		OutputStream outputStream = socket.getOutputStream();

		// Write bytes
		outputStream.write(bytes);

		// Increase byte sent
		byteSent += bytes.length;

	}

	@Override
	public String getConnectionId() {
		return id;
	}

	@Override
	public void close() throws IOException {
		socket.close();
	}

	public abstract URI getDestinationId();

	public abstract Direction getDirection();

	public abstract Transport getTransport();

}
