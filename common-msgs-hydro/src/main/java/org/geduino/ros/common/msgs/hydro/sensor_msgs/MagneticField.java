
package org.geduino.ros.common.msgs.hydro.sensor_msgs;

import java.io.IOException;
import org.geduino.ros.common.msgs.hydro.geometry_msgs.Vector3;
import org.geduino.ros.common.msgs.hydro.std_msgs.Header;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   Measurement of the Magnetic Field vector at a specific location.  If the covariance of the measurement is known, it should be filled in  (if all you know is the variance of each measurement, e.g. from the datasheet, just put those along the diagonal)  A covariance matrix of all zeros will be interpreted as "covariance unknown",  and to use the data a covariance will have to be assumed or gotten from some  other source
 * 
 */
public class MagneticField
    extends Message
{

    public final static MessageDetails<MagneticField> DETAILS = new MessageDetails<MagneticField>(Name.parseMessageName("sensor_msgs/MagneticField"), "2f3b0b43eed0c9501de0fa3ff89a45aa", "MagneticField message", (MagneticField.class));
    /**
     *  timestamp is the time the# field was measured# frame_id is the location and orientation# of the field measurement
     * 
     */
    public Header header = new Header();
    /**
     *  x, y, and z components of the# field vector in Tesla# If your sensor does not output 3 axes,# put NaNs in the components not reported.
     * 
     */
    public Vector3 magnetic_field = new Vector3();
    /**
     *  Row major about x, y, z axes# 0 is interpreted as variance unknown
     * 
     */
    public double[] magnetic_field_covariance = new double[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += magnetic_field.getLength();
        length += 4;
        for (int index = 0; (index<magnetic_field_covariance.length); index ++) {
            length += 8;
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
        magnetic_field.serialize(dataWriter);
        dataWriter.writeUInt32(magnetic_field_covariance.length);
        for (int index = 0; (index<magnetic_field_covariance.length); index ++) {
            dataWriter.writeFloat64(magnetic_field_covariance[index]);
        }
    }

}
