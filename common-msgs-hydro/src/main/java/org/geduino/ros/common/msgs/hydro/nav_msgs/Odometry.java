
package org.geduino.ros.common.msgs.hydro.nav_msgs;

import java.io.IOException;
import org.geduino.ros.common.msgs.hydro.geometry_msgs.PoseWithCovariance;
import org.geduino.ros.common.msgs.hydro.geometry_msgs.TwistWithCovariance;
import org.geduino.ros.common.msgs.hydro.std_msgs.Header;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   This represents an estimate of a position and velocity in free space.  The pose in this message should be specified in the coordinate frame given by header.frame_id.  The twist in this message should be specified in the coordinate frame given by the child_frame_id
 * 
 */
public class Odometry
    extends Message
{

    public final static MessageDetails<Odometry> DETAILS = new MessageDetails<Odometry>(Name.parseMessageName("nav_msgs/Odometry"), "cd5e73d190d741a2f92e81eda573aca7", "Odometry message", (Odometry.class));
    /**
     * 
     * 
     */
    public Header header = new Header();
    /**
     * 
     * 
     */
    public String child_frame_id = "";
    /**
     * 
     * 
     */
    public PoseWithCovariance pose = new PoseWithCovariance();
    /**
     * 
     * 
     */
    public TwistWithCovariance twist = new TwistWithCovariance();

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += (child_frame_id.length()+ 4);
        length += pose.getLength();
        length += twist.getLength();
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
        dataWriter.writeUInt32(child_frame_id.length());
        dataWriter.writeString(child_frame_id);
        pose.serialize(dataWriter);
        twist.serialize(dataWriter);
    }

}
