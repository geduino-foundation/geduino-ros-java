
package org.geduino.ros.common.msgs.hydro.visualization_msgs;

import java.io.IOException;
import org.geduino.ros.common.msgs.hydro.geometry_msgs.Point;
import org.geduino.ros.common.msgs.hydro.geometry_msgs.Pose;
import org.geduino.ros.common.msgs.hydro.geometry_msgs.Vector3;
import org.geduino.ros.common.msgs.hydro.std_msgs.Header;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   See http://www.ros.org/wiki/rviz/DisplayTypes/Marker and http://www.ros.org/wiki/rviz/Tutorials/Markers%3A%20Basic%20Shapes for more information on using this message with rviz
 * 
 */
public class Marker
    extends Message
{

    public final static MessageDetails<Marker> DETAILS = new MessageDetails<Marker>(Name.parseMessageName("visualization_msgs/Marker"), "18326976df9d29249efc939e00342cde", "Marker message", (Marker.class));
    /**
     * 
     * 
     */
    public final static int ARROW = (0);
    /**
     * 
     * 
     */
    public final static int CUBE = (1);
    /**
     * 
     * 
     */
    public final static int SPHERE = (2);
    /**
     * 
     * 
     */
    public final static int CYLINDER = (3);
    /**
     * 
     * 
     */
    public final static int LINE_STRIP = (4);
    /**
     * 
     * 
     */
    public final static int LINE_LIST = (5);
    /**
     * 
     * 
     */
    public final static int CUBE_LIST = (6);
    /**
     * 
     * 
     */
    public final static int SPHERE_LIST = (7);
    /**
     * 
     * 
     */
    public final static int POINTS = (8);
    /**
     * 
     * 
     */
    public final static int TEXT_VIEW_FACING = (9);
    /**
     * 
     * 
     */
    public final static int MESH_RESOURCE = (10);
    /**
     * 
     * 
     */
    public final static int TRIANGLE_LIST = (11);
    /**
     * 
     * 
     */
    public final static int ADD = (0);
    /**
     * 
     * 
     */
    public final static int MODIFY = (0);
    /**
     * 
     * 
     */
    public final static int DELETE = (2);
    /**
     *  header for time/frame information
     * 
     */
    public Header header = new Header();
    /**
     *  Namespace to place this object in... used in conjunction with id to create a unique name for the object
     * 
     */
    public String ns = "";
    /**
     *  object ID useful in conjunction with the namespace for manipulating and deleting the object later
     * 
     */
    public int id = 0;
    /**
     *  Type of object
     * 
     */
    public int type = 0;
    /**
     *  0 add/modify an object, 1 (deprecated), 2 deletes an object
     * 
     */
    public int action = 0;
    /**
     *  Pose of the object
     * 
     */
    public Pose pose = new Pose();
    /**
     *  Scale of the object 1,1,1 means default (usually 1 meter square)
     * 
     */
    public Vector3 scale = new Vector3();
    /**
     *  Color [0.0-1.0]
     * 
     */
    public org.geduino.ros.common.msgs.hydro.std_msgs.ColorRGBA color = new org.geduino.ros.common.msgs.hydro.std_msgs.ColorRGBA();
    /**
     *  How long the object should last before being automatically deleted.  0 means forever
     * 
     */
    public long lifetime = 0L;
    /**
     *  If this marker should be frame-locked, i.e. retransformed into its frame every timestep#Only used if the type specified has some use for them (eg. POINTS, LINE_STRIP, ...)
     * 
     */
    public boolean frame_locked = false;
    /**
     * Only used if the type specified has some use for them (eg. POINTS, LINE_STRIP, ...)#number of colors must either be 0 or equal to the number of points#NOTE: alpha is not yet used
     * 
     */
    public Point[] points = new Point[] {};
    /**
     *  NOTE: only used for text markers
     * 
     */
    public org.geduino.ros.common.msgs.hydro.std_msgs.ColorRGBA[] colors = new org.geduino.ros.common.msgs.hydro.std_msgs.ColorRGBA[] {};
    /**
     *  NOTE: only used for MESH_RESOURCE markers
     * 
     */
    public String text = "";
    /**
     * 
     * 
     */
    public String mesh_resource = "";
    /**
     * 
     * 
     */
    public boolean mesh_use_embedded_materials = false;

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += (ns.length()+ 4);
        length += 4;
        length += 4;
        length += 4;
        length += pose.getLength();
        length += scale.getLength();
        length += color.getLength();
        length += 8;
        length += 1;
        length += 4;
        for (int index = 0; (index<points.length); index ++) {
            length += points[index].getLength();
        }
        length += 4;
        for (int index = 0; (index<colors.length); index ++) {
            length += colors[index].getLength();
        }
        length += (text.length()+ 4);
        length += (mesh_resource.length()+ 4);
        length += 1;
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
        pose.serialize(dataWriter);
        scale.serialize(dataWriter);
        color.serialize(dataWriter);
        dataWriter.writeDuration(lifetime);
        dataWriter.writeBool(frame_locked);
        dataWriter.writeUInt32(points.length);
        for (int index = 0; (index<points.length); index ++) {
            points[index].serialize(dataWriter);
        }
        dataWriter.writeUInt32(colors.length);
        for (int index = 0; (index<colors.length); index ++) {
            colors[index].serialize(dataWriter);
        }
        dataWriter.writeUInt32(text.length());
        dataWriter.writeString(text);
        dataWriter.writeUInt32(mesh_resource.length());
        dataWriter.writeString(mesh_resource);
        dataWriter.writeBool(mesh_use_embedded_materials);
    }

}
