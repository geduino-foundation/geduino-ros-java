
package org.geduino.ros.common.msgs.hydro.std_msgs;

import java.io.IOException;
import java.math.BigInteger;
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
public class UInt64
    extends Message
{

    public final static MessageDetails<UInt64> DETAILS = new MessageDetails<UInt64>(Name.parseMessageName("std_msgs/UInt64"), "1b2a79973e8bf53d7b53acb71299cb57", "UInt64 message", (UInt64.class));
    /**
     * 
     * 
     */
    public BigInteger data = (BigInteger.ZERO);

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
        dataWriter.writeUInt64(data);
    }

}
