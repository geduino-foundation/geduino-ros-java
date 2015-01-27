
package org.geduino.ros.common.msgs.hydro.geometry_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *  A specification of a polygon where the first and last points are assumed to be connected
 * 
 */
public class Polygon
    extends Message
{

    public final static MessageDetails<Polygon> DETAILS = new MessageDetails<Polygon>(Name.parseMessageName("geometry_msgs/Polygon"), "cd60a26494a087f577976f0329fa120e", "Polygon message", (Polygon.class));
    /**
     * 
     * 
     */
    public Point32 [] points = new Point32 [] {};

    @Override
    public long getLength() {
        long length = 0;
        length += 4;
        for (int index = 0; (index<points.length); index ++) {
            length += points[index].getLength();
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
        dataWriter.writeUInt32(points.length);
        for (int index = 0; (index<points.length); index ++) {
            points[index].serialize(dataWriter);
        }
    }

}
