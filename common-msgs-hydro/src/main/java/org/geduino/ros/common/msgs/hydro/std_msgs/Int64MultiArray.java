
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
public class Int64MultiArray
    extends Message
{

    public final static MessageDetails<Int64MultiArray> DETAILS = new MessageDetails<Int64MultiArray>(Name.parseMessageName("std_msgs/Int64MultiArray"), "54865aa6c65be0448113a2afc6a49270", "Int64MultiArray message", (Int64MultiArray.class));
    /**
     *  specification of data layout
     * 
     */
    public MultiArrayLayout layout = new MultiArrayLayout();
    /**
     *  array of data
     * 
     */
    public long[] data = new long[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += layout.getLength();
        length += 4;
        for (int index = 0; (index<data.length); index ++) {
            length += 8;
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
            dataWriter.writeInt64(data[index]);
        }
    }

}
