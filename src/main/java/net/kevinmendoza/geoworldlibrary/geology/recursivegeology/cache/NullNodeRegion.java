package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;
import com.typesafe.config.ConfigException.Null;

public class NullNodeRegion implements INodeRegion {

	private final INodeRegion region;
	
	NullNodeRegion(INodeRegion region) {
		this.region = region;
	}
	
	public final boolean isLeaf() {	return false; }

	public boolean isInside(Vector2i key) 				{ return region.isInside(key); 					}
	public boolean isInside(Vector3i key) 				{ return region.isInside(key);					}
	public double getCenterDistance(Vector3i vec3) 		{ return region.getCenterDistance(vec3); 		}
	public double getCenterDistance(Vector2i vec2) 		{ return region.getCenterDistance(vec2);			}
	public String getLocationData(Vector3i globalVector) { return region.getLocationData(globalVector); 	}
	@Override 
	public boolean equals(Object object) {
		if(object instanceof NullPrototypeRegion || object instanceof NullNodeRegion) {
			INodeRegion other = (INodeRegion)object;
			if(other.hashCode() == region.hashCode()) {	return true;	 }
		}
		return false;
	}
	@Override
	public String toString() {
		return region.toString();
	}
	@Override
	public int hashCode() {
		return region.hashCode();
	}

	
}
