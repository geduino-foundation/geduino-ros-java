
package org.geduino.ros.common.msgs.hydro.geometry_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   This contains the position of a point in free space
 * 
 */
public class Point
    extends Message
{

    public final static MessageDetails<Point> DETAILS = new MessageDetails<Point>(Name.parseMessageName("geometry_msgs/Point"), "4a842b65f413084dc2b10fb484ea7f17", "Point message", (Point.class));
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

    @Override
    public long getLength() {
        long length = 0;
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
    }

}
