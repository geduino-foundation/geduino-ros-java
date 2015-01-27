
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
 *   This represents an estimated twist with reference coordinate frame and timestamp.
 * 
 */
public class TwistWithCovarianceStamped
    extends Message
{

    public final static MessageDetails<TwistWithCovarianceStamped> DETAILS = new MessageDetails<TwistWithCovarianceStamped>(Name.parseMessageName("geometry_msgs/TwistWithCovarianceStamped"), "8927a1a12fb2607ceea095b2dc440a96", "TwistWithCovarianceStamped message", (TwistWithCovarianceStamped.class));
    /**
     * 
     * 
     */
    public Header header = new Header();
    /**
     * 
     * 
     */
    public TwistWithCovariance twist = new TwistWithCovariance();

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
