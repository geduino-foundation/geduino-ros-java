
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
 *   This represents a Point with reference coordinate frame and timestamp
 * 
 */
public class PointStamped
    extends Message
{

    public final static MessageDetails<PointStamped> DETAILS = new MessageDetails<PointStamped>(Name.parseMessageName("geometry_msgs/PointStamped"), "c63aecb41bfdfd6b7e1fac37c7cbe7bf", "PointStamped message", (PointStamped.class));
    /**
     * 
     * 
     */
    public Header header = new Header();
    /**
     * 
     * 
     */
    public Point point = new Point();

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += point.getLength();
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
        point.serialize(dataWriter);
    }

}
