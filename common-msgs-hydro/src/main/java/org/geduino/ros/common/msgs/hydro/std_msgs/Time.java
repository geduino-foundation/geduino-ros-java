
package org.geduino.ros.common.msgs.hydro.std_msgs;

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
public class Time
    extends Message
{

    public final static MessageDetails<Time> DETAILS = new MessageDetails<Time>(Name.parseMessageName("std_msgs/Time"), "cd7166c74c552c311fbcc2fe5a7bc289", "Time message", (Time.class));
    /**
     * 
     * 
     */
    public long data = 0L;

    @Override
    public long getLength() {
        long length = 0;
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
        dataWriter.writeTime(data);
    }

}
