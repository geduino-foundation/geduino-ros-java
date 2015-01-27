
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
public class Duration
    extends Message
{

    public final static MessageDetails<Duration> DETAILS = new MessageDetails<Duration>(Name.parseMessageName("std_msgs/Duration"), "3e286caf4241d664e55f3ad380e2ae46", "Duration message", (Duration.class));
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
        dataWriter.writeDuration(data);
    }

}
