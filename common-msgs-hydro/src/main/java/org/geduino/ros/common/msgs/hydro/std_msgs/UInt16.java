
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
public class UInt16
    extends Message
{

    public final static MessageDetails<UInt16> DETAILS = new MessageDetails<UInt16>(Name.parseMessageName("std_msgs/UInt16"), "1df79edf208b629fe6b81923a544552d", "UInt16 message", (UInt16.class));
    /**
     * 
     * 
     */
    public int data = 0;

    @Override
    public long getLength() {
        long length = 0;
        length += 2;
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
        dataWriter.writeUInt16(data);
    }

}
