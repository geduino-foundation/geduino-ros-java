
package org.geduino.ros.common.msgs.hydro.sensor_msgs;

import java.io.IOException;
import org.geduino.ros.common.msgs.hydro.geometry_msgs.Quaternion;
import org.geduino.ros.common.msgs.hydro.std_msgs.Header;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   This is a message to hold data from an IMU (Inertial Measurement Unit)   Accelerations should be in m/s^2 (not in g's), and rotational velocity should be in rad/sec   If the covariance of the measurement is known, it should be filled in (if all you know is the  variance of each measurement, e.g. from the datasheet, just put those along the diagonal)  A covariance matrix of all zeros will be interpreted as "covariance unknown", and to use the  data a covariance will have to be assumed or gotten from some other source   If you have no estimate for one of the data elements (e.g. your IMU doesn't produce an orientation  estimate), please set element 0 of the associated covariance matrix to -1  If you are interpreting this message, please check for a value of -1 in the first element of each  covariance matrix, and disregard the associated estimate.
 * 
 */
public class Imu
    extends Message
{

    public final static MessageDetails<Imu> DETAILS = new MessageDetails<Imu>(Name.parseMessageName("sensor_msgs/Imu"), "6a62c6daae103f4ff57a132d6f95cec2", "Imu message", (Imu.class));
    /**
     * 
     * 
     */
    public Header header = new Header();
    /**
     * 
     * 
     */
    public Quaternion orientation = new Quaternion();
    /**
     *  Row major about x, y, z axes
     * 
     */
    public double[] orientation_covariance = new double[] {};
    /**
     * 
     * 
     */
    public org.geduino.ros.common.msgs.hydro.geometry_msgs.Vector3 angular_velocity = new org.geduino.ros.common.msgs.hydro.geometry_msgs.Vector3();
    /**
     *  Row major about x, y, z axes
     * 
     */
    public double[] angular_velocity_covariance = new double[] {};
    /**
     * 
     * 
     */
    public org.geduino.ros.common.msgs.hydro.geometry_msgs.Vector3 linear_acceleration = new org.geduino.ros.common.msgs.hydro.geometry_msgs.Vector3();
    /**
     *  Row major x, y z
     * 
     */
    public double[] linear_acceleration_covariance = new double[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += orientation.getLength();
        length += 4;
        for (int index = 0; (index<orientation_covariance.length); index ++) {
            length += 8;
        }
        length += angular_velocity.getLength();
        length += 4;
        for (int index = 0; (index<angular_velocity_covariance.length); index ++) {
            length += 8;
        }
        length += linear_acceleration.getLength();
        length += 4;
        for (int index = 0; (index<linear_acceleration_covariance.length); index ++) {
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
        orientation.serialize(dataWriter);
        dataWriter.writeUInt32(orientation_covariance.length);
        for (int index = 0; (index<orientation_covariance.length); index ++) {
            dataWriter.writeFloat64(orientation_covariance[index]);
        }
        angular_velocity.serialize(dataWriter);
        dataWriter.writeUInt32(angular_velocity_covariance.length);
        for (int index = 0; (index<angular_velocity_covariance.length); index ++) {
            dataWriter.writeFloat64(angular_velocity_covariance[index]);
        }
        linear_acceleration.serialize(dataWriter);
        dataWriter.writeUInt32(linear_acceleration_covariance.length);
        for (int index = 0; (index<linear_acceleration_covariance.length); index ++) {
            dataWriter.writeFloat64(linear_acceleration_covariance[index]);
        }
    }

}
