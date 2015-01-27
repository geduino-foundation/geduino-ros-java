
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
public class Float32
    extends Message
{

    public final static MessageDetails<Float32> DETAILS = new MessageDetails<Float32>(Name.parseMessageName("std_msgs/Float32"), "73fcbf46b49191e672908e50842a83d4", "Float32 message", (Float32.class));
    /**
     * 
     * 
     */
    public float data = 0.0F;

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
        dataWriter.writeFloat32(data);
    }

}
