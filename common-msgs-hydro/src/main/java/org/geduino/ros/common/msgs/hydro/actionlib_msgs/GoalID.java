
package org.geduino.ros.common.msgs.hydro.actionlib_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   The stamp should store the time at which this goal was requested.  It is used by an action server when it tries to preempt all  goals that were requested before a certain time
 * 
 */
public class GoalID
    extends Message
{

    public final static MessageDetails<GoalID> DETAILS = new MessageDetails<GoalID>(Name.parseMessageName("actionlib_msgs/GoalID"), "302881f31927c1df708a2dbab0e80ee8", "GoalID message", (GoalID.class));
    /**
     *  The id provides a way to associate feedback and# result message with specific goal requests. The id# specified must be unique.
     * 
     */
    public long stamp = 0L;
    /**
     * 
     * 
     */
    public String id = "";

    @Override
    public long getLength() {
        long length = 0;
        length += 8;
        length += (id.length()+ 4);
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
        dataWriter.writeTime(stamp);
        dataWriter.writeUInt32(id.length());
        dataWriter.writeString(id);
    }

}
