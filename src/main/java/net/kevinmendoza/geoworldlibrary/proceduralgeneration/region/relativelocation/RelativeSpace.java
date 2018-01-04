package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.relativelocation;

import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.RotationConvention;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

abstract class RelativeSpace implements IRelativeSpace {
	
	private final Rotation rotation;
	private final Vector3i center3;
	private final Vector2i center2;
	
	RelativeSpace(RelativeSpaceBuilder builder){
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
		return new Vector3i(Math.round(r[0]),Math.round(r[1]),Math.round(r[2]));
	}
	protected Vector3i applyRotation(Vector3i point) {
		double[] r = {point.getX(), point.getY(), point.getZ()};
		rotation.applyTo(r,r);
		return new Vector3i(Math.round(r[0]),Math.round(r[1]),Math.round(r[2]));
	}

	public double getDistanceToCenter(Vector3i vec) {
		return center3.distance(vec);
	}

	public double getDistanceToCenter(Vector2i vec) {
		return center2.distance(vec);
	}
	
	public Vector3i getCenter3i() { return new Vector3i(center3); }
	public Vector2i getCenter2i() { return new Vector2i(center2); }
	@Override
	public String toString() {
		Vector3D vector3D = rotation.getAxis(RotationConvention.VECTOR_OPERATOR);
		double angle = Math.floor(Math.toDegrees(rotation.getAngle())*100)/100;
		return "======= Relative Coordinate Transform ======" + 
				"rotation:\n"  + 
				"rotation Vector:" + vector3D.toString() +"\n" +
				"rotation Angle:" + angle + "\n" 
				+"Center Coordinates (3d):" + center3.toString();
	}
 
}

