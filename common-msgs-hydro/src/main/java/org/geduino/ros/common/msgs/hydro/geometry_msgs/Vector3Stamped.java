
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
 *   This represents a Vector3 with reference coordinate frame and timestamp
 * 
 */
public class Vector3Stamped
    extends Message
{

    public final static MessageDetails<Vector3Stamped> DETAILS = new MessageDetails<Vector3Stamped>(Name.parseMessageName("geometry_msgs/Vector3Stamped"), "7b324c7325e683bf02a9b14b01090ec7", "Vector3Stamped message", (Vector3Stamped.class));
    /**
     * 
     * 
     */
    public Header header = new Header();
    /**
     * 
     * 
     */
    public Vector3 vector = new Vector3();

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += vector.getLength();
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
        vector.serialize(dataWriter);
    }

}
