
package org.geduino.ros.common.msgs.hydro.sensor_msgs;

import java.io.IOException;
import org.geduino.ros.common.msgs.hydro.std_msgs.Header;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   This is a message that holds data to describe the state of a set of torque controlled joints.   The state of each joint (revolute or prismatic) is defined by:   * the position of the joint (rad or m),   * the velocity of the joint (rad/s or m/s) and   * the effort that is applied in the joint (Nm or N).   Each joint is uniquely identified by its name  The header specifies the time at which the joint states were recorded. All the joint states  in one message have to be recorded at the same time.   This message consists of a multiple arrays, one for each part of the joint state.  The goal is to make each of the fields optional. When e.g. your joints have no  effort associated with them, you can leave the effort array empty.   All arrays in this message should have the same size, or be empty.  This is the only way to uniquely associate the joint name with the correct  states.
 * 
 */
public class JointState
    extends Message
{

    public final static MessageDetails<JointState> DETAILS = new MessageDetails<JointState>(Name.parseMessageName("sensor_msgs/JointState"), "3066dcd76a6cfaef579bd0f34173e9fd", "JointState message", (JointState.class));
    /**
     * 
     * 
     */
    public Header header = new Header();
    /**
     * 
     * 
     */
    public String[] name = new String[] {};
    /**
     * 
     * 
     */
    public double[] position = new double[] {};
    /**
     * 
     * 
     */
    public double[] velocity = new double[] {};
    /**
     * 
     * 
     */
    public double[] effort = new double[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += 4;
        for (int index = 0; (index<name.length); index ++) {
            length += (name[index].length()+ 4);
        }
        length += 4;
        for (int index = 0; (index<position.length); index ++) {
            length += 8;
        }
        length += 4;
        for (int index = 0; (index<velocity.length); index ++) {
            length += 8;
        }
        length += 4;
        for (int index = 0; (index<effort.length); index ++) {
            length += 8;
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
        dataWriter.writeUInt32(name.length);
        for (int index = 0; (index<name.length); index ++) {
            dataWriter.writeUInt32(name[index].length());
            dataWriter.writeString(name[index]);
        }
        dataWriter.writeUInt32(position.length);
        for (int index = 0; (index<position.length); index ++) {
            dataWriter.writeFloat64(position[index]);
        }
        dataWriter.writeUInt32(velocity.length);
        for (int index = 0; (index<velocity.length); index ++) {
            dataWriter.writeFloat64(velocity[index]);
        }
        dataWriter.writeUInt32(effort.length);
        for (int index = 0; (index<effort.length); index ++) {
            dataWriter.writeFloat64(effort[index]);
        }
    }

}
