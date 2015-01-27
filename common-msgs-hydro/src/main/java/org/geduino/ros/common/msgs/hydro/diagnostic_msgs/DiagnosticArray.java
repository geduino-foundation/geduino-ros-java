
package org.geduino.ros.common.msgs.hydro.diagnostic_msgs;

import java.io.IOException;
import org.geduino.ros.common.msgs.hydro.std_msgs.Header;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   This message is used to send diagnostic information about the state of the robot
 * 
 */
public class DiagnosticArray
    extends Message
{

    public final static MessageDetails<DiagnosticArray> DETAILS = new MessageDetails<DiagnosticArray>(Name.parseMessageName("diagnostic_msgs/DiagnosticArray"), "3cfbeff055e708a24c3d946a5c8139cd", "DiagnosticArray message", (DiagnosticArray.class));
    /**
     * for timestamp
     * 
     */
    public Header header = new Header();
    /**
     *  an array of components being reported on
     * 
     */
    public DiagnosticStatus[] status = new DiagnosticStatus[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += 4;
        for (int index = 0; (index<status.length); index ++) {
            length += status[index].getLength();
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
        dataWriter.writeUInt32(status.length);
        for (int index = 0; (index<status.length); index ++) {
            status[index].serialize(dataWriter);
        }
    }

}
