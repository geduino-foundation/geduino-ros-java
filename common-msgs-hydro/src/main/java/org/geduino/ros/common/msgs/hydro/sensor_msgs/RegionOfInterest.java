
package org.geduino.ros.common.msgs.hydro.sensor_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   This message is used to specify a region of interest within an image.   When used to specify the ROI setting of the camera when the image was  taken, the height and width fields should either match the height and  width fields for the associated image; or height = width = 0  indicates that the full resolution image was captured.
 * 
 */
public class RegionOfInterest
    extends Message
{

    public final static MessageDetails<RegionOfInterest> DETAILS = new MessageDetails<RegionOfInterest>(Name.parseMessageName("sensor_msgs/RegionOfInterest"), "bdb633039d588fcccb441a4d43ccfe09", "RegionOfInterest message", (RegionOfInterest.class));
    /**
     *  Leftmost pixel of the ROI# (0 if the ROI includes the left edge of the image)
     * 
     */
    public long x_offset = 0L;
    /**
     *  Topmost pixel of the ROI# (0 if the ROI includes the top edge of the image)
     * 
     */
    public long y_offset = 0L;
    /**
     *  Height of ROI
     * 
     */
    public long height = 0L;
    /**
     *  Width of ROI# True if a distinct rectified ROI should be calculated from the "raw"# ROI in this message. Typically this should be False if the full image# is captured (ROI not used), and True if a subwindow is captured (ROI# used).
     * 
     */
    public long width = 0L;
    /**
     * 
     * 
     */
    public boolean do_rectify = false;

    @Override
    public long getLength() {
        long length = 0;
        length += 4;
        length += 4;
        length += 4;
        length += 4;
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
        dataWriter.writeUInt32(x_offset);
        dataWriter.writeUInt32(y_offset);
        dataWriter.writeUInt32(height);
        dataWriter.writeUInt32(width);
        dataWriter.writeBool(do_rectify);
    }

}
