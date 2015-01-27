
package org.geduino.ros.common.msgs.hydro.sensor_msgs;

import java.io.IOException;
import org.geduino.ros.common.msgs.hydro.geometry_msgs.Point32;
import org.geduino.ros.common.msgs.hydro.std_msgs.Header;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   This message holds a collection of 3d points, plus optional additional  information about each point.  Time of sensor data acquisition, coordinate frame ID.
 * 
 */
public class PointCloud
    extends Message
{

    public final static MessageDetails<PointCloud> DETAILS = new MessageDetails<PointCloud>(Name.parseMessageName("sensor_msgs/PointCloud"), "d8e9c3f5afbdd8a130fd1d2763945fca", "PointCloud message", (PointCloud.class));
    /**
     *  Array of 3d points. Each Point32 should be interpreted as a 3d point# in the frame given in the header.
     * 
     */
    public Header header = new Header();
    /**
     *  Each channel should have the same number of elements as points array,# and the data in each channel should correspond 1:1 with each point.# Channel names in common practice are listed in ChannelFloat32.msg.
     * 
     */
    public Point32 [] points = new Point32 [] {};
    /**
     * 
     * 
     */
    public ChannelFloat32 [] channels = new ChannelFloat32 [] {};

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += 4;
        for (int index = 0; (index<points.length); index ++) {
            length += points[index].getLength();
        }
        length += 4;
        for (int index = 0; (index<channels.length); index ++) {
            length += channels[index].getLength();
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
        header.serialize(dataWriter);
        dataWriter.writeUInt32(points.length);
        for (int index = 0; (index<points.length); index ++) {
            points[index].serialize(dataWriter);
        }
        dataWriter.writeUInt32(channels.length);
        for (int index = 0; (index<channels.length); index ++) {
            channels[index].serialize(dataWriter);
        }
    }

}
