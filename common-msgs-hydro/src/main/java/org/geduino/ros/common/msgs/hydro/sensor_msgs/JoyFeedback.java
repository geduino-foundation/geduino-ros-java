
package org.geduino.ros.common.msgs.hydro.sensor_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   Declare of the type of feedback
 * 
 */
public class JoyFeedback
    extends Message
{

    public final static MessageDetails<JoyFeedback> DETAILS = new MessageDetails<JoyFeedback>(Name.parseMessageName("sensor_msgs/JoyFeedback"), "f4dcd73460360d98f36e55ee7f2e46f1", "JoyFeedback message", (JoyFeedback.class));
    /**
     * 
     * 
     */
    public final static int TYPE_LED = (0);
    /**
     * 
     * 
     */
    public final static int TYPE_RUMBLE = (1);
    /**
     * 
     * 
     */
    public final static int TYPE_BUZZER = (2);
    /**
     *  This will hold an id number for each type of each feedback.# Example, the first led would be id=0, the second would be id=1
     * 
     */
    public int type = 0;
    /**
     *  Intensity of the feedback, from 0.0 to 1.0, inclusive.  If device is# actually binary, driver should treat 0<=x<0.5 as off, 0.5<=x<=1 as on.
     * 
     */
    public int id = 0;
    /**
     * 
     * 
     */
    public float intensity = 0.0F;

    @Override
    public long getLength() {
        long length = 0;
        length += 1;
        length += 1;
        length += 4;
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
        dataWriter.writeUInt8(type);
        dataWriter.writeUInt8(id);
        dataWriter.writeFloat32(intensity);
    }

}
