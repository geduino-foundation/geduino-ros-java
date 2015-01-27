
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
 *   Measurement from an external time source not actively synchronized with the system clock.
 * 
 */
public class TimeReference
    extends Message
{

    public final static MessageDetails<TimeReference> DETAILS = new MessageDetails<TimeReference>(Name.parseMessageName("sensor_msgs/TimeReference"), "fded64a0265108ba86c3d38fb11c0c16", "TimeReference message", (TimeReference.class));
    /**
     *  stamp is system time for which measurement was valid# frame_id is not used
     * 
     */
    public Header header = new Header();
    /**
     *  corresponding time from this external source
     * 
     */
    public long time_ref = 0L;
    /**
     *  (optional) name of time source
     * 
     */
    public String source = "";

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += 8;
        length += (source.length()+ 4);
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
        dataWriter.writeTime(time_ref);
        dataWriter.writeUInt32(source.length());
        dataWriter.writeString(source);
    }

}
