
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
 *   Single reading from a relative humidity sensor.  Defines the ratio of partial  pressure of water vapor to the saturated vapor pressure at a temperature.
 * 
 */
public class RelativeHumidity
    extends Message
{

    public final static MessageDetails<RelativeHumidity> DETAILS = new MessageDetails<RelativeHumidity>(Name.parseMessageName("sensor_msgs/RelativeHumidity"), "8730015b05955b7e992ce29a2678d90f", "RelativeHumidity message", (RelativeHumidity.class));
    /**
     *  timestamp of the measurement# frame_id is the location of the humidity sensor
     * 
     */
    public Header header = new Header();
    /**
     *  Expression of the relative humidity# from 0.0 to 1.0.# 0.0 is no partial pressure of water vapor# 1.0 represents partial pressure of saturation
     * 
     */
    public double relative_humidity = 0.0D;
    /**
     *  0 is interpreted as variance unknown
     * 
     */
    public double variance = 0.0D;

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += 8;
        length += 8;
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
        dataWriter.writeFloat64(relative_humidity);
        dataWriter.writeFloat64(variance);
    }

}
