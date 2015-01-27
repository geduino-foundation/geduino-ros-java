
package org.geduino.ros.common.msgs.hydro.trajectory_msgs;

import java.io.IOException;
import org.geduino.ros.common.msgs.hydro.geometry_msgs.Transform;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   Each multi-dof joint can specify a transform (up to 6 DOF)
 * 
 */
public class MultiDOFJointTrajectoryPoint
    extends Message
{

    public final static MessageDetails<MultiDOFJointTrajectoryPoint> DETAILS = new MessageDetails<MultiDOFJointTrajectoryPoint>(Name.parseMessageName("trajectory_msgs/MultiDOFJointTrajectoryPoint"), "3ebe08d1abd5b65862d50e09430db776", "MultiDOFJointTrajectoryPoint message", (MultiDOFJointTrajectoryPoint.class));
    /**
     *  There can be a velocity specified for the origin of the joint
     * 
     */
    public Transform[] transforms = new Transform[] {};
    /**
     *  There can be an acceleration specified for the origin of the joint
     * 
     */
    public org.geduino.ros.common.msgs.hydro.geometry_msgs.Twist[] velocities = new org.geduino.ros.common.msgs.hydro.geometry_msgs.Twist[] {};
    /**
     * 
     * 
     */
    public org.geduino.ros.common.msgs.hydro.geometry_msgs.Twist[] accelerations = new org.geduino.ros.common.msgs.hydro.geometry_msgs.Twist[] {};
    /**
     * 
     * 
     */
    public long time_from_start = 0L;

    @Override
    public long getLength() {
        long length = 0;
        length += 4;
        for (int index = 0; (index<transforms.length); index ++) {
            length += transforms[index].getLength();
        }
        length += 4;
        for (int index = 0; (index<velocities.length); index ++) {
            length += velocities[index].getLength();
        }
        length += 4;
        for (int index = 0; (index<accelerations.length); index ++) {
            length += accelerations[index].getLength();
        }
        length += 8;
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
        dataWriter.writeUInt32(transforms.length);
        for (int index = 0; (index<transforms.length); index ++) {
            transforms[index].serialize(dataWriter);
        }
        dataWriter.writeUInt32(velocities.length);
        for (int index = 0; (index<velocities.length); index ++) {
            velocities[index].serialize(dataWriter);
        }
        dataWriter.writeUInt32(accelerations.length);
        for (int index = 0; (index<accelerations.length); index ++) {
            accelerations[index].serialize(dataWriter);
        }
        dataWriter.writeDuration(time_from_start);
    }

}
