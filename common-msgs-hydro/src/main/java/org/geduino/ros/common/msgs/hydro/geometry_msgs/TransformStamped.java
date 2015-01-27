
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
 *   This expresses a transform from coordinate frame header.frame_id  to the coordinate frame child_frame_id   This message is mostly used by the  <a href="http://www.ros.org/wiki/tf">tf</a> package.  See its documentation for more information.
 * 
 */
public class TransformStamped
    extends Message
{

    public final static MessageDetails<TransformStamped> DETAILS = new MessageDetails<TransformStamped>(Name.parseMessageName("geometry_msgs/TransformStamped"), "b5764a33bfeb3588febc2682852579b0", "TransformStamped message", (TransformStamped.class));
    /**
     * 
     * 
     */
    public Header header = new Header();
    /**
     *  the frame id of the child frame
     * 
     */
    public String child_frame_id = "";
    /**
     * 
     * 
     */
    public Transform transform = new Transform();

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += (child_frame_id.length()+ 4);
        length += transform.getLength();
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
        dataWriter.writeUInt32(child_frame_id.length());
        dataWriter.writeString(child_frame_id);
        transform.serialize(dataWriter);
    }

}
