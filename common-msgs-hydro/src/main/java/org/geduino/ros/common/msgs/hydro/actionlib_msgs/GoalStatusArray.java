
package org.geduino.ros.common.msgs.hydro.actionlib_msgs;

import java.io.IOException;
import org.geduino.ros.common.msgs.hydro.std_msgs.Header;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   Stores the statuses for goals that are currently being tracked  by an action server
 * 
 */
public class GoalStatusArray
    extends Message
{

    public final static MessageDetails<GoalStatusArray> DETAILS = new MessageDetails<GoalStatusArray>(Name.parseMessageName("actionlib_msgs/GoalStatusArray"), "8b2b82f13216d0a8ea88bd3af735e619", "GoalStatusArray message", (GoalStatusArray.class));
    /**
     * 
     * 
     */
    public Header header = new Header();
    /**
     * 
     * 
     */
    public GoalStatus[] status_list = new GoalStatus[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += 4;
        for (int index = 0; (index<status_list.length); index ++) {
            length += status_list[index].getLength();
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
        dataWriter.writeUInt32(status_list.length);
        for (int index = 0; (index<status_list.length); index ++) {
            status_list[index].serialize(dataWriter);
        }
    }

}
