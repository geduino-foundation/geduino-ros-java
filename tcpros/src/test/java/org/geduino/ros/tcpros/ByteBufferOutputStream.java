package org.geduino.ros.tcpros;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

class ByteBufferOutputStream extends OutputStream {

	private volatile int index;
	private volatile byte[] bytes;

	ByteBufferOutputStream(int maxBufferSize) {
		bytes = new byte[maxBufferSize];
	}

	@Override
	public void write(int byte_) throws IOException {

		synchronized (this) {

			if (index < bytes.length) {

				// Set byte
				bytes[index++] = (byte) byte_;

			} else {

				// Throw exception
				throw new IOException("buffer is full");

			}

		}

	}

	byte[] reset() {

		synchronized (this) {

			// Get bytes
			byte[] bytes = Arrays.copyOf(this.bytes, index);

			// Reset index
			index = 0;

			return bytes;

		}

	}

}
