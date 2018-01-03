package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;

import java.util.Set;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.IData;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.INodeCache;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.INodeRegion;

class Node extends AbstractNode implements INode {

	private final IPrototype prototype;
	private final INodeCache cache;
	
	Node(NodeBuilder nodeBuilder){
		super(nodeBuilder);
		prototype 		= nodeBuilder.getPrototype();
		cache			= nodeBuilder.getCache();
	}
	
	protected IData getBaseData(Vector3i vector3i) { return prototype.getData(vector3i); }
	protected IData getBaseData(Vector2i vector2i) { return prototype.getData(vector2i); }
	
	public String getLocationData(Vector3i vec) 
	{ return prototype.getLocationData(vec); }
	public 		final String getName() { return prototype.getName(); }
	public 		final boolean isLeaf() { return false; }
	public 		final double getCenterDistance(Vector3i vec) 	{ return prototype.getCenterDistance(vec); }
	public 		final double getCenterDistance(Vector2i vec) 	{ return prototype.getCenterDistance(vec); }
	public 		final Vector2i getRandomInternalPoint2i() 		{ return prototype.getRandomInternalPoint2i(); }
	public 		final Vector3i getRandomInternalPoint3i() 		{ return prototype.getRandomInternalPoint3i(); }
	public 		final boolean isInside(Vector2i vec)  		{ return prototype.isInside(vec); }
	public 		final boolean isInside(Vector3i vec)  		{ return prototype.isInside(vec); }

	
	@Override
	public final boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Node)) {
			return false;
		}
		Node user = (Node) o;
		return user.hashCode()==user.hashCode();
	}
	
	@Override
	public final int hashCode(){
		return prototype.hashCode();
	}
	
	public double getExternalMultiplier(Vector2i vector2i) {
		if(prototype.isInside(vector2i))
			return 1;
		else
			return prototype.getExternalMultiplier(vector2i);
	}

	@Override
	public double getExternalMultiplier(Vector3i vector3i) {
		if(prototype.isInside(vector3i))
			return 1;
		else
			return prototype.getExternalMultiplier(vector3i);
	}

	@Override
	protected double getModifier(Vector2i vector2i) {
		return getExternalMultiplier(vector2i);
	}

	@Override
	protected double getModifier(Vector3i vector3i) {
		return getExternalMultiplier(vector3i);
	}

}
