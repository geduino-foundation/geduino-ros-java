
package org.geduino.ros.common.msgs.hydro.geometry_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   This expresses velocity in free space with uncertainty.
 * 
 */
public class TwistWithCovariance
    extends Message
{

    public final static MessageDetails<TwistWithCovariance> DETAILS = new MessageDetails<TwistWithCovariance>(Name.parseMessageName("geometry_msgs/TwistWithCovariance"), "1fe8a28e6890a4cc3ae4c3ca5c7d82e6", "TwistWithCovariance message", (TwistWithCovariance.class));
    /**
     *  Row-major representation of the 6x6 covariance matrix# The orientation parameters use a fixed-axis representation.# In order, the parameters are:# (x, y, z, rotation about X axis, rotation about Y axis, rotation about Z axis)
     * 
     */
    public Twist twist = new Twist();
    /**
     * 
     * 
     */
    public double[] covariance = new double[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += twist.getLength();
        length += 4;
        for (int index = 0; (index<covariance.length); index ++) {
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
        twist.serialize(dataWriter);
        dataWriter.writeUInt32(covariance.length);
        for (int index = 0; (index<covariance.length); index ++) {
            dataWriter.writeFloat64(covariance[index]);
        }
    }

}
