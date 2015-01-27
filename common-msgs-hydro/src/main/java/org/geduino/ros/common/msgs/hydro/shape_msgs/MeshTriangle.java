
package org.geduino.ros.common.msgs.hydro.shape_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   Definition of a triangle's vertices
 * 
 */
public class MeshTriangle
    extends Message
{

    public final static MessageDetails<MeshTriangle> DETAILS = new MessageDetails<MeshTriangle>(Name.parseMessageName("shape_msgs/MeshTriangle"), "23688b2e6d2de3d32fe8af104a903253", "MeshTriangle message", (MeshTriangle.class));
    /**
     * 
     * 
     */
    public long[] vertex_indices = new long[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += 4;
        for (int index = 0; (index<vertex_indices.length); index ++) {
            length += 4;
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
        dataWriter.writeUInt32(vertex_indices.length);
        for (int index = 0; (index<vertex_indices.length); index ++) {
            dataWriter.writeUInt32(vertex_indices[index]);
        }
    }

}
