
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
public class MultiArrayDimension
    extends Message
{

    public final static MessageDetails<MultiArrayDimension> DETAILS = new MessageDetails<MultiArrayDimension>(Name.parseMessageName("std_msgs/MultiArrayDimension"), "4cd0c83a8683deae40ecdac60e53bfa8", "MultiArrayDimension message", (MultiArrayDimension.class));
    /**
     *  label of given dimension
     * 
     */
    public java.lang.String label = "";
    /**
     *  size of given dimension (in type units)
     * 
     */
    public long size = 0L;
    /**
     *  stride of given dimension
     * 
     */
    public long stride = 0L;

    @Override
    public long getLength() {
        long length = 0;
        length += (label.length()+ 4);
        length += 4;
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
        dataWriter.writeUInt32(label.length());
        dataWriter.writeString(label);
        dataWriter.writeUInt32(size);
        dataWriter.writeUInt32(stride);
    }

}
