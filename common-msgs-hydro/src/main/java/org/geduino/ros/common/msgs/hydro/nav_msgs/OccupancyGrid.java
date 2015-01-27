
package org.geduino.ros.common.msgs.hydro.nav_msgs;

import java.io.IOException;
import org.geduino.ros.common.msgs.hydro.std_msgs.Header;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   This represents a 2-D grid map, in which each cell represents the probability of  occupancy.
 * 
 */
public class OccupancyGrid
    extends Message
{

    public final static MessageDetails<OccupancyGrid> DETAILS = new MessageDetails<OccupancyGrid>(Name.parseMessageName("nav_msgs/OccupancyGrid"), "3381f2d731d4076ec5c71b0759edbe4e", "OccupancyGrid message", (OccupancyGrid.class));
    /**
     * MetaData for the map
     * 
     */
    public Header header = new Header();
    /**
     *  The map data, in row-major order, starting with (0,0).  Occupancy# probabilities are in the range [0,100].  Unknown is -1.
     * 
     */
    public MapMetaData info = new MapMetaData();
    /**
     * 
     * 
     */
    public int[] data = new int[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += info.getLength();
        length += 4;
        for (int index = 0; (index<data.length); index ++) {
            length += 1;
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
        info.serialize(dataWriter);
        dataWriter.writeUInt32(data.length);
        for (int index = 0; (index<data.length); index ++) {
            dataWriter.writeInt8(data[index]);
        }
    }

}
