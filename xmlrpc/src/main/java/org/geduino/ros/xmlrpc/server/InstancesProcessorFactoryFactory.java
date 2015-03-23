package org.geduino.ros.xmlrpc.server;

import java.util.HashMap;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.server.RequestProcessorFactoryFactory;

/**
 * A {@link RequestProcessorFactoryFactory} implementation that use the unique
 * processor object instance for a processor class.
 * 
 * @author alessandro
 *
 */
class InstancesProcessorFactoryFactory implements
		RequestProcessorFactoryFactory {

	private final Map<Class<?>, Object> processors = new HashMap<Class<?>, Object>();

	/**
	 * Put new processor instance
	 * 
	 * @param processorClass
	 *            the processor class
	 * @param processor
	 *            the processor instance
	 */
	void put(Class<?> processorClass, Object processor) {
		processors.put(processorClass, processor);
	}

	/**
	 * Get the request processor for given processor class
	 * 
	 * @param processorClass
	 *            the processor class
	 * @return the request processor instance
	 * @throws XmlRpcException
	 *             if no instance was set for given processor class
	 */
	protected Object getRequestProcessor(Class<?> processorClass)
			throws XmlRpcException {

		// Get processoe
		Object processor = processors.get(processorClass);

		if (processor == null) {
			throw new XmlRpcException("could not find processor for class: "
					+ processorClass);
		}

		return processor;

	}

	@Override
	public RequestProcessorFactory getRequestProcessorFactory(
			@SuppressWarnings("rawtypes") Class processorClass)
			throws XmlRpcException {

		// Get processor
		final Object processor = getRequestProcessor(processorClass);

		return new RequestProcessorFactory() {

			@Override
			public Object getRequestProcessor(XmlRpcRequest processorRequest)
					throws XmlRpcException {

				return processor;

			}

		};

	}

}
