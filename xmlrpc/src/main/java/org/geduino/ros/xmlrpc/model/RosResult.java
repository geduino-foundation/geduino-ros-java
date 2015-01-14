package org.geduino.ros.xmlrpc.model;


import java.io.Serializable;

import org.geduino.ros.core.api.exception.RosApiException;
import org.geduino.ros.xmlrpc.exception.InvalidObjectTypeException;

public class RosResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int SUCCESS = 1;
	public static final int FAILED = 0;
	public static final int ERROR = -1;

	private final int code;
	private final String statusMessage;
	private final T payload;

	public RosResult(int code, String statusMessage, T payload) {
		this.code = code;
		this.statusMessage = statusMessage;
		this.payload = payload;
	}

	@SuppressWarnings("unchecked")
	public RosResult(Object response) throws RosApiException {

		try {

			// Cast to array of object
			Object[] responses = (Object[]) response;

			if (responses.length == 3) {

				// Get code, status message and payload
				code = ((Integer) responses[0]).intValue();
				statusMessage = (String) responses[1];

				// Check code
				if (code != SUCCESS) {

					// Throw exception
					throw new RosApiException(statusMessage);

				}

				// Cast to T
				payload = (T) responses[2];

			} else {

				// Throw exception
				throw new RosApiException("unexpected response object length: "
						+ responses.length);

			}

		} catch (ClassCastException ex) {

			// Throw exception
			throw new InvalidObjectTypeException("unexpected object type", ex);

		}

	}

	public int getCode() {
		return code;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public T getPayload() {
		return payload;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((payload == null) ? 0 : payload.hashCode());
		result = prime * result
				+ ((statusMessage == null) ? 0 : statusMessage.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RosResult other = (RosResult) obj;
		if (code != other.code)
			return false;
		if (payload == null) {
			if (other.payload != null)
				return false;
		} else if (!payload.equals(other.payload))
			return false;
		if (statusMessage == null) {
			if (other.statusMessage != null)
				return false;
		} else if (!statusMessage.equals(other.statusMessage))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Return [code=" + code + ", statusMessage=" + statusMessage
				+ "]";
	}

}
