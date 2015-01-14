package org.geduino.ros.xmlrpc.server;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.metadata.XmlRpcSystemImpl;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcHandlerMapping;
import org.apache.xmlrpc.webserver.XmlRpcServlet;

public class ExtendedXmlRpcServlet extends XmlRpcServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected XmlRpcHandlerMapping newXmlRpcHandlerMapping()
			throws XmlRpcException {

		PropertyHandlerMapping propertyHandlerMapping = (PropertyHandlerMapping) super
				.newXmlRpcHandlerMapping();

		XmlRpcSystemImpl.addSystemHandler(propertyHandlerMapping);

		return propertyHandlerMapping;

	}

}
