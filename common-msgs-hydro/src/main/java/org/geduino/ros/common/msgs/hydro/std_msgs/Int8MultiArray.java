
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
public class Int8MultiArray
    extends Message
{

    public final static MessageDetails<Int8MultiArray> DETAILS = new MessageDetails<Int8MultiArray>(Name.parseMessageName("std_msgs/Int8MultiArray"), "d7c1af35a1b4781bbe79e03dd94b7c13", "Int8MultiArray message", (Int8MultiArray.class));
    /**
     *  specification of data layout
     * 
     */
    public MultiArrayLayout layout = new MultiArrayLayout();
    /**
     *  array of data
     * 
     */
    public int[] data = new int[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += layout.getLength();
        length += 4;
        for (int index = 0; (index<data.length); index ++) {
            length += 1;
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
            dataWriter.writeInt8(data[index]);
        }
    }

}
