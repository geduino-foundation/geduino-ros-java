
package org.geduino.ros.common.msgs.hydro.std_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   The multiarray declares a generic multi-dimensional array of a  particular data type.  Dimensions are ordered from outer most  to inner most.
 * 
 */
public class MultiArrayLayout
    extends Message
{

    public final static MessageDetails<MultiArrayLayout> DETAILS = new MessageDetails<MultiArrayLayout>(Name.parseMessageName("std_msgs/MultiArrayLayout"), "0fed2a11c13e11c5571b4e2a995a91a3", "MultiArrayLayout message", (MultiArrayLayout.class));
    /**
     *  Array of dimension properties
     * 
     */
    public MultiArrayDimension[] dim = new MultiArrayDimension[] {};
    /**
     *  padding bytes at front of data# Accessors should ALWAYS be written in terms of dimension stride# and specified outer-most dimension first.## multiarray(i,j,k) = data[data_offset + dim_stride[1]*i + dim_stride[2]*j + k]## A standard, 3-channel 640x480 image with interleaved color channels# would be specified as:## dim[0].label  = "height"# dim[0].size   = 480# dim[0].stride = 3*640*480 = 921600  (note dim[0] stride is just size of image)# dim[1].label  = "width"# dim[1].size   = 640# dim[1].stride = 3*640 = 1920# dim[2].label  = "channel"# dim[2].size   = 3# dim[2].stride = 3## multiarray(i,j,k) refers to the ith row, jth column, and kth channel.
     * 
     */
    public long data_offset = 0L;

    @Override
    public long getLength() {
        long length = 0;
        length += 4;
        for (int index = 0; (index<dim.length); index ++) {
            length += dim[index].getLength();
        }
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
        dataWriter.writeUInt32(dim.length);
        for (int index = 0; (index<dim.length); index ++) {
            dim[index].serialize(dataWriter);
        }
        dataWriter.writeUInt32(data_offset);
    }

}
