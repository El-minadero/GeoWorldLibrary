package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.relativelocation;

import org.apache.commons.math3.geometry.euclidean.threed.Rotation;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

class RelativeSpaceBuilder {
	private Vector3i center3;
	private Vector2i center2;
	private Rotation rotation;
	
	public RelativeSpaceBuilder setCenter(Vector2i center) { this.center2 = center; return this; }
	public RelativeSpaceBuilder setCenter(Vector3i center) { this.center3 = center; return this; }
	public RelativeSpaceBuilder setRotation(Rotation rotation) { this.rotation = rotation; return this; }
	
	public Vector3i get3iCenter() { return center3; }
	public Vector2i get2iCenter() { return center2; }
	public Rotation getRotation() { return rotation;}
	
	IRelativeSpace build2DLocation() {
		center3 = new Vector3i(center2.getX(),0,center2.getY());
		return new RelativeSpace2D(this);
	}
	IRelativeSpace build3DLocation() {
		center2 = new Vector2i(center3.getX(),center3.getZ());
		return new RelativeSpace3D(this);
	}
	
	
}
