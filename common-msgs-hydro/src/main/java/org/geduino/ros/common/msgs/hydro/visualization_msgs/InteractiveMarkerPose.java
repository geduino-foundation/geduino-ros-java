
package org.geduino.ros.common.msgs.hydro.visualization_msgs;

import java.io.IOException;
import org.geduino.ros.common.msgs.hydro.geometry_msgs.Pose;
import org.geduino.ros.common.msgs.hydro.std_msgs.Header;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   Time/frame info.
 * 
 */
public class InteractiveMarkerPose
    extends Message
{

    public final static MessageDetails<InteractiveMarkerPose> DETAILS = new MessageDetails<InteractiveMarkerPose>(Name.parseMessageName("visualization_msgs/InteractiveMarkerPose"), "a6e6833209a196a38d798dadb02c81f8", "InteractiveMarkerPose message", (InteractiveMarkerPose.class));
    /**
     *  Initial pose. Also, defines the pivot point for rotations.
     * 
     */
    public Header header = new Header();
    /**
     *  Identifying string. Must be globally unique in# the topic that this message is sent through.
     * 
     */
    public Pose pose = new Pose();
    /**
     * 
     * 
     */
    public String name = "";

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += pose.getLength();
        length += (name.length()+ 4);
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
        dataWriter.writeUInt32(name.length());
        dataWriter.writeString(name);
    }

}
