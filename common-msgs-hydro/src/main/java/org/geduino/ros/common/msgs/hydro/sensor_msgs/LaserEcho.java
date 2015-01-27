
package org.geduino.ros.common.msgs.hydro.sensor_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   This message is a submessage of MultiEchoLaserScan and is not intended  to be used separately.
 * 
 */
public class LaserEcho
    extends Message
{

    public final static MessageDetails<LaserEcho> DETAILS = new MessageDetails<LaserEcho>(Name.parseMessageName("sensor_msgs/LaserEcho"), "8bc5ae449b200fba4d552b4225586696", "LaserEcho message", (LaserEcho.class));
    /**
     *  Multiple values of ranges or intensities.# Each array represents data from the same angle increment.
     * 
     */
    public float[] echoes = new float[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += 4;
        for (int index = 0; (index<echoes.length); index ++) {
            length += 4;
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
        dataWriter.writeUInt32(echoes.length);
        for (int index = 0; (index<echoes.length); index ++) {
            dataWriter.writeFloat32(echoes[index]);
        }
    }

}
