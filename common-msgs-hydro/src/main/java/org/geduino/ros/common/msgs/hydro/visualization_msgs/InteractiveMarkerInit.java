
package org.geduino.ros.common.msgs.hydro.visualization_msgs;

import java.io.IOException;
import java.math.BigInteger;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   Identifying string. Must be unique in the topic namespace  that this server works on.
 * 
 */
public class InteractiveMarkerInit
    extends Message
{

    public final static MessageDetails<InteractiveMarkerInit> DETAILS = new MessageDetails<InteractiveMarkerInit>(Name.parseMessageName("visualization_msgs/InteractiveMarkerInit"), "aa2f1dcea79533d1710675195653028d", "InteractiveMarkerInit message", (InteractiveMarkerInit.class));
    /**
     *  Sequence number.# The client will use this to detect if it has missed a subsequent# update.  Every update message will have the same sequence number as# an init message.  Clients will likely want to unsubscribe from the# init topic after a successful initialization to avoid receiving# duplicate data.
     * 
     */
    public String server_id = "";
    /**
     *  All markers.
     * 
     */
    public BigInteger seq_num = (BigInteger.ZERO);
    /**
     * 
     * 
     */
    public InteractiveMarker[] markers = new InteractiveMarker[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += (server_id.length()+ 4);
        length += 8;
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
        dataWriter.writeUInt32(server_id.length());
        dataWriter.writeString(server_id);
        dataWriter.writeUInt64(seq_num);
        dataWriter.writeUInt32(markers.length);
        for (int index = 0; (index<markers.length); index ++) {
            markers[index].serialize(dataWriter);
        }
    }

}
