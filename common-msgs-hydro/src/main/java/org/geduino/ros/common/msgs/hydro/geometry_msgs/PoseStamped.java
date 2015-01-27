
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
 *   A Pose with reference coordinate frame and timestamp
 * 
 */
public class PoseStamped
    extends Message
{

    public final static MessageDetails<PoseStamped> DETAILS = new MessageDetails<PoseStamped>(Name.parseMessageName("geometry_msgs/PoseStamped"), "d3812c3cbc69362b77dc0b19b345f8f5", "PoseStamped message", (PoseStamped.class));
    /**
     * 
     * 
     */
    public Header header = new Header();
    /**
     * 
     * 
     */
    public Pose pose = new Pose();

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
