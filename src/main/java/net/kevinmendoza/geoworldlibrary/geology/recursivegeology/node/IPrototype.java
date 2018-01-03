package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.IData;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.INodeRegion;

interface IPrototype extends INode {

	public String getLocationData(Vector3i vec);

	public String getName();

	public Vector2i getRandomInternalPoint2i();

	public Vector3i getRandomInternalPoint3i();
	
	public double getExternalMultiplier(Vector2i vector2i);
	
	public double getExternalMultiplier(Vector3i vector3i);
}
