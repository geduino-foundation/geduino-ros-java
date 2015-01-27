
package org.geduino.ros.common.msgs.hydro.nav_msgs;

import java.io.IOException;
import org.geduino.ros.common.msgs.hydro.geometry_msgs.PoseStamped;
import org.geduino.ros.common.msgs.hydro.std_msgs.Header;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *  An array of poses that represents a Path for a robot to follow
 * 
 */
public class Path
    extends Message
{

    public final static MessageDetails<Path> DETAILS = new MessageDetails<Path>(Name.parseMessageName("nav_msgs/Path"), "6227e2b7e9cce15051f669a5e197bbf7", "Path message", (Path.class));
    /**
     * 
     * 
     */
    public Header header = new Header();
    /**
     * 
     * 
     */
    public PoseStamped[] poses = new PoseStamped[] {};

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
