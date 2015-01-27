
package org.geduino.ros.common.msgs.hydro.shape_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   Representation of a plane, using the plane equation ax + by + cz + d = 0  a := coef[0]  b := coef[1]  c := coef[2]  d := coef[3]
 * 
 */
public class Plane
    extends Message
{

    public final static MessageDetails<Plane> DETAILS = new MessageDetails<Plane>(Name.parseMessageName("shape_msgs/Plane"), "2c1b92ed8f31492f8e73f6a4a44ca796", "Plane message", (Plane.class));
    /**
     * 
     * 
     */
    public double[] coef = new double[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += 4;
        for (int index = 0; (index<coef.length); index ++) {
            length += 8;
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
        dataWriter.writeUInt32(coef.length);
        for (int index = 0; (index<coef.length); index ++) {
            dataWriter.writeFloat64(coef[index]);
        }
    }

}
