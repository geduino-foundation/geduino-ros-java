
package org.geduino.ros.common.msgs.hydro.trajectory_msgs;

import java.io.IOException;
import org.geduino.ros.common.msgs.hydro.std_msgs.Header;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   The header is used to specify the coordinate frame and the reference time for the trajectory durations
 * 
 */
public class MultiDOFJointTrajectory
    extends Message
{

    public final static MessageDetails<MultiDOFJointTrajectory> DETAILS = new MessageDetails<MultiDOFJointTrajectory>(Name.parseMessageName("trajectory_msgs/MultiDOFJointTrajectory"), "ef145a45a5f47b77b7f5cdde4b16c942", "MultiDOFJointTrajectory message", (MultiDOFJointTrajectory.class));
    /**
     *  A representation of a multi-dof joint trajectory (each point is a transformation)# Each point along the trajectory will include an array of positions/velocities/accelerations# that has the same length as the array of joint names, and has the same order of joints as# the joint names array.
     * 
     */
    public Header header = new Header();
    /**
     * 
     * 
     */
    public String[] joint_names = new String[] {};
    /**
     * 
     * 
     */
    public MultiDOFJointTrajectoryPoint[] points = new MultiDOFJointTrajectoryPoint[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += 4;
        for (int index = 0; (index<joint_names.length); index ++) {
            length += (joint_names[index].length()+ 4);
        }
        length += 4;
        for (int index = 0; (index<points.length); index ++) {
            length += points[index].getLength();
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
        dataWriter.writeUInt32(joint_names.length);
        for (int index = 0; (index<joint_names.length); index ++) {
            dataWriter.writeUInt32(joint_names[index].length());
            dataWriter.writeString(joint_names[index]);
        }
        dataWriter.writeUInt32(points.length);
        for (int index = 0; (index<points.length); index ++) {
            points[index].serialize(dataWriter);
        }
    }

}
