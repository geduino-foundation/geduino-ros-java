
package org.geduino.ros.common.msgs.hydro.trajectory_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   Each trajectory point specifies either positions[, velocities[, accelerations]]  or positions[, effort] for the trajectory to be executed.  All specified values are in the same order as the joint names in JointTrajectory.msg
 * 
 */
public class JointTrajectoryPoint
    extends Message
{

    public final static MessageDetails<JointTrajectoryPoint> DETAILS = new MessageDetails<JointTrajectoryPoint>(Name.parseMessageName("trajectory_msgs/JointTrajectoryPoint"), "f3cd1e1c4d320c79d6985c904ae5dcd3", "JointTrajectoryPoint message", (JointTrajectoryPoint.class));
    /**
     * 
     * 
     */
    public double[] positions = new double[] {};
    /**
     * 
     * 
     */
    public double[] velocities = new double[] {};
    /**
     * 
     * 
     */
    public double[] accelerations = new double[] {};
    /**
     * 
     * 
     */
    public double[] effort = new double[] {};
    /**
     * 
     * 
     */
    public long time_from_start = 0L;

    @Override
    public long getLength() {
        long length = 0;
        length += 4;
        for (int index = 0; (index<positions.length); index ++) {
            length += 8;
        }
        length += 4;
        for (int index = 0; (index<velocities.length); index ++) {
            length += 8;
        }
        length += 4;
        for (int index = 0; (index<accelerations.length); index ++) {
            length += 8;
        }
        length += 4;
        for (int index = 0; (index<effort.length); index ++) {
            length += 8;
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
        dataWriter.writeUInt32(positions.length);
        for (int index = 0; (index<positions.length); index ++) {
            dataWriter.writeFloat64(positions[index]);
        }
        dataWriter.writeUInt32(velocities.length);
        for (int index = 0; (index<velocities.length); index ++) {
            dataWriter.writeFloat64(velocities[index]);
        }
        dataWriter.writeUInt32(accelerations.length);
        for (int index = 0; (index<accelerations.length); index ++) {
            dataWriter.writeFloat64(accelerations[index]);
        }
        dataWriter.writeUInt32(effort.length);
        for (int index = 0; (index<effort.length); index ++) {
            dataWriter.writeFloat64(effort[index]);
        }
        dataWriter.writeDuration(time_from_start);
    }

}
