
package org.geduino.ros.common.msgs.hydro.geometry_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   A representation of pose in free space, composed of postion and orientation.
 * 
 */
public class Pose
    extends Message
{

    public final static MessageDetails<Pose> DETAILS = new MessageDetails<Pose>(Name.parseMessageName("geometry_msgs/Pose"), "e45d45a5a1ce597b249e23fb30fc871f", "Pose message", (Pose.class));
    /**
     * 
     * 
     */
    public Point position = new Point();
    /**
     * 
     * 
     */
    public Quaternion orientation = new Quaternion();

    @Override
    public long getLength() {
        long length = 0;
        length += position.getLength();
        length += orientation.getLength();
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
        position.serialize(dataWriter);
        orientation.serialize(dataWriter);
    }

}
