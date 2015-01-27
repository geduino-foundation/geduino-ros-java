
package org.geduino.ros.common.msgs.hydro.sensor_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   This message publishes values for multiple feedback at once.
 * 
 */
public class JoyFeedbackArray
    extends Message
{

    public final static MessageDetails<JoyFeedbackArray> DETAILS = new MessageDetails<JoyFeedbackArray>(Name.parseMessageName("sensor_msgs/JoyFeedbackArray"), "cde5730a895b1fc4dee6f91b754b213d", "JoyFeedbackArray message", (JoyFeedbackArray.class));
    /**
     * 
     * 
     */
    public JoyFeedback[] array = new JoyFeedback[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += 4;
        for (int index = 0; (index<array.length); index ++) {
            length += array[index].getLength();
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
        dataWriter.writeUInt32(array.length);
        for (int index = 0; (index<array.length); index ++) {
            array[index].serialize(dataWriter);
        }
    }

}
