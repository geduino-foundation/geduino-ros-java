
package org.geduino.ros.common.msgs.hydro.sensor_msgs;

import java.io.IOException;
import org.geduino.ros.common.msgs.hydro.std_msgs.Header;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   This message holds a collection of N-dimensional points, which may  contain additional information such as normals, intensity, etc. The  point data is stored as a binary blob, its layout described by the  contents of the "fields" array.  The point cloud data may be organized 2d (image-like) or 1d  (unordered). Point clouds organized as 2d images may be produced by  camera depth sensors such as stereo or time-of-flight.  Time of sensor data acquisition, and the coordinate frame ID (for 3d  points).
 * 
 */
public class PointCloud2
    extends Message
{

    public final static MessageDetails<PointCloud2> DETAILS = new MessageDetails<PointCloud2>(Name.parseMessageName("sensor_msgs/PointCloud2"), "1158d486dd51d683ce2f1be655c3c181", "PointCloud2 message", (PointCloud2.class));
    /**
     *  2D structure of the point cloud. If the cloud is unordered, height is# 1 and width is the length of the point cloud.
     * 
     */
    public Header header = new Header();
    /**
     * 
     * 
     */
    public long height = 0L;
    /**
     *  Describes the channels and their layout in the binary data blob.
     * 
     */
    public long width = 0L;
    /**
     * 
     * 
     */
    public PointField[] fields = new PointField[] {};
    /**
     *  Is this data bigendian?
     * 
     */
    public boolean is_bigendian = false;
    /**
     *  Length of a point in bytes
     * 
     */
    public long point_step = 0L;
    /**
     *  Length of a row in bytes
     * 
     */
    public long row_step = 0L;
    /**
     *  Actual point data, size is (row_step*height)
     * 
     */
    public int[] data = new int[] {};
    /**
     *  True if there are no invalid points
     * 
     */
    public boolean is_dense = false;

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += 4;
        length += 4;
        length += 4;
        for (int index = 0; (index<fields.length); index ++) {
            length += fields[index].getLength();
        }
        length += 1;
        length += 4;
        length += 4;
        length += 4;
        for (int index = 0; (index<data.length); index ++) {
            length += 1;
        }
        length += 1;
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
        dataWriter.writeUInt32(height);
        dataWriter.writeUInt32(width);
        dataWriter.writeUInt32(fields.length);
        for (int index = 0; (index<fields.length); index ++) {
            fields[index].serialize(dataWriter);
        }
        dataWriter.writeBool(is_bigendian);
        dataWriter.writeUInt32(point_step);
        dataWriter.writeUInt32(row_step);
        dataWriter.writeUInt32(data.length);
        for (int index = 0; (index<data.length); index ++) {
            dataWriter.writeUInt8(data[index]);
        }
        dataWriter.writeBool(is_dense);
    }

}
