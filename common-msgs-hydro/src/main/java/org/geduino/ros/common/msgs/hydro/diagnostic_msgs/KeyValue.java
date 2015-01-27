
package org.geduino.ros.common.msgs.hydro.diagnostic_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 * 
 * 
 */
public class KeyValue
    extends Message
{

    public final static MessageDetails<KeyValue> DETAILS = new MessageDetails<KeyValue>(Name.parseMessageName("diagnostic_msgs/KeyValue"), "cf57fdc6617a881a88c16e768132149c", "KeyValue message", (KeyValue.class));
    /**
     *  what to label this value when viewing
     * 
     */
    public String key = "";
    /**
     *  a value to track over time
     * 
     */
    public String value = "";

    @Override
    public long getLength() {
        long length = 0;
        length += (key.length()+ 4);
        length += (value.length()+ 4);
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
        dataWriter.writeUInt32(key.length());
        dataWriter.writeString(key);
        dataWriter.writeUInt32(value.length());
        dataWriter.writeString(value);
    }

}
