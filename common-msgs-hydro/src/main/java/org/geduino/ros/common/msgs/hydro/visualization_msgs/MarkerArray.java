
package org.geduino.ros.common.msgs.hydro.visualization_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 * 
 * 
 */
public class MarkerArray
    extends Message
{

    public final static MessageDetails<MarkerArray> DETAILS = new MessageDetails<MarkerArray>(Name.parseMessageName("visualization_msgs/MarkerArray"), "90da67007c26525f655c1c269094e39f", "MarkerArray message", (MarkerArray.class));
    /**
     * 
     * 
     */
    public Marker[] markers = new Marker[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += 4;
        for (int index = 0; (index<markers.length); index ++) {
            length += markers[index].getLength();
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
        dataWriter.writeUInt32(markers.length);
        for (int index = 0; (index<markers.length); index ++) {
            markers[index].serialize(dataWriter);
        }
    }

}
