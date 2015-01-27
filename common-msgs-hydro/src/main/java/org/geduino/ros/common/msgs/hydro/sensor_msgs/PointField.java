
package org.geduino.ros.common.msgs.hydro.sensor_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   This message holds the description of one point entry in the  PointCloud2 message format.
 * 
 */
public class PointField
    extends Message
{

    public final static MessageDetails<PointField> DETAILS = new MessageDetails<PointField>(Name.parseMessageName("sensor_msgs/PointField"), "268eacb2962780ceac86cbd17e328150", "PointField message", (PointField.class));
    /**
     * 
     * 
     */
    public final static int INT8 = (1);
    /**
     * 
     * 
     */
    public final static int UINT8 = (2);
    /**
     * 
     * 
     */
    public final static int INT16 = (3);
    /**
     * 
     * 
     */
    public final static int UINT16 = (4);
    /**
     * 
     * 
     */
    public final static int INT32 = (5);
    /**
     * 
     * 
     */
    public final static int UINT32 = (6);
    /**
     * 
     * 
     */
    public final static int FLOAT32 = (7);
    /**
     * 
     * 
     */
    public final static int FLOAT64 = (8);
    /**
     *  Name of field
     * 
     */
    public String name = "";
    /**
     *  Offset from start of point struct
     * 
     */
    public long offset = 0L;
    /**
     *  Datatype enumeration, see above
     * 
     */
    public int datatype = 0;
    /**
     *  How many elements in the field
     * 
     */
    public long count = 0L;

    @Override
    public long getLength() {
        long length = 0;
        length += (name.length()+ 4);
        length += 4;
        length += 1;
        length += 4;
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
        dataWriter.writeUInt32(name.length());
        dataWriter.writeString(name);
        dataWriter.writeUInt32(offset);
        dataWriter.writeUInt8(datatype);
        dataWriter.writeUInt32(count);
    }

}
