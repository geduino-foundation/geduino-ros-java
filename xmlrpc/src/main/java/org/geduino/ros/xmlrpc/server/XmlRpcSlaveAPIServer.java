package org.geduino.ros.xmlrpc.server;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.GenericServlet;

import org.apache.log4j.Logger;
import org.apache.xmlrpc.server.RequestProcessorFactoryFactory;
import org.apache.xmlrpc.webserver.XmlRpcServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.geduino.ros.core.api.SlaveAPI;

public class XmlRpcSlaveAPIServer {

	private static final Logger LOGGER = Logger
			.getLogger(XmlRpcSlaveAPIServer.class);

	private final SelectChannelConnector selectChannelConnector;
	private final SlaveAPI slaveAPI;

	private Server server;

	public XmlRpcSlaveAPIServer(SlaveAPI slaveAPI) {

		this.slaveAPI = slaveAPI;

		// Create select channel connector
		selectChannelConnector = new SelectChannelConnector();
		selectChannelConnector.setPort(11311);
		selectChannelConnector.setThreadPool(new QueuedThreadPool(10));
		selectChannelConnector.setMaxIdleTime(60000);
		selectChannelConnector.setRequestHeaderSize(8192);

	}

	public SelectChannelConnector getSelectChannelConnector() {
		return selectChannelConnector;
	}

	public void start() throws Exception {

		// Log
		LOGGER.trace("creating context / for server...");

		// Create context / for server
		ServletContextHandler servletContextHandler = new ServletContextHandler(
				ServletContextHandler.NO_SESSIONS);
		servletContextHandler.setContextPath("/");

		// Log
		LOGGER.trace("mapping servlet...");

		// Create xml rpc init parameter
		Map<String, String> xmlRpcServletInitParameter = new HashMap<String, String>();

		// Create xml rpc servlet
		XmlRpcServlet xmlRpcServlet = new ExtendedXmlRpcServlet();

		// Create xml rpc slave api servlet
		XmlRpcSlaveAPIServlet xmlRpcSlaveAPIServlet = new XmlRpcSlaveAPIServlet(
				slaveAPI);

		// Create request processor factory factory for xml rpc slave api servlet
		RequestProcessorFactoryFactory.InstancesProcessorFactoryFactory requestProcessorFactoryFactory = new RequestProcessorFactoryFactory.InstancesProcessorFactoryFactory();
		requestProcessorFactoryFactory.put(XmlRpcSlaveAPIServlet.class,
				xmlRpcSlaveAPIServlet);

		// Set request processor factory factory on servlet
		xmlRpcServlet
				.setRequestProcessorFactoryFactory(requestProcessorFactoryFactory);

		// Map xml rpc servlet
		mapServlet(servletContextHandler, xmlRpcServlet, "XmlRpcServlet", "/",
				xmlRpcServletInitParameter);

		// Create jetty server
		server = new Server();
		server.setHandler(servletContextHandler);
		server.addConnector(selectChannelConnector);
		server.start();

		// Log
		LOGGER.debug("server started on port: "
				+ getSelectChannelConnector().getPort());

	}

	public void join() throws InterruptedException {

		// Log
		LOGGER.debug("join server thread...");

		// Join server thread
		server.join();

	}

	public void stop() throws Exception {

		// Log
		LOGGER.debug("stopping server...");

		// Stop server
		server.stop();

		// Log
		LOGGER.info("server stopped");

	}

	private void mapServlet(ServletContextHandler servletContextHandler,
			GenericServlet servlet, String servletName, String servletMapping,
			Map<String, String> initParameters) {

		// Log
		LOGGER.info("mapping servlet: " + servletName + " to: "
				+ servletMapping);

		// Create servlet holder
		ServletHolder servletHolder = new ServletHolder(servletName, servlet);

		// Set init parameters
		servletHolder.setInitParameters(initParameters);

		// Add servlet
		servletContextHandler.addServlet(servletHolder, servletMapping);

	}

}
