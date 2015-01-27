
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
public class InteractiveMarkerUpdate
    extends Message
{

    public final static MessageDetails<InteractiveMarkerUpdate> DETAILS = new MessageDetails<InteractiveMarkerUpdate>(Name.parseMessageName("visualization_msgs/InteractiveMarkerUpdate"), "83e22f99d3b31fde725e0a338200e036", "InteractiveMarkerUpdate message", (InteractiveMarkerUpdate.class));
    /**
     *  Sequence number.# The client will use this to detect if it has missed an update.
     * 
     */
    public String server_id = "";
    /**
     *  Type holds the purpose of this message.  It must be one of UPDATE or KEEP_ALIVE.# UPDATE: Incremental update to previous state.#         The sequence number must be 1 higher than for#         the previous update.# KEEP_ALIVE: Indicates the that the server is still living.#             The sequence number does not increase.#             No payload data should be filled out (markers, poses, or erases).
     * 
     */
    public BigInteger seq_num = (BigInteger.ZERO);
    /**
     * 
     * 
     */
    public final static int KEEP_ALIVE = (0);
    /**
     * 
     * 
     */
    public final static int UPDATE = (1);
    /**
     * Note: No guarantees on the order of processing.#      Contents must be kept consistent by sender.#Markers to be added or updated
     * 
     */
    public int type = 0;
    /**
     * Poses of markers that should be moved
     * 
     */
    public InteractiveMarker[] markers = new InteractiveMarker[] {};
    /**
     * Names of markers to be erased
     * 
     */
    public InteractiveMarkerPose[] poses = new InteractiveMarkerPose[] {};
    /**
     * 
     * 
     */
    public String[] erases = new String[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += (server_id.length()+ 4);
        length += 8;
        length += 1;
        length += 4;
        for (int index = 0; (index<markers.length); index ++) {
            length += markers[index].getLength();
        }
        length += 4;
        for (int index = 0; (index<poses.length); index ++) {
            length += poses[index].getLength();
        }
        length += 4;
        for (int index = 0; (index<erases.length); index ++) {
            length += (erases[index].length()+ 4);
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
        dataWriter.writeUInt8(type);
        dataWriter.writeUInt32(markers.length);
        for (int index = 0; (index<markers.length); index ++) {
            markers[index].serialize(dataWriter);
        }
        dataWriter.writeUInt32(poses.length);
        for (int index = 0; (index<poses.length); index ++) {
            poses[index].serialize(dataWriter);
        }
        dataWriter.writeUInt32(erases.length);
        for (int index = 0; (index<erases.length); index ++) {
            dataWriter.writeUInt32(erases[index].length());
            dataWriter.writeString(erases[index]);
        }
    }

}
