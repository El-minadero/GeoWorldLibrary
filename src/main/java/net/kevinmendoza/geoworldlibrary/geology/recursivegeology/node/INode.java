package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.INodeRegion;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.IData;

public interface INode extends INodeRegion,IGeology {
	
	/**
	 *  Returns the name of the Node or its prototype
	 * @return
	 */
	public String getName();
	/**
	 *  Returns some amount of human readable information
	 * @return
	 */
	public String getLocationData(Vector3i vec);
	
	/**
	 *  Returns the double distance to the center of the IRegion shape within the prototype
	 * @return
	 */
	public double getCenterDistance(Vector3i vec);
	/**
	 *  Returns the double distance to the center of the IRegion shape within the prototype
	 * @return
	 */
	public double getCenterDistance(Vector2i vec);
	
	/**
	 *  Returns a Vector2i point within the area defined by IRegion
	 * @return
	 */
	public Vector2i getRandomInternalPoint2i();
	/**
	 *  Returns a Vector3i point within the area defined by IRegion
	 * @return
	 */
	public Vector3i getRandomInternalPoint3i();
	
	/**
	 *  Returns true if vector is inside the area defined by IRegion
	 * @return
	 */
	public boolean isInside(Vector2i vector2i);
	
	public boolean isInside(Vector3i vector3i);
	
	/**
	 *  Returns a data object defined by the Node, prototype, and any nested objects
	 * @return IData data
	 */
	public IData getData(Vector2i vector2i);
	/**
	 *  Returns a data object defined by the Node, prototype, and any nested objects
	 * @return IData data
	 */
	public IData getData(Vector3i vector3i);
	/**
	 *  Returns a data object defined by the Node, prototype, and any nested objects without
	 *  applied decay values for the object in question
	 * @return IData data
	 */
	public IData getDefaultData(Vector2i vector2i);
	/**
	 *  Returns a data object defined by the Node, prototype, and any nested objects without
	 *  applied decay values for the object in question
	 * @return IData data
	 */
	public IData getDefaultData(Vector3i vector3i);
	/**
	 *  return the external damping parameter;
	 * @param vector2i
	 * @return
	 */
	public double getExternalMultiplier(Vector2i vector2i);
	/**
	 *  return the external damping parameter;
	 * @param vector2i
	 * @return
	 */
	public double getExternalMultiplier(Vector3i vector3i);
	
}
