
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
public class UInt16MultiArray
    extends Message
{

    public final static MessageDetails<UInt16MultiArray> DETAILS = new MessageDetails<UInt16MultiArray>(Name.parseMessageName("std_msgs/UInt16MultiArray"), "52f264f1c973c4b73790d384c6cb4484", "UInt16MultiArray message", (UInt16MultiArray.class));
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
            length += 2;
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
            dataWriter.writeUInt16(data[index]);
        }
    }

}
