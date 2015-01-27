
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
 *   This message contains an uncompressed image  (0, 0) is at top-left corner of image 
 * 
 */
public class Image
    extends Message
{

    public final static MessageDetails<Image> DETAILS = new MessageDetails<Image>(Name.parseMessageName("sensor_msgs/Image"), "060021388200f6f0f447d0fcd9c64743", "Image message", (Image.class));
    /**
     *  Header timestamp should be acquisition time of image# Header frame_id should be optical frame of camera# origin of frame should be optical center of cameara# +x should point to the right in the image# +y should point down in the image# +z should point into to plane of the image# If the frame_id here and the frame_id of the CameraInfo# message associated with the image conflict# the behavior is undefined
     * 
     */
    public Header header = new Header();
    /**
     *  image height, that is, number of rows
     * 
     */
    public long height = 0L;
    /**
     *  image width, that is, number of columns# The legal values for encoding are in file src/image_encodings.cpp# If you want to standardize a new string format, join# ros-users@lists.sourceforge.net and send an email proposing a new encoding.
     * 
     */
    public long width = 0L;
    /**
     *  Encoding of pixels -- channel meaning, ordering, size# taken from the list of strings in include/sensor_msgs/image_encodings.h
     * 
     */
    public String encoding = "";
    /**
     *  is this data bigendian?
     * 
     */
    public int is_bigendian = 0;
    /**
     *  Full row length in bytes
     * 
     */
    public long step = 0L;
    /**
     *  actual matrix data, size is (step * rows)
     * 
     */
    public int[] data = new int[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += 4;
        length += 4;
        length += (encoding.length()+ 4);
        length += 1;
        length += 4;
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
        dataWriter.writeUInt32(height);
        dataWriter.writeUInt32(width);
        dataWriter.writeUInt32(encoding.length());
        dataWriter.writeString(encoding);
        dataWriter.writeUInt8(is_bigendian);
        dataWriter.writeUInt32(step);
        dataWriter.writeUInt32(data.length);
        for (int index = 0; (index<data.length); index ++) {
            dataWriter.writeUInt8(data[index]);
        }
    }

}
