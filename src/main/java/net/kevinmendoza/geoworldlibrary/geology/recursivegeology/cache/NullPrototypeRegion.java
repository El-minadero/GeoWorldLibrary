package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;
import com.flowpowered.noise.module.combiner.Min;

public class NullPrototypeRegion implements INodeRegion {

	private static final int MAX = 	5;
	private final Vector2i center2;
	private final Vector3i center3;
	private final int hash;
	
	NullPrototypeRegion(Vector2i center){
		this.center2 = center;
		this.center3 = new Vector3i(center.getX(),0,center.getY());
		hash = center3.hashCode();
	}
	
	NullPrototypeRegion(Vector3i center){
		this.center2 = new Vector2i(center.getX(),center.getZ());
		this.center3 = center;
		hash = center2.hashCode();
	}

	public final boolean isLeaf() { return true; }
	public final double getCenterDistance(Vector3i vec3) { return vec3.distance(center3); }
	public final double getCenterDistance(Vector2i vec2) { return vec2.distance(center2); }

	public boolean isInside(Vector2i key) {
		Vector2i minus = key.sub(center2);
		return (Math.abs(minus.getX()) < MAX && Math.abs(minus.getY()) < MAX);
	}
	
	public boolean isInside(Vector3i key) {
		Vector3i minus = key.sub(center3);
		return (Math.abs(minus.getX()) < MAX && Math.abs(minus.getY()) < MAX && Math.abs(minus.getZ()) < MAX);
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof NullPrototypeRegion) {
			NullPrototypeRegion other = (NullPrototypeRegion) object;
			if(other.hashCode()==this.hash) { return true; }
		}
		return false;
	}
	@Override
	public String toString() {
		return center2.toString();
	}
	
	public int hashCode() {
		return hash;
	}
	public String getLocationData(Vector3i globalVector) { return center2.toString(); }

}
