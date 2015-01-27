
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
 * 
 * 
 */
public class JointTrajectory
    extends Message
{

    public final static MessageDetails<JointTrajectory> DETAILS = new MessageDetails<JointTrajectory>(Name.parseMessageName("trajectory_msgs/JointTrajectory"), "65b4f94a94d1ed67169da35a02f33d3f", "JointTrajectory message", (JointTrajectory.class));
    /**
     * 
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
    public JointTrajectoryPoint[] points = new JointTrajectoryPoint[] {};

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
