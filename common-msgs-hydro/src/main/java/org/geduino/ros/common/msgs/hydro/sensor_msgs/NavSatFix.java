
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
 *   Navigation Satellite fix for any Global Navigation Satellite System   Specified using the WGS 84 reference ellipsoid  header.stamp specifies the ROS time for this measurement (the         corresponding satellite time may be reported using the         sensor_msgs/TimeReference message).   header.frame_id is the frame of reference reported by the satellite         receiver, usually the location of the antenna.  This is a         Euclidean frame relative to the vehicle, not a reference         ellipsoid.
 * 
 */
public class NavSatFix
    extends Message
{

    public final static MessageDetails<NavSatFix> DETAILS = new MessageDetails<NavSatFix>(Name.parseMessageName("sensor_msgs/NavSatFix"), "2d3a8cd499b9b4a0249fb98fd05cfa48", "NavSatFix message", (NavSatFix.class));
    /**
     *  satellite fix status information
     * 
     */
    public Header header = new Header();
    /**
     *  Latitude [degrees]. Positive is north of equator; negative is south.
     * 
     */
    public NavSatStatus status = new NavSatStatus();
    /**
     *  Longitude [degrees]. Positive is east of prime meridian; negative is west.
     * 
     */
    public double latitude = 0.0D;
    /**
     *  Altitude [m]. Positive is above the WGS 84 ellipsoid# (quiet NaN if no altitude is available).
     * 
     */
    public double longitude = 0.0D;
    /**
     *  Position covariance [m^2] defined relative to a tangential plane# through the reported position. The components are East, North, and# Up (ENU), in row-major order.## Beware: this coordinate system exhibits singularities at the poles.
     * 
     */
    public double altitude = 0.0D;
    /**
     *  If the covariance of the fix is known, fill it in completely. If the# GPS receiver provides the variance of each measurement, put them# along the diagonal. If only Dilution of Precision is available,# estimate an approximate covariance from that.
     * 
     */
    public double[] position_covariance = new double[] {};
    /**
     * 
     * 
     */
    public final static int COVARIANCE_TYPE_UNKNOWN = (0);
    /**
     * 
     * 
     */
    public final static int COVARIANCE_TYPE_APPROXIMATED = (1);
    /**
     * 
     * 
     */
    public final static int COVARIANCE_TYPE_DIAGONAL_KNOWN = (2);
    /**
     * 
     * 
     */
    public final static int COVARIANCE_TYPE_KNOWN = (3);
    /**
     * 
     * 
     */
    public int position_covariance_type = 0;

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += status.getLength();
        length += 8;
        length += 8;
        length += 8;
        length += 4;
        for (int index = 0; (index<position_covariance.length); index ++) {
            length += 8;
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
        status.serialize(dataWriter);
        dataWriter.writeFloat64(latitude);
        dataWriter.writeFloat64(longitude);
        dataWriter.writeFloat64(altitude);
        dataWriter.writeUInt32(position_covariance.length);
        for (int index = 0; (index<position_covariance.length); index ++) {
            dataWriter.writeFloat64(position_covariance[index]);
        }
        dataWriter.writeUInt8(position_covariance_type);
    }

}
