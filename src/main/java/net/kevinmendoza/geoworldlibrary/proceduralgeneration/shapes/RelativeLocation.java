package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import org.apache.commons.math3.geometry.euclidean.threed.Rotation;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

abstract class RelativeLocation implements IRelativePointModifier {
	
	private final Rotation rotation;
	private final Vector3i center3;
	private final Vector2i center2;
	
	RelativeLocation(Builder builder){
		rotation = builder.getRotation();
		center3  = builder.get3iCenter();
		center2  = builder.get2iCenter();
	}
	
	protected Vector3i getFast3iCenter() { return center3; }
	protected Vector2i getFast2iCenter() { return center2; }
	
	protected Vector3i addToCenter(Vector3i localPoint) { return localPoint.add(center3); }
	protected Vector3i subtractCenter(Vector3i localPoint) { return localPoint.sub(center3); }
	
	protected Vector3i applyInverseRotation(Vector3i point) {
		double[] r = {point.getX(), point.getY(), point.getZ()};
		rotation.applyInverseTo(r,r);
		return new Vector3i(r[0],r[1],r[2]);
	}
	protected Vector3i applyRotation(Vector3i point) {
		double[] r = {point.getX(), point.getY(), point.getZ()};
		rotation.applyTo(r,r);
		return new Vector3i(r[0],r[1],r[2]);
	}

	public Vector3i getGlobalCenter3i() { return new Vector3i(center3); }
	public Vector2i getGlobalCenter2i() { return new Vector2i(center2); }
	@Override
	public String toString() {
		return "rotation:" + rotation.toString() + " vi3" + center3.toString() + " vi2:" + center2.toString();
	}
 	
	public static class Builder {
		private Vector3i center3;
		private Vector2i center2;
		private Rotation rotation;
		
		public Builder setCenter(Vector2i center) { this.center2 = center; return this; }
		public Builder setCenter(Vector3i center) { this.center3 = center; return this; }
		public Builder setRotation(Rotation rotation) { this.rotation = rotation; return this; }
		
		public Vector3i get3iCenter() { return center3; }
		public Vector2i get2iCenter() { return center2; }
		public Rotation getRotation() { return rotation;}
		
		IRelativePointModifier build2DLocation() {
			center3 = new Vector3i(center2.getX(),0,center2.getY());
			return new RelativeLocation2D(this);
		}
		IRelativePointModifier build3DLocation() {
			center2 = new Vector2i(center3.getX(),center3.getZ());
			return new RelativeLocation3D(this);
		}
		
		
	}
}

