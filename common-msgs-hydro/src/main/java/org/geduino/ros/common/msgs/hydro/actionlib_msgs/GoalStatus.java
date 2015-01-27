
package org.geduino.ros.common.msgs.hydro.actionlib_msgs;

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
public class GoalStatus
    extends Message
{

    public final static MessageDetails<GoalStatus> DETAILS = new MessageDetails<GoalStatus>(Name.parseMessageName("actionlib_msgs/GoalStatus"), "d388f9b87b3c471f784434d671988d4a", "GoalStatus message", (GoalStatus.class));
    /**
     * 
     * 
     */
    public GoalID goal_id = new GoalID();
    /**
     * 
     * 
     */
    public int status = 0;
    /**
     *  The goal has yet to be processed by the action server
     * 
     */
    public final static int PENDING = (0);
    /**
     *  The goal is currently being processed by the action server
     * 
     */
    public final static int ACTIVE = (1);
    /**
     *  The goal received a cancel request after it started executing#   and has since completed its execution (Terminal State)
     * 
     */
    public final static int PREEMPTED = (2);
    /**
     *  The goal was achieved successfully by the action server (Terminal State)
     * 
     */
    public final static int SUCCEEDED = (3);
    /**
     *  The goal was aborted during execution by the action server due#    to some failure (Terminal State)
     * 
     */
    public final static int ABORTED = (4);
    /**
     *  The goal was rejected by the action server without being processed,#    because the goal was unattainable or invalid (Terminal State)
     * 
     */
    public final static int REJECTED = (5);
    /**
     *  The goal received a cancel request after it started executing#    and has not yet completed execution
     * 
     */
    public final static int PREEMPTING = (6);
    /**
     *  The goal received a cancel request before it started executing,#    but the action server has not yet confirmed that the goal is canceled
     * 
     */
    public final static int RECALLING = (7);
    /**
     *  The goal received a cancel request before it started executing#    and was successfully cancelled (Terminal State)
     * 
     */
    public final static int RECALLED = (8);
    /**
     *  An action client can determine that a goal is LOST. This should not be#    sent over the wire by an action server#Allow for the user to associate a string with GoalStatus for debugging
     * 
     */
    public final static int LOST = (9);
    /**
     * 
     * 
     */
    public String text = "";

    @Override
    public long getLength() {
        long length = 0;
        length += goal_id.getLength();
        length += 1;
        length += (text.length()+ 4);
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
        goal_id.serialize(dataWriter);
        dataWriter.writeUInt8(status);
        dataWriter.writeUInt32(text.length());
        dataWriter.writeString(text);
    }

}
