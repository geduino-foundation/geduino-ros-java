
package org.geduino.ros.common.msgs.hydro.diagnostic_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   This message holds the status of an individual component of the robot.   Possible levels of operations
 * 
 */
public class DiagnosticStatus
    extends Message
{

    public final static MessageDetails<DiagnosticStatus> DETAILS = new MessageDetails<DiagnosticStatus>(Name.parseMessageName("diagnostic_msgs/DiagnosticStatus"), "67d15a62edb26e9d52b0f0efa3ef9da7", "DiagnosticStatus message", (DiagnosticStatus.class));
    /**
     * 
     * 
     */
    public final static int OK = (0);
    /**
     * 
     * 
     */
    public final static int WARN = (1);
    /**
     * 
     * 
     */
    public final static int ERROR = (2);
    /**
     *  level of operation enumerated above
     * 
     */
    public int level = 0;
    /**
     *  a description of the test/component reporting
     * 
     */
    public String name = "";
    /**
     *  a description of the status
     * 
     */
    public String message = "";
    /**
     *  a hardware unique string
     * 
     */
    public String hardware_id = "";
    /**
     *  an array of values associated with the status
     * 
     */
    public KeyValue[] values = new KeyValue[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += 1;
        length += (name.length()+ 4);
        length += (message.length()+ 4);
        length += (hardware_id.length()+ 4);
        length += 4;
        for (int index = 0; (index<values.length); index ++) {
            length += values[index].getLength();
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
        dataWriter.writeInt8(level);
        dataWriter.writeUInt32(name.length());
        dataWriter.writeString(name);
        dataWriter.writeUInt32(message.length());
        dataWriter.writeString(message);
        dataWriter.writeUInt32(hardware_id.length());
        dataWriter.writeString(hardware_id);
        dataWriter.writeUInt32(values.length);
        for (int index = 0; (index<values.length); index ++) {
            values[index].serialize(dataWriter);
        }
    }

}
