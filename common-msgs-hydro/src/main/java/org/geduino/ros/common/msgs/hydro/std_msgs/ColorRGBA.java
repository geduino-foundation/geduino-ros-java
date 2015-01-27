
package org.geduino.ros.common.msgs.hydro.std_msgs;

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
public class ColorRGBA
    extends Message
{

    public final static MessageDetails<ColorRGBA> DETAILS = new MessageDetails<ColorRGBA>(Name.parseMessageName("std_msgs/ColorRGBA"), "a29a96539573343b1310c73607334b00", "ColorRGBA message", (ColorRGBA.class));
    /**
     * 
     * 
     */
    public float r = 0.0F;
    /**
     * 
     * 
     */
    public float g = 0.0F;
    /**
     * 
     * 
     */
    public float b = 0.0F;
    /**
     * 
     * 
     */
    public float a = 0.0F;

    @Override
    public long getLength() {
        long length = 0;
        length += 4;
        length += 4;
        length += 4;
        length += 4;
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
        dataWriter.writeFloat32(r);
        dataWriter.writeFloat32(g);
        dataWriter.writeFloat32(b);
        dataWriter.writeFloat32(a);
    }

}
