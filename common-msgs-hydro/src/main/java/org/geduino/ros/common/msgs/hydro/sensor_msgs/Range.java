
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
 *   Single range reading from an active ranger that emits energy and reports  one range reading that is valid along an arc at the distance measured.  This message is  not appropriate for laser scanners. See the LaserScan  message if you are working with a laser scanner.  This message also can represent a fixed-distance (binary) ranger.  This  sensor will have min_range===max_range===distance of detection.  These sensors follow REP 117 and will output -Inf if the object is detected  and +Inf if the object is outside of the detection range.
 * 
 */
public class Range
    extends Message
{

    public final static MessageDetails<Range> DETAILS = new MessageDetails<Range>(Name.parseMessageName("sensor_msgs/Range"), "c005c34273dc426c67a020a87bc24148", "Range message", (Range.class));
    /**
     *  timestamp in the header is the time the ranger# returned the distance reading# Radiation type enums# If you want a value added to this list, send an email to the ros-users list
     * 
     */
    public Header header = new Header();
    /**
     * 
     * 
     */
    public final static int ULTRASOUND = (0);
    /**
     * 
     * 
     */
    public final static int INFRARED = (1);
    /**
     *  the type of radiation used by the sensor# (sound, IR, etc) [enum]
     * 
     */
    public int radiation_type = 0;
    /**
     *  the size of the arc that the distance reading is# valid for [rad]# the object causing the range reading may have# been anywhere within -field_of_view/2 and# field_of_view/2 at the measured range.# 0 angle corresponds to the x-axis of the sensor.
     * 
     */
    public float field_of_view = 0.0F;
    /**
     *  minimum range value [m]
     * 
     */
    public float min_range = 0.0F;
    /**
     *  maximum range value [m]# Fixed distance rangers require min_range==max_range
     * 
     */
    public float max_range = 0.0F;
    /**
     *  range data [m]# (Note: values < range_min or > range_max# should be discarded)# Fixed distance rangers only output -Inf or +Inf.# -Inf represents a detection within fixed distance.# (Detection too close to the sensor to quantify)# +Inf represents no detection within the fixed distance.# (Object out of range)
     * 
     */
    public float range = 0.0F;

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += 1;
        length += 4;
        length += 4;
        length += 4;
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
        header.serialize(dataWriter);
        dataWriter.writeUInt8(radiation_type);
        dataWriter.writeFloat32(field_of_view);
        dataWriter.writeFloat32(min_range);
        dataWriter.writeFloat32(max_range);
        dataWriter.writeFloat32(range);
    }

}
