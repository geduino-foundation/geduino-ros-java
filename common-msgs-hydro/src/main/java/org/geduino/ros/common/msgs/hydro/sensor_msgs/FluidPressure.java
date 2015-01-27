
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
 *   Single pressure reading.  This message is appropriate for measuring the  pressure inside of a fluid (air, water, etc).  This also includes  atmospheric or barometric pressure.  This message is not appropriate for force/pressure contact sensors.
 * 
 */
public class FluidPressure
    extends Message
{

    public final static MessageDetails<FluidPressure> DETAILS = new MessageDetails<FluidPressure>(Name.parseMessageName("sensor_msgs/FluidPressure"), "804dc5cea1c5306d6a2eb80b9833befe", "FluidPressure message", (FluidPressure.class));
    /**
     *  timestamp of the measurement# frame_id is the location of the pressure sensor
     * 
     */
    public Header header = new Header();
    /**
     *  Absolute pressure reading in Pascals.
     * 
     */
    public double fluid_pressure = 0.0D;
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
        dataWriter.writeFloat64(fluid_pressure);
        dataWriter.writeFloat64(variance);
    }

}
