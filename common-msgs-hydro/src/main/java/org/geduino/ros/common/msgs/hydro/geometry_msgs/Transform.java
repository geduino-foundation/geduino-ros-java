
package org.geduino.ros.common.msgs.hydro.geometry_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   This represents the transform between two coordinate frames in free space.
 * 
 */
public class Transform
    extends Message
{

    public final static MessageDetails<Transform> DETAILS = new MessageDetails<Transform>(Name.parseMessageName("geometry_msgs/Transform"), "ac9eff44abf714214112b05d54a3cf9b", "Transform message", (Transform.class));
    /**
     * 
     * 
     */
    public Vector3 translation = new Vector3();
    /**
     * 
     * 
     */
    public Quaternion rotation = new Quaternion();

    @Override
    public long getLength() {
        long length = 0;
        length += translation.getLength();
        length += rotation.getLength();
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
        translation.serialize(dataWriter);
        rotation.serialize(dataWriter);
    }

}
