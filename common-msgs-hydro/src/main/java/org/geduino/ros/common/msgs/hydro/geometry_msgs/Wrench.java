
package org.geduino.ros.common.msgs.hydro.geometry_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   This represents force in free space, separated into  its linear and angular parts.
 * 
 */
public class Wrench
    extends Message
{

    public final static MessageDetails<Wrench> DETAILS = new MessageDetails<Wrench>(Name.parseMessageName("geometry_msgs/Wrench"), "4f539cf138b23283b520fd271b567936", "Wrench message", (Wrench.class));
    /**
     * 
     * 
     */
    public org.geduino.ros.common.msgs.hydro.geometry_msgs.Vector3 force = new org.geduino.ros.common.msgs.hydro.geometry_msgs.Vector3();
    /**
     * 
     * 
     */
    public org.geduino.ros.common.msgs.hydro.geometry_msgs.Vector3 torque = new org.geduino.ros.common.msgs.hydro.geometry_msgs.Vector3();

    @Override
    public long getLength() {
        long length = 0;
        length += force.getLength();
        length += torque.getLength();
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
        force.serialize(dataWriter);
        torque.serialize(dataWriter);
    }

}
