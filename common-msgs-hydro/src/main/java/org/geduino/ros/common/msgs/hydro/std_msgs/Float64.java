
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
public class Float64
    extends Message
{

    public final static MessageDetails<Float64> DETAILS = new MessageDetails<Float64>(Name.parseMessageName("std_msgs/Float64"), "fdb28210bfa9d7c91146260178d9a584", "Float64 message", (Float64.class));
    /**
     * 
     * 
     */
    public double data = 0.0D;

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
        dataWriter.writeFloat64(data);
    }

}
