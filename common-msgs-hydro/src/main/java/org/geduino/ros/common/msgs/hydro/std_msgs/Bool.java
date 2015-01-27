
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
public class Bool
    extends Message
{

    public final static MessageDetails<Bool> DETAILS = new MessageDetails<Bool>(Name.parseMessageName("std_msgs/Bool"), "8b94c1b53db61fb6aed406028ad6332a", "Bool message", (Bool.class));
    /**
     * 
     * 
     */
    public boolean data = false;

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
        dataWriter.writeBool(data);
    }

}
