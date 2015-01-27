
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
 *   This message contains a compressed image
 * 
 */
public class CompressedImage
    extends Message
{

    public final static MessageDetails<CompressedImage> DETAILS = new MessageDetails<CompressedImage>(Name.parseMessageName("sensor_msgs/CompressedImage"), "8f7a12909da2c9d3332d540a0977563f", "CompressedImage message", (CompressedImage.class));
    /**
     *  Header timestamp should be acquisition time of image# Header frame_id should be optical frame of camera# origin of frame should be optical center of cameara# +x should point to the right in the image# +y should point down in the image# +z should point into to plane of the image
     * 
     */
    public Header header = new Header();
    /**
     *  Specifies the format of the data#   Acceptable values:#     jpeg, png
     * 
     */
    public String format = "";
    /**
     *  Compressed image buffer
     * 
     */
    public int[] data = new int[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += (format.length()+ 4);
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
        dataWriter.writeUInt32(format.length());
        dataWriter.writeString(format);
        dataWriter.writeUInt32(data.length);
        for (int index = 0; (index<data.length); index ++) {
            dataWriter.writeUInt8(data[index]);
        }
    }

}
