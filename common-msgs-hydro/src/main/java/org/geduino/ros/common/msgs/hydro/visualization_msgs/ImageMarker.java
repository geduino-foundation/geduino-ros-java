
package org.geduino.ros.common.msgs.hydro.visualization_msgs;

import java.io.IOException;
import org.geduino.ros.common.msgs.hydro.std_msgs.Header;
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
public class ImageMarker
    extends Message
{

    public final static MessageDetails<ImageMarker> DETAILS = new MessageDetails<ImageMarker>(Name.parseMessageName("visualization_msgs/ImageMarker"), "1de93c67ec8858b831025a08fbf1b35c", "ImageMarker message", (ImageMarker.class));
    /**
     * 
     * 
     */
    public final static int CIRCLE = (0);
    /**
     * 
     * 
     */
    public final static int LINE_STRIP = (1);
    /**
     * 
     * 
     */
    public final static int LINE_LIST = (2);
    /**
     * 
     * 
     */
    public final static int POLYGON = (3);
    /**
     * 
     * 
     */
    public final static int POINTS = (4);
    /**
     * 
     * 
     */
    public final static int ADD = (0);
    /**
     * 
     * 
     */
    public final static int REMOVE = (1);
    /**
     * 
     * 
     */
    public Header header = new Header();
    /**
     *  namespace, used with id to form a unique id
     * 
     */
    public String ns = "";
    /**
     *  unique id within the namespace
     * 
     */
    public int id = 0;
    /**
     *  CIRCLE/LINE_STRIP/etc.
     * 
     */
    public int type = 0;
    /**
     *  ADD/REMOVE
     * 
     */
    public int action = 0;
    /**
     *  2D, in pixel-coords
     * 
     */
    public org.geduino.ros.common.msgs.hydro.geometry_msgs.Point position = new org.geduino.ros.common.msgs.hydro.geometry_msgs.Point();
    /**
     *  the diameter for a circle, etc.
     * 
     */
    public float scale = 0.0F;
    /**
     * 
     * 
     */
    public org.geduino.ros.common.msgs.hydro.std_msgs.ColorRGBA outline_color = new org.geduino.ros.common.msgs.hydro.std_msgs.ColorRGBA();
    /**
     *  whether to fill in the shape with color
     * 
     */
    public int filled = 0;
    /**
     *  color [0.0-1.0]
     * 
     */
    public org.geduino.ros.common.msgs.hydro.std_msgs.ColorRGBA fill_color = new org.geduino.ros.common.msgs.hydro.std_msgs.ColorRGBA();
    /**
     *  How long the object should last before being automatically deleted.  0 means forever
     * 
     */
    public long lifetime = 0L;
    /**
     *  used for LINE_STRIP/LINE_LIST/POINTS/etc., 2D in pixel coords
     * 
     */
    public org.geduino.ros.common.msgs.hydro.geometry_msgs.Point[] points = new org.geduino.ros.common.msgs.hydro.geometry_msgs.Point[] {};
    /**
     *  a color for each line, point, etc.
     * 
     */
    public org.geduino.ros.common.msgs.hydro.std_msgs.ColorRGBA[] outline_colors = new org.geduino.ros.common.msgs.hydro.std_msgs.ColorRGBA[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += (ns.length()+ 4);
        length += 4;
        length += 4;
        length += 4;
        length += position.getLength();
        length += 4;
        length += outline_color.getLength();
        length += 1;
        length += fill_color.getLength();
        length += 8;
        length += 4;
        for (int index = 0; (index<points.length); index ++) {
            length += points[index].getLength();
        }
        length += 4;
        for (int index = 0; (index<outline_colors.length); index ++) {
            length += outline_colors[index].getLength();
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
        dataWriter.writeUInt32(ns.length());
        dataWriter.writeString(ns);
        dataWriter.writeInt32(id);
        dataWriter.writeInt32(type);
        dataWriter.writeInt32(action);
        position.serialize(dataWriter);
        dataWriter.writeFloat32(scale);
        outline_color.serialize(dataWriter);
        dataWriter.writeUInt8(filled);
        fill_color.serialize(dataWriter);
        dataWriter.writeDuration(lifetime);
        dataWriter.writeUInt32(points.length);
        for (int index = 0; (index<points.length); index ++) {
            points[index].serialize(dataWriter);
        }
        dataWriter.writeUInt32(outline_colors.length);
        for (int index = 0; (index<outline_colors.length); index ++) {
            outline_colors[index].serialize(dataWriter);
        }
    }

}
