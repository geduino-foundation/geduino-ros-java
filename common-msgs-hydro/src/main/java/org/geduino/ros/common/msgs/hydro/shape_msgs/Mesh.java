
package org.geduino.ros.common.msgs.hydro.shape_msgs;

import java.io.IOException;
import org.geduino.ros.common.msgs.hydro.geometry_msgs.Point;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   Definition of a mesh  list of triangles; the index values refer to positions in vertices[]
 * 
 */
public class Mesh
    extends Message
{

    public final static MessageDetails<Mesh> DETAILS = new MessageDetails<Mesh>(Name.parseMessageName("shape_msgs/Mesh"), "1ffdae9486cd3316a121c578b47a85cc", "Mesh message", (Mesh.class));
    /**
     *  the actual vertices that make up the mesh
     * 
     */
    public MeshTriangle[] triangles = new MeshTriangle[] {};
    /**
     * 
     * 
     */
    public Point[] vertices = new Point[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += 4;
        for (int index = 0; (index<triangles.length); index ++) {
            length += triangles[index].getLength();
        }
        length += 4;
        for (int index = 0; (index<vertices.length); index ++) {
            length += vertices[index].getLength();
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
        dataWriter.writeUInt32(triangles.length);
        for (int index = 0; (index<triangles.length); index ++) {
            triangles[index].serialize(dataWriter);
        }
        dataWriter.writeUInt32(vertices.length);
        for (int index = 0; (index<vertices.length); index ++) {
            vertices[index].serialize(dataWriter);
        }
    }

}
