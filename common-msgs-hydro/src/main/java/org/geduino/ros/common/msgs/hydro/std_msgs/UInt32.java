
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
public class UInt32
    extends Message
{

    public final static MessageDetails<UInt32> DETAILS = new MessageDetails<UInt32>(Name.parseMessageName("std_msgs/UInt32"), "304a39449588c7f8ce2df6e8001c5fce", "UInt32 message", (UInt32.class));
    /**
     * 
     * 
     */
    public long data = 0L;

    @Override
    public long getLength() {
        long length = 0;
        length += 4;
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
        dataWriter.writeUInt32(data);
    }

}
