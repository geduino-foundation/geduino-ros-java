
package org.geduino.ros.common.msgs.hydro.std_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   Please look at the MultiArrayLayout message definition for  documentation on all multiarrays.
 * 
 */
public class Float32MultiArray
    extends Message
{

    public final static MessageDetails<Float32MultiArray> DETAILS = new MessageDetails<Float32MultiArray>(Name.parseMessageName("std_msgs/Float32MultiArray"), "6a40e0ffa6a17a503ac3f8616991b1f6", "Float32MultiArray message", (Float32MultiArray.class));
    /**
     *  specification of data layout
     * 
     */
    public MultiArrayLayout layout = new MultiArrayLayout();
    /**
     *  array of data
     * 
     */
    public float[] data = new float[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += layout.getLength();
        length += 4;
        for (int index = 0; (index<data.length); index ++) {
            length += 4;
        }
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
        layout.serialize(dataWriter);
        dataWriter.writeUInt32(data.length);
        for (int index = 0; (index<data.length); index ++) {
            dataWriter.writeFloat32(data[index]);
        }
    }

}
