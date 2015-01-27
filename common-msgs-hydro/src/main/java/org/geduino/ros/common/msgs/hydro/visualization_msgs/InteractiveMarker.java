
package org.geduino.ros.common.msgs.hydro.visualization_msgs;

import java.io.IOException;
import org.geduino.ros.common.msgs.hydro.geometry_msgs.Pose;
import org.geduino.ros.common.msgs.hydro.std_msgs.Header;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   Time/frame info.  If header.time is set to 0, the marker will be retransformed into  its frame on each timestep. You will receive the pose feedback  in the same frame.  Otherwise, you might receive feedback in a different frame.  For rviz, this will be the current 'fixed frame' set by the user.
 * 
 */
public class InteractiveMarker
    extends Message
{

    public final static MessageDetails<InteractiveMarker> DETAILS = new MessageDetails<InteractiveMarker>(Name.parseMessageName("visualization_msgs/InteractiveMarker"), "311bd5f6cd6a20820ac0ba84315f4e22", "InteractiveMarker message", (InteractiveMarker.class));
    /**
     *  Initial pose. Also, defines the pivot point for rotations.
     * 
     */
    public Header header = new Header();
    /**
     *  Identifying string. Must be globally unique in# the topic that this message is sent through.
     * 
     */
    public Pose pose = new Pose();
    /**
     *  Short description (< 40 characters).
     * 
     */
    public String name = "";
    /**
     *  Scale to be used for default controls (default=1).
     * 
     */
    public String description = "";
    /**
     *  All menu and submenu entries associated with this marker.
     * 
     */
    public float scale = 0.0F;
    /**
     *  List of controls displayed for this marker.
     * 
     */
    public MenuEntry[] menu_entries = new MenuEntry[] {};
    /**
     * 
     * 
     */
    public InteractiveMarkerControl[] controls = new InteractiveMarkerControl[] {};

    @Override
    public long getLength() {
        long length = 0;
        length += header.getLength();
        length += pose.getLength();
        length += (name.length()+ 4);
        length += (description.length()+ 4);
        length += 4;
        length += 4;
        for (int index = 0; (index<menu_entries.length); index ++) {
            length += menu_entries[index].getLength();
        }
        length += 4;
        for (int index = 0; (index<controls.length); index ++) {
            length += controls[index].getLength();
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
        pose.serialize(dataWriter);
        dataWriter.writeUInt32(name.length());
        dataWriter.writeString(name);
        dataWriter.writeUInt32(description.length());
        dataWriter.writeString(description);
        dataWriter.writeFloat32(scale);
        dataWriter.writeUInt32(menu_entries.length);
        for (int index = 0; (index<menu_entries.length); index ++) {
            menu_entries[index].serialize(dataWriter);
        }
        dataWriter.writeUInt32(controls.length);
        for (int index = 0; (index<controls.length); index ++) {
            controls[index].serialize(dataWriter);
        }
    }

}
