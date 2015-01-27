
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
 *   Single scan from a multi-echo planar laser range-finder   If you have another ranging device with different behavior (e.g. a sonar  array), please find or create a different message, since applications  will make fairly laser-specific assumptions about this data
 * 
 */
public class MultiEchoLaserScan
    extends Message
{

    public final static MessageDetails<MultiEchoLaserScan> DETAILS = new MessageDetails<MultiEchoLaserScan>(Name.parseMessageName("sensor_msgs/MultiEchoLaserScan"), "6fefb0c6da89d7c8abe4b339f5c2f8fb", "MultiEchoLaserScan message", (MultiEchoLaserScan.class));
    /**
     *  timestamp in the header is the acquisition time of# the first ray in the scan.## in frame frame_id, angles are measured around# the positive Z axis (counterclockwise, if Z is up)# with zero angle being forward along the x axis
     * 
     */
    public Header header = new Header();
    /**
     *  start angle of the scan [rad]
     * 
     */
    public float angle_min = 0.0F;
    /**
     *  end angle of the scan [rad]
     * 
     */
    public float angle_max = 0.0F;
    /**
     *  angular distance between measurements [rad]
     * 
     */
    public float angle_increment = 0.0F;
    /**
     *  time between measurements [seconds] - if your scanner# is moving, this will be used in interpolating position# of 3d points
     * 
     */
    public float time_increment = 0.0F;
    /**
     *  time between scans [seconds]
     * 
     */
    public float scan_time = 0.0F;
    /**
     *  minimum range value [m]
     * 
     */
    public float range_min = 0.0F;
    /**
     *  maximum range value [m]
     * 
     */
    public float range_max = 0.0F;
    /**
     *  range data [m] (Note: NaNs, values < range_min or > range_max should be discarded)# +Inf measurements are out of range# -Inf measurements are too close to determine exact distance.
     * 
     */
    public org.geduino.ros.common.msgs.hydro.sensor_msgs.LaserEcho[] ranges = new org.geduino.ros.common.msgs.hydro.sensor_msgs.LaserEcho[] {};
    /**
     *  intensity data [device-specific units].  If your# device does not provide intensities, please leave# the array empty.
     * 
     */
    public org.geduino.ros.common.msgs.hydro.sensor_msgs.LaserEcho[] intensities = new org.geduino.ros.common.msgs.hydro.sensor_msgs.LaserEcho[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += 4;
        length += 4;
        length += 4;
        length += 4;
        length += 4;
        length += 4;
        length += 4;
        length += 4;
        for (int index = 0; (index<ranges.length); index ++) {
            length += ranges[index].getLength();
        }
        length += 4;
        for (int index = 0; (index<intensities.length); index ++) {
            length += intensities[index].getLength();
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
        dataWriter.writeFloat32(angle_min);
        dataWriter.writeFloat32(angle_max);
        dataWriter.writeFloat32(angle_increment);
        dataWriter.writeFloat32(time_increment);
        dataWriter.writeFloat32(scan_time);
        dataWriter.writeFloat32(range_min);
        dataWriter.writeFloat32(range_max);
        dataWriter.writeUInt32(ranges.length);
        for (int index = 0; (index<ranges.length); index ++) {
            ranges[index].serialize(dataWriter);
        }
        dataWriter.writeUInt32(intensities.length);
        for (int index = 0; (index<intensities.length); index ++) {
            intensities[index].serialize(dataWriter);
        }
    }

}
