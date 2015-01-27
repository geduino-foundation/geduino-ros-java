
package org.geduino.ros.common.msgs.hydro.sensor_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   This message is used by the PointCloud message to hold optional data  associated with each point in the cloud. The length of the values  array should be the same as the length of the points array in the  PointCloud, and each value should be associated with the corresponding  point.  Channel names in existing practice include:    "u", "v" - row and column (respectively) in the left stereo image.               This is opposite to usual conventions but remains for               historical reasons. The newer PointCloud2 message has no               such problem.    "rgb" - For point clouds produced by color stereo cameras. uint8            (R,G,B) values packed into the least significant 24 bits,            in order.    "intensity" - laser or pixel intensity.    "distance"  The channel name should give semantics of the channel (e.g.  "intensity" instead of "value").
 * 
 */
public class ChannelFloat32
    extends Message
{

    public final static MessageDetails<ChannelFloat32> DETAILS = new MessageDetails<ChannelFloat32>(Name.parseMessageName("sensor_msgs/ChannelFloat32"), "3d40139cdd33dfedcb71ffeeeb42ae7f", "ChannelFloat32 message", (ChannelFloat32.class));
    /**
     *  The values array should be 1-1 with the elements of the associated# PointCloud.
     * 
     */
    public String name = "";
    /**
     * 
     * 
     */
    public float[] values = new float[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += (name.length()+ 4);
        length += 4;
        for (int index = 0; (index<values.length); index ++) {
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
        dataWriter.writeUInt32(name.length());
        dataWriter.writeString(name);
        dataWriter.writeUInt32(values.length);
        for (int index = 0; (index<values.length); index ++) {
            dataWriter.writeFloat32(values[index]);
        }
    }

}
