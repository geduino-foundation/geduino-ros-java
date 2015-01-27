
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
public class Char
    extends Message
{

    public final static MessageDetails<Char> DETAILS = new MessageDetails<Char>(Name.parseMessageName("std_msgs/Char"), "1bf77f25acecdedba0e224b162199717", "Char message", (Char.class));
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
        dataWriter.writeUInt8(data);
    }

}
