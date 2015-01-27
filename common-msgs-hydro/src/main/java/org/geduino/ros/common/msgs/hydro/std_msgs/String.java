
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
public class String
    extends Message
{

    public final static MessageDetails<String> DETAILS = new MessageDetails<String>(Name.parseMessageName("std_msgs/String"), "992ce8a1687cec8c8bd883ec73ca41d1", "String message", (String.class));
    /**
     * 
     * 
     */
    public java.lang.String data = "";

    @Override
    public long getLength() {
        long length = 0;
        length += (data.length()+ 4);
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
        dataWriter.writeUInt32(data.length());
        dataWriter.writeString(data);
    }

}
