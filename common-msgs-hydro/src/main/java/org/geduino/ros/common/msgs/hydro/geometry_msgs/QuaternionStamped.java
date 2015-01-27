
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
 *   This represents an orientation with reference coordinate frame and timestamp.
 * 
 */
public class QuaternionStamped
    extends Message
{

    public final static MessageDetails<QuaternionStamped> DETAILS = new MessageDetails<QuaternionStamped>(Name.parseMessageName("geometry_msgs/QuaternionStamped"), "e57f1e547e0e1fd13504588ffc8334e2", "QuaternionStamped message", (QuaternionStamped.class));
    /**
     * 
     * 
     */
    public Header header = new Header();
    /**
     * 
     * 
     */
    public Quaternion quaternion = new Quaternion();

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += quaternion.getLength();
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
        quaternion.serialize(dataWriter);
    }

}
