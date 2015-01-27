
package org.geduino.ros.common.msgs.hydro.visualization_msgs;

import java.io.IOException;
import org.geduino.ros.common.msgs.hydro.geometry_msgs.Point;
import org.geduino.ros.common.msgs.hydro.geometry_msgs.Pose;
import org.geduino.ros.common.msgs.hydro.std_msgs.Header;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   Time/frame info.
 * 
 */
public class InteractiveMarkerFeedback
    extends Message
{

    public final static MessageDetails<InteractiveMarkerFeedback> DETAILS = new MessageDetails<InteractiveMarkerFeedback>(Name.parseMessageName("visualization_msgs/InteractiveMarkerFeedback"), "ab0f1eee058667e28c19ff3ffc3f4b78", "InteractiveMarkerFeedback message", (InteractiveMarkerFeedback.class));
    /**
     *  Identifying string. Must be unique in the topic namespace.
     * 
     */
    public Header header = new Header();
    /**
     *  Feedback message sent back from the GUI, e.g.# when the status of an interactive marker was modified by the user.# Specifies which interactive marker and control this message refers to
     * 
     */
    public String client_id = "";
    /**
     * 
     * 
     */
    public String marker_name = "";
    /**
     *  Type of the event# KEEP_ALIVE: sent while dragging to keep up control of the marker# MENU_SELECT: a menu entry has been selected# BUTTON_CLICK: a button control has been clicked# POSE_UPDATE: the pose has been changed using one of the controls
     * 
     */
    public String control_name = "";
    /**
     * 
     * 
     */
    public final static int KEEP_ALIVE = (0);
    /**
     * 
     * 
     */
    public final static int POSE_UPDATE = (1);
    /**
     * 
     * 
     */
    public final static int MENU_SELECT = (2);
    /**
     * 
     * 
     */
    public final static int BUTTON_CLICK = (3);
    /**
     * 
     * 
     */
    public final static int MOUSE_DOWN = (4);
    /**
     * 
     * 
     */
    public final static int MOUSE_UP = (5);
    /**
     *  Current pose of the marker# Note: Has to be valid for all feedback types.
     * 
     */
    public int event_type = 0;
    /**
     *  Contains the ID of the selected menu entry# Only valid for MENU_SELECT events.
     * 
     */
    public Pose pose = new Pose();
    /**
     *  If event_type is BUTTON_CLICK, MOUSE_DOWN, or MOUSE_UP, mouse_point# may contain the 3 dimensional position of the event on the# control.  If it does, mouse_point_valid will be true.  mouse_point# will be relative to the frame listed in the header.
     * 
     */
    public long menu_entry_id = 0L;
    /**
     * 
     * 
     */
    public Point mouse_point = new Point();
    /**
     * 
     * 
     */
    public boolean mouse_point_valid = false;

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += (client_id.length()+ 4);
        length += (marker_name.length()+ 4);
        length += (control_name.length()+ 4);
        length += 1;
        length += pose.getLength();
        length += 4;
        length += mouse_point.getLength();
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
        dataWriter.writeUInt32(client_id.length());
        dataWriter.writeString(client_id);
        dataWriter.writeUInt32(marker_name.length());
        dataWriter.writeString(marker_name);
        dataWriter.writeUInt32(control_name.length());
        dataWriter.writeString(control_name);
        dataWriter.writeUInt8(event_type);
        pose.serialize(dataWriter);
        dataWriter.writeUInt32(menu_entry_id);
        mouse_point.serialize(dataWriter);
        dataWriter.writeBool(mouse_point_valid);
    }

}
