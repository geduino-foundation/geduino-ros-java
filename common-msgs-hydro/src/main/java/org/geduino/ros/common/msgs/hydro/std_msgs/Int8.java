
package org.geduino.ros.common.msgs.hydro.std_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 * 
 * 
 */
public class Int8
    extends Message
{

    public final static MessageDetails<Int8> DETAILS = new MessageDetails<Int8>(Name.parseMessageName("std_msgs/Int8"), "27ffa0c9c4b8fb8492252bcad9e5c57b", "Int8 message", (Int8.class));
    /**
     * 
     * 
     */
    public int data = 0;

    @Override
    public long getLength() {
        long length = 0;
        length += 1;
        return length;
    }

    public void deserialize(DataReader dataReader)
        throws IOException, RosMessageSerializationException
    {
    }

    @Override
    public void serialize(DataWriter dataWriter)
        throws IOException, RosMessageSerializationException
    {
        dataWriter.writeInt8(data);
    }

}
