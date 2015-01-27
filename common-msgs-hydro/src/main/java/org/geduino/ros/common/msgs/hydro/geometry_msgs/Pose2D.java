
package org.geduino.ros.common.msgs.hydro.geometry_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   This expresses a position and orientation on a 2D manifold.
 * 
 */
public class Pose2D
    extends Message
{

    public final static MessageDetails<Pose2D> DETAILS = new MessageDetails<Pose2D>(Name.parseMessageName("geometry_msgs/Pose2D"), "938fa65709584ad8e77d238529be13b8", "Pose2D message", (Pose2D.class));
    /**
     * 
     * 
     */
    public double x = 0.0D;
    /**
     * 
     * 
     */
    public double y = 0.0D;
    /**
     * 
     * 
     */
    public double theta = 0.0D;

    @Override
    public long getLength() {
        long length = 0;
        length += 8;
        length += 8;
        length += 8;
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
        dataWriter.writeFloat64(x);
        dataWriter.writeFloat64(y);
        dataWriter.writeFloat64(theta);
    }

}
