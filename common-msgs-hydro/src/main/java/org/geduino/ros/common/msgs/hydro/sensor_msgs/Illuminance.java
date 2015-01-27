
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
 *   Single photometric illuminance measurement.  Light should be assumed to be  measured along the sensor's x-axis (the area of detection is the y-z plane).  The illuminance should have a 0 or positive value and be received with  the sensor's +X axis pointing toward the light source.  Photometric illuminance is the measure of the human eye's sensitivity of the  intensity of light encountering or passing through a surface.  All other Photometric and Radiometric measurements should  not use this message.  This message cannot represent:  Luminous intensity (candela/light source output)  Luminance (nits/light output per area)  Irradiance (watt/area), etc.
 * 
 */
public class Illuminance
    extends Message
{

    public final static MessageDetails<Illuminance> DETAILS = new MessageDetails<Illuminance>(Name.parseMessageName("sensor_msgs/Illuminance"), "8cf5febb0952fca9d650c3d11a81a188", "Illuminance message", (Illuminance.class));
    /**
     *  timestamp is the time the illuminance was measured# frame_id is the location and direction of the reading
     * 
     */
    public Header header = new Header();
    /**
     *  Measurement of the Photometric Illuminance in Lux.
     * 
     */
    public double illuminance = 0.0D;
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
        dataWriter.writeFloat64(illuminance);
        dataWriter.writeFloat64(variance);
    }

}
