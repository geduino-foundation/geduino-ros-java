
package org.geduino.ros.common.msgs.hydro.sensor_msgs;

import java.io.IOException;
import org.geduino.ros.common.msgs.hydro.geometry_msgs.Transform;
import org.geduino.ros.common.msgs.hydro.geometry_msgs.Twist;
import org.geduino.ros.common.msgs.hydro.geometry_msgs.Wrench;
import org.geduino.ros.common.msgs.hydro.std_msgs.Header;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   Representation of state for joints with multiple degrees of freedom,  following the structure of JointState.   It is assumed that a joint in a system corresponds to a transform that gets applied  along the kinematic chain. For example, a planar joint (as in URDF) is 3DOF (x, y, yaw)  and those 3DOF can be expressed as a transformation matrix, and that transformation  matrix can be converted back to (x, y, yaw)   Each joint is uniquely identified by its name  The header specifies the time at which the joint states were recorded. All the joint states  in one message have to be recorded at the same time.   This message consists of a multiple arrays, one for each part of the joint state.  The goal is to make each of the fields optional. When e.g. your joints have no  wrench associated with them, you can leave the wrench array empty.   All arrays in this message should have the same size, or be empty.  This is the only way to uniquely associate the joint name with the correct  states.
 * 
 */
public class MultiDOFJointState
    extends Message
{

    public final static MessageDetails<MultiDOFJointState> DETAILS = new MessageDetails<MultiDOFJointState>(Name.parseMessageName("sensor_msgs/MultiDOFJointState"), "690f272f0640d2631c305eeb8301e59d", "MultiDOFJointState message", (MultiDOFJointState.class));
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
    public Transform[] transforms = new Transform[] {};
    /**
     * 
     * 
     */
    public Twist[] twist = new Twist[] {};
    /**
     * 
     * 
     */
    public Wrench[] wrench = new Wrench[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += 4;
        for (int index = 0; (index<joint_names.length); index ++) {
            length += (joint_names[index].length()+ 4);
        }
        length += 4;
        for (int index = 0; (index<transforms.length); index ++) {
            length += transforms[index].getLength();
        }
        length += 4;
        for (int index = 0; (index<twist.length); index ++) {
            length += twist[index].getLength();
        }
        length += 4;
        for (int index = 0; (index<wrench.length); index ++) {
            length += wrench[index].getLength();
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
        dataWriter.writeUInt32(transforms.length);
        for (int index = 0; (index<transforms.length); index ++) {
            transforms[index].serialize(dataWriter);
        }
        dataWriter.writeUInt32(twist.length);
        for (int index = 0; (index<twist.length); index ++) {
            twist[index].serialize(dataWriter);
        }
        dataWriter.writeUInt32(wrench.length);
        for (int index = 0; (index<wrench.length); index ++) {
            wrench[index].serialize(dataWriter);
        }
    }

}
