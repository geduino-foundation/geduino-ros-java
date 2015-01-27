
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
 *   This expresses an estimated pose with a reference coordinate frame and timestamp
 * 
 */
public class PoseWithCovarianceStamped
    extends Message
{

    public final static MessageDetails<PoseWithCovarianceStamped> DETAILS = new MessageDetails<PoseWithCovarianceStamped>(Name.parseMessageName("geometry_msgs/PoseWithCovarianceStamped"), "953b798c0f514ff060a53a3498ce6246", "PoseWithCovarianceStamped message", (PoseWithCovarianceStamped.class));
    /**
     * 
     * 
     */
    public Header header = new Header();
    /**
     * 
     * 
     */
    public PoseWithCovariance pose = new PoseWithCovariance();

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += pose.getLength();
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
        pose.serialize(dataWriter);
    }

}
