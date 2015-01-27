
package org.geduino.ros.common.msgs.hydro.geometry_msgs;

import java.io.IOException;
import org.geduino.ros.common.msgs.hydro.std_msgs.Header;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   This represents a Polygon with reference coordinate frame and timestamp
 * 
 */
public class PolygonStamped
    extends Message
{

    public final static MessageDetails<PolygonStamped> DETAILS = new MessageDetails<PolygonStamped>(Name.parseMessageName("geometry_msgs/PolygonStamped"), "c6be8f7dc3bee7fe9e8d296070f53340", "PolygonStamped message", (PolygonStamped.class));
    /**
     * 
     * 
     */
    public Header header = new Header();
    /**
     * 
     * 
     */
    public Polygon polygon = new Polygon();

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += polygon.getLength();
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
        polygon.serialize(dataWriter);
    }

}
