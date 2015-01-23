package org.geduino.ros.tcpros;

import java.io.IOException;
import java.io.InputStream;

class ByteBufferInputStream extends InputStream {

	private volatile int index;
	private volatile byte[] bytes;

	@Override
	public int read() throws IOException {

		synchronized (this) {

			// Get result˙˙
			int result = (index < bytes.length) ? (bytes[index++] & 0xFF) : -1;

			return result;

		}

	}

	void reset(byte[] bytes) {

		synchronized (this) {

			// Set buffer content
			this.bytes = bytes;

			// Reset index
			index = 0;

		}

	}

}
