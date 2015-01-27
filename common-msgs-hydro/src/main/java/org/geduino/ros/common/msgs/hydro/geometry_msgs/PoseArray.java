
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
 *   An array of poses with a header for global reference.
 * 
 */
public class PoseArray
    extends Message
{

    public final static MessageDetails<PoseArray> DETAILS = new MessageDetails<PoseArray>(Name.parseMessageName("geometry_msgs/PoseArray"), "916c28c5764443f268b296bb671b9d97", "PoseArray message", (PoseArray.class));
    /**
     * 
     * 
     */
    public Header header = new Header();
    /**
     * 
     * 
     */
    public Pose[] poses = new Pose[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += 4;
        for (int index = 0; (index<poses.length); index ++) {
            length += poses[index].getLength();
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
        header.serialize(dataWriter);
        dataWriter.writeUInt32(poses.length);
        for (int index = 0; (index<poses.length); index ++) {
            poses[index].serialize(dataWriter);
        }
    }

}
