
package org.geduino.ros.common.msgs.hydro.visualization_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   MenuEntry message.  Each InteractiveMarker message has an array of MenuEntry messages.  A collection of MenuEntries together describe a  menu/submenu/subsubmenu/etc tree, though they are stored in a flat  array.  The tree structure is represented by giving each menu entry  an ID number and a "parent_id" field.  Top-level entries are the  ones with parent_id = 0.  Menu entries are ordered within their  level the same way they are ordered in the containing array.  Parent  entries must appear before their children.  Example:  - id = 3    parent_id = 0    title = "fun"  - id = 2    parent_id = 0    title = "robot"  - id = 4    parent_id = 2    title = "pr2"  - id = 5    parent_id = 2    title = "turtle"   Gives a menu tree like this:   - fun   - robot     - pr2     - turtle  ID is a number for each menu entry.  Must be unique within the  control, and should never be 0.
 * 
 */
public class MenuEntry
    extends Message
{

    public final static MessageDetails<MenuEntry> DETAILS = new MessageDetails<MenuEntry>(Name.parseMessageName("visualization_msgs/MenuEntry"), "b90ec63024573de83b57aa93eb39be2d", "MenuEntry message", (MenuEntry.class));
    /**
     *  ID of the parent of this menu entry, if it is a submenu.  If this# menu entry is a top-level entry, set parent_id to 0.
     * 
     */
    public long id = 0L;
    /**
     *  menu / entry title
     * 
     */
    public long parent_id = 0L;
    /**
     *  Arguments to command indicated by command_type (below)
     * 
     */
    public String title = "";
    /**
     *  Command_type stores the type of response desired when this menu# entry is clicked.# FEEDBACK: send an InteractiveMarkerFeedback message with menu_entry_id set to this entry's id.# ROSRUN: execute "rosrun" with arguments given in the command field (above).# ROSLAUNCH: execute "roslaunch" with arguments given in the command field (above).
     * 
     */
    public String command = "";
    /**
     * 
     * 
     */
    public final static int FEEDBACK = (0);
    /**
     * 
     * 
     */
    public final static int ROSRUN = (1);
    /**
     * 
     * 
     */
    public final static int ROSLAUNCH = (2);
    /**
     * 
     * 
     */
    public int command_type = 0;

    @Override
    public long getLength() {
        long length = 0;
        length += 4;
        length += 4;
        length += (title.length()+ 4);
        length += (command.length()+ 4);
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
        dataWriter.writeUInt32(id);
        dataWriter.writeUInt32(parent_id);
        dataWriter.writeUInt32(title.length());
        dataWriter.writeString(title);
        dataWriter.writeUInt32(command.length());
        dataWriter.writeString(command);
        dataWriter.writeUInt8(command_type);
    }

}
