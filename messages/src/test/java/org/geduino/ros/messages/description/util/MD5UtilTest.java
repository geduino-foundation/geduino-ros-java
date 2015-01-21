package org.geduino.ros.messages.description.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import junit.framework.TestCase;

import org.geduino.ros.core.naming.model.MessageName;
import org.geduino.ros.core.naming.model.Name;
import org.geduino.ros.messages.description.exception.RosMessageDescriptionException;
import org.geduino.ros.messages.description.loader.MessageDescriptionsLoader;
import org.geduino.ros.messages.description.model.MessageDescription;

public class MD5UtilTest extends TestCase {

	private static final Map<MessageName, String> EXPECTED_MD5 = new HashMap<MessageName, String>();
	static {
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/Bool"),
				"8b94c1b53db61fb6aed406028ad6332a");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/Byte"),
				"ad736a2e8818154c487bb80fe42ce43b");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/ByteMultiArray"),
				"70ea476cbcfd65ac2f68f3cda1e891fe");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/Char"),
				"1bf77f25acecdedba0e224b162199717");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/ColorRGBA"),
				"a29a96539573343b1310c73607334b00");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/Duration"),
				"3e286caf4241d664e55f3ad380e2ae46");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/Empty"),
				"d41d8cd98f00b204e9800998ecf8427e");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/Float32"),
				"73fcbf46b49191e672908e50842a83d4");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/Float32MultiArray"),
				"6a40e0ffa6a17a503ac3f8616991b1f6");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/Float64"),
				"fdb28210bfa9d7c91146260178d9a584");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/Float64MultiArray"),
				"4b7d974086d4060e7db4613a7e6c3ba4");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/Header"),
				"2176decaecbce78abc3b96ef049fabed");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/Int16"),
				"8524586e34fbd7cb1c08c5f5f1ca0e57");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/Int16MultiArray"),
				"d9338d7f523fcb692fae9d0a0e9f067c");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/Int32"),
				"da5909fbe378aeaf85e547e830cc1bb7");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/Int32MultiArray"),
				"1d99f79f8b325b44fee908053e9c945b");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/Int64"),
				"34add168574510e6e17f5d23ecc077ef");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/Int64MultiArray"),
				"54865aa6c65be0448113a2afc6a49270");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/Int8"),
				"27ffa0c9c4b8fb8492252bcad9e5c57b");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/Int8MultiArray"),
				"d7c1af35a1b4781bbe79e03dd94b7c13");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/MultiArrayDimension"),
				"4cd0c83a8683deae40ecdac60e53bfa8");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/MultiArrayLayout"),
				"0fed2a11c13e11c5571b4e2a995a91a3");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/String"),
				"992ce8a1687cec8c8bd883ec73ca41d1");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/Time"),
				"cd7166c74c552c311fbcc2fe5a7bc289");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/UInt16"),
				"1df79edf208b629fe6b81923a544552d");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/UInt16MultiArray"),
				"52f264f1c973c4b73790d384c6cb4484");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/UInt32"),
				"304a39449588c7f8ce2df6e8001c5fce");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/UInt32MultiArray"),
				"4d6a180abc9be191b96a7eda6c8a233d");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/UInt64"),
				"1b2a79973e8bf53d7b53acb71299cb57");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/UInt64MultiArray"),
				"6088f127afb1d6c72927aa1247e945af");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/UInt8"),
				"7c8164229e7d2c17eb95e9231617fdee");
		EXPECTED_MD5.put(Name.parseMessageName("std_msgs/UInt8MultiArray"),
				"82373f1612381bb6ee473b5cd6f5d89c");
	}

	public void test() throws RosMessageDescriptionException, IOException {

		// Load message descriptions
		Map<MessageName, MessageDescription> messageDescriptions = MessageDescriptionsLoader
				.load(new File("src/test/resources/org/geduino/ros/messages"));

		for (Iterator<MessageDescription> iterator = messageDescriptions
				.values().iterator(); iterator.hasNext();) {

			// Get message description
			MessageDescription messageDescription = iterator.next();

			// Get md5
			String md5 = MD5Util.computeMD5(messageDescription,
					messageDescriptions);

			// Get expected md5
			String expectedMd5 = EXPECTED_MD5.get(messageDescription.getName());

			// Asserts
			assertNotNull(expectedMd5);
			assertEquals("msg: " + messageDescription.getName(), expectedMd5, md5);

		}

	}

}
