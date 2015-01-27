
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
public class Byte
    extends Message
{

    public final static MessageDetails<Byte> DETAILS = new MessageDetails<Byte>(Name.parseMessageName("std_msgs/Byte"), "ad736a2e8818154c487bb80fe42ce43b", "Byte message", (Byte.class));
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
