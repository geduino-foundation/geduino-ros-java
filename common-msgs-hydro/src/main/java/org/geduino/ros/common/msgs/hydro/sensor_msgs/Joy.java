
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
 *   Reports the state of a joysticks axes and buttons.
 * 
 */
public class Joy
    extends Message
{

    public final static MessageDetails<Joy> DETAILS = new MessageDetails<Joy>(Name.parseMessageName("sensor_msgs/Joy"), "5a9ea5f83505693b71e785041e67a8bb", "Joy message", (Joy.class));
    /**
     *  timestamp in the header is the time the data is received from the joystick
     * 
     */
    public Header header = new Header();
    /**
     *  the axes measurements from a joystick
     * 
     */
    public float[] axes = new float[] {};
    /**
     *  the buttons measurements from a joystick
     * 
     */
    public int[] buttons = new int[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += 4;
        for (int index = 0; (index<axes.length); index ++) {
            length += 4;
        }
        length += 4;
        for (int index = 0; (index<buttons.length); index ++) {
            length += 4;
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
        dataWriter.writeUInt32(axes.length);
        for (int index = 0; (index<axes.length); index ++) {
            dataWriter.writeFloat32(axes[index]);
        }
        dataWriter.writeUInt32(buttons.length);
        for (int index = 0; (index<buttons.length); index ++) {
            dataWriter.writeInt32(buttons[index]);
        }
    }

}
