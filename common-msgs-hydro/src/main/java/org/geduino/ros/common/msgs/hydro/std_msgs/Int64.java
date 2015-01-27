
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
public class Int64
    extends Message
{

    public final static MessageDetails<Int64> DETAILS = new MessageDetails<Int64>(Name.parseMessageName("std_msgs/Int64"), "34add168574510e6e17f5d23ecc077ef", "Int64 message", (Int64.class));
    /**
     * 
     * 
     */
    public long data = 0L;

    @Override
    public long getLength() {
        long length = 0;
        length += 8;
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
        dataWriter.writeInt64(data);
    }

}
