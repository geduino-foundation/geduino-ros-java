
package org.geduino.ros.common.msgs.hydro.sensor_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   Navigation Satellite fix status for any Global Navigation Satellite System  Whether to output an augmented fix is determined by both the fix  type and the last time differential corrections were received.  A  fix is valid when status >= STATUS_FIX.
 * 
 */
public class NavSatStatus
    extends Message
{

    public final static MessageDetails<NavSatStatus> DETAILS = new MessageDetails<NavSatStatus>(Name.parseMessageName("sensor_msgs/NavSatStatus"), "331cdbddfa4bc96ffc3b9ad98900a54c", "NavSatStatus message", (NavSatStatus.class));
    /**
     *  unable to fix position
     * 
     */
    public final static int STATUS_NO_FIX = (-1);
    /**
     *  unaugmented fix
     * 
     */
    public final static int STATUS_FIX = (0);
    /**
     *  with satellite-based augmentation
     * 
     */
    public final static int STATUS_SBAS_FIX = (1);
    /**
     *  with ground-based augmentation
     * 
     */
    public final static int STATUS_GBAS_FIX = (2);
    /**
     *  Bits defining which Global Navigation Satellite System signals were# used by the receiver.
     * 
     */
    public int status = 0;
    /**
     * 
     * 
     */
    public final static int SERVICE_GPS = (1);
    /**
     * 
     * 
     */
    public final static int SERVICE_GLONASS = (2);
    /**
     *  includes BeiDou.
     * 
     */
    public final static int SERVICE_COMPASS = (4);
    /**
     * 
     * 
     */
    public final static int SERVICE_GALILEO = (8);
    /**
     * 
     * 
     */
    public int service = 0;

    @Override
    public long getLength() {
        long length = 0;
        length += 1;
        length += 2;
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
        dataWriter.writeInt8(status);
        dataWriter.writeUInt16(service);
    }

}
