
package org.geduino.ros.common.msgs.hydro.shape_msgs;

import java.io.IOException;
import org.geduino.ros.core.messages.exception.RosMessageSerializationException;
import org.geduino.ros.core.messages.model.DataReader;
import org.geduino.ros.core.messages.model.DataWriter;
import org.geduino.ros.core.messages.model.Message;
import org.geduino.ros.core.messages.model.MessageDetails;
import org.geduino.ros.core.naming.model.Name;


/**
 *   Define box, sphere, cylinder, cone  All shapes are defined to have their bounding boxes centered around 0,0,0.
 * 
 */
public class SolidPrimitive
    extends Message
{

    public final static MessageDetails<SolidPrimitive> DETAILS = new MessageDetails<SolidPrimitive>(Name.parseMessageName("shape_msgs/SolidPrimitive"), "d8f8cbc74c5ff283fca29569ccefb45d", "SolidPrimitive message", (SolidPrimitive.class));
    /**
     * 
     * 
     */
    public final static int BOX = (1);
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
     *  The type of the shape
     * 
     */
    public final static int CONE = (4);
    /**
     *  The dimensions of the shape
     * 
     */
    public int type = 0;
    /**
     *  The meaning of the shape dimensions: each constant defines the index in the 'dimensions' array# For the BOX type, the X, Y, and Z dimensions are the length of the corresponding# sides of the box.
     * 
     */
    public double[] dimensions = new double[] {};
    /**
     * 
     * 
     */
    public final static int BOX_X = (0);
    /**
     * 
     * 
     */
    public final static int BOX_Y = (1);
    /**
     *  For the SPHERE type, only one component is used, and it gives the radius of# the sphere.
     * 
     */
    public final static int BOX_Z = (2);
    /**
     *  For the CYLINDER and CONE types, the center line is oriented along# the Z axis.  Therefore the CYLINDER_HEIGHT (CONE_HEIGHT) component# of dimensions gives the height of the cylinder (cone).  The# CYLINDER_RADIUS (CONE_RADIUS) component of dimensions gives the# radius of the base of the cylinder (cone).  Cone and cylinder# primitives are defined to be circular. The tip of the cone is# pointing up, along +Z axis.
     * 
     */
    public final static int SPHERE_RADIUS = (0);
    /**
     * 
     * 
     */
    public final static int CYLINDER_HEIGHT = (0);
    /**
     * 
     * 
     */
    public final static int CYLINDER_RADIUS = (1);
    /**
     * 
     * 
     */
    public final static int CONE_HEIGHT = (0);
    /**
     * 
     * 
     */
    public final static int CONE_RADIUS = (1);

    @Override
    public long getLength() {
        long length = 0;
        length += 1;
        length += 4;
        for (int index = 0; (index<dimensions.length); index ++) {
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
        dataWriter.writeUInt8(type);
        dataWriter.writeUInt32(dimensions.length);
        for (int index = 0; (index<dimensions.length); index ++) {
            dataWriter.writeFloat64(dimensions[index]);
        }
    }

}
