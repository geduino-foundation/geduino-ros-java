
package org.geduino.ros.common.msgs.hydro.nav_msgs;

import java.io.IOException;
import org.geduino.ros.common.msgs.hydro.geometry_msgs.Point;
import org.geduino.ros.common.msgs.hydro.std_msgs.Header;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *  an array of cells in a 2D grid
 * 
 */
public class GridCells
    extends Message
{

    public final static MessageDetails<GridCells> DETAILS = new MessageDetails<GridCells>(Name.parseMessageName("nav_msgs/GridCells"), "b9e4f5df6d28e272ebde00a3994830f5", "GridCells message", (GridCells.class));
    /**
     * 
     * 
     */
    public Header header = new Header();
    /**
     * 
     * 
     */
    public float cell_width = 0.0F;
    /**
     * 
     * 
     */
    public float cell_height = 0.0F;
    /**
     * 
     * 
     */
    public Point[] cells = new Point[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += 4;
        length += 4;
        length += 4;
        for (int index = 0; (index<cells.length); index ++) {
            length += cells[index].getLength();
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
        dataWriter.writeFloat32(cell_width);
        dataWriter.writeFloat32(cell_height);
        dataWriter.writeUInt32(cells.length);
        for (int index = 0; (index<cells.length); index ++) {
            cells[index].serialize(dataWriter);
        }
    }

}
