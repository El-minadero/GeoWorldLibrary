package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public interface INodeRegion {

	boolean isLeaf();

	boolean isInside(Vector2i key);
	boolean isInside(Vector3i key);

	double getCenterDistance(Vector3i vec3);
	double getCenterDistance(Vector2i vec3);

	String getLocationData(Vector3i globalVector);
	
	String toString();

}
