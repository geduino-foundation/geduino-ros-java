
package org.geduino.ros.common.msgs.hydro.std_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   Standard metadata for higher-level stamped data types.  This is generally used to communicate timestamped data  in a particular coordinate frame.   sequence ID: consecutively increasing ID
 * 
 */
public class Header
    extends Message
{

    public final static MessageDetails<Header> DETAILS = new MessageDetails<Header>(Name.parseMessageName("std_msgs/Header"), "2176decaecbce78abc3b96ef049fabed", "Header message", (Header.class));
    /**
     * Two-integer timestamp that is expressed as:# * stamp.sec: seconds (stamp_secs) since epoch (in Python the variable is called 'secs')# * stamp.nsec: nanoseconds since stamp_secs (in Python the variable is called 'nsecs')# time-handling sugar is provided by the client library
     * 
     */
    public long seq = 0L;
    /**
     * Frame this data is associated with# 0: no frame# 1: global frame
     * 
     */
    public long stamp = 0L;
    /**
     * 
     * 
     */
    public java.lang.String frame_id = "";

    @Override
    public long getLength() {
        long length = 0;
        length += 4;
        length += 8;
        length += (frame_id.length()+ 4);
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
        dataWriter.writeUInt32(seq);
        dataWriter.writeTime(stamp);
        dataWriter.writeUInt32(frame_id.length());
        dataWriter.writeString(frame_id);
    }

}
