package org.geduino.ros.messages.description;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.geduino.ros.core.naming.model.MessageName;
import org.geduino.ros.messages.description.exception.RosMessageDescriptionException;
import org.geduino.ros.messages.description.loader.MessageDescriptionsLoader;
import org.geduino.ros.messages.description.model.MessageDescription;

public class Main {

	/**
	 * @param args
	 * @throws RosMessageDescriptionException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws RosMessageDescriptionException, IOException {
		
		Map<MessageName, MessageDescription> msgs = MessageDescriptionsLoader.load(new File("src/main/resources/org/geduino/ros/messages"));
		
		Set<MessageName> keys = msgs.keySet();
		
		System.out.println(keys);
		for (Iterator<MessageDescription> iterator = msgs.values().iterator(); iterator.hasNext();) {
			
			MessageDescription md = iterator.next();
			
			System.out.println(md.toString() + " - " + md.getUnmeetDependencies(keys) + " - " + md.getDependencies());
		}
		
		
	}

}
