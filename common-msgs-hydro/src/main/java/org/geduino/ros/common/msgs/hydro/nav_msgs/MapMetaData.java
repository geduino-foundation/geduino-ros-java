
package org.geduino.ros.common.msgs.hydro.nav_msgs;

import java.io.IOException;
import org.geduino.ros.common.msgs.hydro.geometry_msgs.Pose;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   This hold basic information about the characterists of the OccupancyGrid  The time at which the map was loaded
 * 
 */
public class MapMetaData
    extends Message
{

    public final static MessageDetails<MapMetaData> DETAILS = new MessageDetails<MapMetaData>(Name.parseMessageName("nav_msgs/MapMetaData"), "10cfc8a2818024d3248802c00c95f11b", "MapMetaData message", (MapMetaData.class));
    /**
     *  The map resolution [m/cell]
     * 
     */
    public long map_load_time = 0L;
    /**
     *  Map width [cells]
     * 
     */
    public float resolution = 0.0F;
    /**
     *  Map height [cells]
     * 
     */
    public long width = 0L;
    /**
     *  The origin of the map [m, m, rad].  This is the real-world pose of the# cell (0,0) in the map.
     * 
     */
    public long height = 0L;
    /**
     * 
     * 
     */
    public Pose origin = new Pose();

    @Override
    public long getLength() {
        long length = 0;
        length += 8;
        length += 4;
        length += 4;
        length += 4;
        length += origin.getLength();
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
        dataWriter.writeTime(map_load_time);
        dataWriter.writeFloat32(resolution);
        dataWriter.writeUInt32(width);
        dataWriter.writeUInt32(height);
        origin.serialize(dataWriter);
    }

}
