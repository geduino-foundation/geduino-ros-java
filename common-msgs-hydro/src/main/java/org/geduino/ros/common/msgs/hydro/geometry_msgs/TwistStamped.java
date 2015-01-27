
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
 *   A twist with reference coordinate frame and timestamp
 * 
 */
public class TwistStamped
    extends Message
{

    public final static MessageDetails<TwistStamped> DETAILS = new MessageDetails<TwistStamped>(Name.parseMessageName("geometry_msgs/TwistStamped"), "98d34b0043a2093cf9d9345ab6eef12e", "TwistStamped message", (TwistStamped.class));
    /**
     * 
     * 
     */
    public Header header = new Header();
    /**
     * 
     * 
     */
    public Twist twist = new Twist();

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += twist.getLength();
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
        twist.serialize(dataWriter);
    }

}
