
package org.geduino.ros.common.msgs.hydro.geometry_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   This expresses velocity in free space broken into its linear and angular parts.
 * 
 */
public class Twist
    extends Message
{

    public final static MessageDetails<Twist> DETAILS = new MessageDetails<Twist>(Name.parseMessageName("geometry_msgs/Twist"), "9f195f881246fdfa2798d1d3eebca84a", "Twist message", (Twist.class));
    /**
     * 
     * 
     */
    public org.geduino.ros.common.msgs.hydro.geometry_msgs.Vector3 linear = new org.geduino.ros.common.msgs.hydro.geometry_msgs.Vector3();
    /**
     * 
     * 
     */
    public org.geduino.ros.common.msgs.hydro.geometry_msgs.Vector3 angular = new org.geduino.ros.common.msgs.hydro.geometry_msgs.Vector3();

    @Override
    public long getLength() {
        long length = 0;
        length += linear.getLength();
        length += angular.getLength();
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
        linear.serialize(dataWriter);
        angular.serialize(dataWriter);
    }

}
