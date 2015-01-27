
package org.geduino.ros.common.msgs.hydro.geometry_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   This represents an orientation in free space in quaternion form.
 * 
 */
public class Quaternion
    extends Message
{

    public final static MessageDetails<Quaternion> DETAILS = new MessageDetails<Quaternion>(Name.parseMessageName("geometry_msgs/Quaternion"), "a779879fadf0160734f906b8c19c7004", "Quaternion message", (Quaternion.class));
    /**
     * 
     * 
     */
    public double x = 0.0D;
    /**
     * 
     * 
     */
    public double y = 0.0D;
    /**
     * 
     * 
     */
    public double z = 0.0D;
    /**
     * 
     * 
     */
    public double w = 0.0D;

    @Override
    public long getLength() {
        long length = 0;
        length += 8;
        length += 8;
        length += 8;
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
        dataWriter.writeFloat64(x);
        dataWriter.writeFloat64(y);
        dataWriter.writeFloat64(z);
        dataWriter.writeFloat64(w);
    }

}
