
package org.geduino.ros.common.msgs.hydro.geometry_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   This contains the position of a point in free space(with 32 bits of precision).  It is recommeded to use Point wherever possible instead of Point32.   This recommendation is to promote interoperability.   This message is designed to take up less space when sending  lots of points at once, as in the case of a PointCloud.
 * 
 */
public class Point32
    extends Message
{

    public final static MessageDetails<Point32> DETAILS = new MessageDetails<Point32>(Name.parseMessageName("geometry_msgs/Point32"), "cc153912f1453b708d221682bc23d9ac", "Point32 message", (Point32.class));
    /**
     * 
     * 
     */
    public float x = 0.0F;
    /**
     * 
     * 
     */
    public float y = 0.0F;
    /**
     * 
     * 
     */
    public float z = 0.0F;

    @Override
    public long getLength() {
        long length = 0;
        length += 4;
        length += 4;
        length += 4;
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
        dataWriter.writeFloat32(x);
        dataWriter.writeFloat32(y);
        dataWriter.writeFloat32(z);
    }

}
