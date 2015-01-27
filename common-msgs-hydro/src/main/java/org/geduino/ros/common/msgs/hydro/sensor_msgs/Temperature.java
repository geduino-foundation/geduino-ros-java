
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
 *   Single temperature reading.
 * 
 */
public class Temperature
    extends Message
{

    public final static MessageDetails<Temperature> DETAILS = new MessageDetails<Temperature>(Name.parseMessageName("sensor_msgs/Temperature"), "ff71b307acdbe7c871a5a6d7ed359100", "Temperature message", (Temperature.class));
    /**
     *  timestamp is the time the temperature was measured# frame_id is the location of the temperature reading
     * 
     */
    public Header header = new Header();
    /**
     *  Measurement of the Temperature in Degrees Celsius
     * 
     */
    public double temperature = 0.0D;
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
        dataWriter.writeFloat64(temperature);
        dataWriter.writeFloat64(variance);
    }

}
