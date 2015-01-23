package org.geduino.ros.tcpros;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.UUID;

import org.geduino.ros.core.transport.model.Connection;
import org.geduino.ros.core.transport.model.PrimitiveTypeReader;
import org.geduino.ros.core.transport.model.PrimitiveTypeWriter;

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

	protected PrimitiveTypeReader getPrimitiveTypeReader() throws IOException {

		// Create primitive type reader
		PrimitiveTypeReader primitiveTypeReader = new InputStreamPrimitiveTypeReader(
				socket.getInputStream());

		return primitiveTypeReader;

	}

	protected PrimitiveTypeWriter getPrimitiveTypeWriter() throws IOException {

		// Create primitive type writer
		PrimitiveTypeWriter primitiveTypeWriter = new OutputStreamPrimitiveTypeWriter(
				socket.getOutputStream());

		return primitiveTypeWriter;

	}

}
