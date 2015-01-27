
package org.geduino.ros.common.msgs.hydro.visualization_msgs;

import java.io.IOException;
import org.geduino.ros.common.msgs.hydro.geometry_msgs.Quaternion;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   Represents a control that is to be displayed together with an interactive marker  Identifying string for this control.  You need to assign a unique value to this to receive feedback from the GUI  on what actions the user performs on this control (e.g. a button click).
 * 
 */
public class InteractiveMarkerControl
    extends Message
{

    public final static MessageDetails<InteractiveMarkerControl> DETAILS = new MessageDetails<InteractiveMarkerControl>(Name.parseMessageName("visualization_msgs/InteractiveMarkerControl"), "e3a939c98cdd4f709d8e1dec2a9c3f37", "InteractiveMarkerControl message", (InteractiveMarkerControl.class));
    /**
     *  Defines the local coordinate frame (relative to the pose of the parent# interactive marker) in which is being rotated and translated.# Default: Identity
     * 
     */
    public String name = "";
    /**
     *  Orientation mode: controls how orientation changes.# INHERIT: Follow orientation of interactive marker# FIXED: Keep orientation fixed at initial state# VIEW_FACING: Align y-z plane with screen (x: forward, y:left, z:up).
     * 
     */
    public Quaternion orientation = new Quaternion();
    /**
     * 
     * 
     */
    public final static int INHERIT = (0);
    /**
     * 
     * 
     */
    public final static int FIXED = (1);
    /**
     * 
     * 
     */
    public final static int VIEW_FACING = (2);
    /**
     *  Interaction mode for this control## NONE: This control is only meant for visualization; no context menu.# MENU: Like NONE, but right-click menu is active.# BUTTON: Element can be left-clicked.# MOVE_AXIS: Translate along local x-axis.# MOVE_PLANE: Translate in local y-z plane.# ROTATE_AXIS: Rotate around local x-axis.# MOVE_ROTATE: Combines MOVE_PLANE and ROTATE_AXIS.
     * 
     */
    public int orientation_mode = 0;
    /**
     * 
     * 
     */
    public final static int NONE = (0);
    /**
     * 
     * 
     */
    public final static int MENU = (1);
    /**
     * 
     * 
     */
    public final static int BUTTON = (2);
    /**
     * 
     * 
     */
    public final static int MOVE_AXIS = (3);
    /**
     * 
     * 
     */
    public final static int MOVE_PLANE = (4);
    /**
     * 
     * 
     */
    public final static int ROTATE_AXIS = (5);
    /**
     *  "3D" interaction modes work with the mouse+SHIFT+CTRL or with 3D cursors.# MOVE_3D: Translate freely in 3D space.# ROTATE_3D: Rotate freely in 3D space about the origin of parent frame.# MOVE_ROTATE_3D: Full 6-DOF freedom of translation and rotation about the cursor origin.
     * 
     */
    public final static int MOVE_ROTATE = (6);
    /**
     * 
     * 
     */
    public final static int MOVE_3D = (7);
    /**
     * 
     * 
     */
    public final static int ROTATE_3D = (8);
    /**
     * 
     * 
     */
    public final static int MOVE_ROTATE_3D = (9);
    /**
     *  If true, the contained markers will also be visible# when the gui is not in interactive mode.
     * 
     */
    public int interaction_mode = 0;
    /**
     *  Markers to be displayed as custom visual representation.# Leave this empty to use the default control handles.## Note:# - The markers can be defined in an arbitrary coordinate frame,#   but will be transformed into the local frame of the interactive marker.# - If the header of a marker is empty, its pose will be interpreted as#   relative to the pose of the parent interactive marker.
     * 
     */
    public boolean always_visible = false;
    /**
     *  In VIEW_FACING mode, set this to true if you don't want the markers# to be aligned with the camera view point. The markers will show up# as in INHERIT mode.
     * 
     */
    public Marker[] markers = new Marker[] {};
    /**
     *  Short description (< 40 characters) of what this control does,# e.g. "Move the robot".# Default: A generic description based on the interaction mode
     * 
     */
    public boolean independent_marker_orientation = false;
    /**
     * 
     * 
     */
    public String description = "";

    @Override
    public long getLength() {
        long length = 0;
        length += (name.length()+ 4);
        length += orientation.getLength();
        length += 1;
        length += 1;
        length += 1;
        length += 4;
        for (int index = 0; (index<markers.length); index ++) {
            length += markers[index].getLength();
        }
        length += 1;
        length += (description.length()+ 4);
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
        dataWriter.writeUInt32(name.length());
        dataWriter.writeString(name);
        orientation.serialize(dataWriter);
        dataWriter.writeUInt8(orientation_mode);
        dataWriter.writeUInt8(interaction_mode);
        dataWriter.writeBool(always_visible);
        dataWriter.writeUInt32(markers.length);
        for (int index = 0; (index<markers.length); index ++) {
            markers[index].serialize(dataWriter);
        }
        dataWriter.writeBool(independent_marker_orientation);
        dataWriter.writeUInt32(description.length());
        dataWriter.writeString(description);
    }

}
