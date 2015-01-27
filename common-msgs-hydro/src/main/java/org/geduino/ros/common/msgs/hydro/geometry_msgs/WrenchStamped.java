
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
 *   A wrench with reference coordinate frame and timestamp
 * 
 */
public class WrenchStamped
    extends Message
{

    public final static MessageDetails<WrenchStamped> DETAILS = new MessageDetails<WrenchStamped>(Name.parseMessageName("geometry_msgs/WrenchStamped"), "d78d3cb249ce23087ade7e7d0c40cfa7", "WrenchStamped message", (WrenchStamped.class));
    /**
     * 
     * 
     */
    public Header header = new Header();
    /**
     * 
     * 
     */
    public Wrench wrench = new Wrench();

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += wrench.getLength();
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
        wrench.serialize(dataWriter);
    }

}
