package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import java.util.Random;

import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.RotationConvention;
import org.apache.commons.math3.geometry.euclidean.threed.RotationOrder;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.RandomInterface;
 
abstract class Shape implements Region,RandomInterface {
	protected final int PRIME = 41;
	private final RegionTypes type;
	private Rotation rotation;
	private final double yVal;
	
	Shape(RegionTypes type,double yVal){
		this.type = type;
		this.rotation =initEmptyRotation();
		this.yVal = yVal;
	}
	Shape(RegionTypes type){
		this.type = type;
		this.rotation =initEmptyRotation();
		this.yVal = 0;
	}
	
	private Rotation initEmptyRotation() {
		return new Rotation(RotationOrder.YXZ,RotationConvention.FRAME_TRANSFORM,0,0,0);
	}
	protected void setRotation(Rotation rot) {
		this.rotation = rot;
	}
	public abstract int getInt(int i);
	public abstract double getDouble();
	
	protected abstract boolean isInBoundingBox(Vector2d vec);
	protected abstract boolean isInBoundingBox(Vector3d vec);

	protected abstract Vector2i createOffsetPoint(Vector2d vec);
	protected abstract Vector2d getRandLocalPoint();
	
	protected abstract boolean isLocallyInside(Vector3d vec);
	protected abstract boolean isLocallyInside(Vector2d vec);
	
	protected abstract Vector2d getRelativeCoordinates(Vector2d vec);
	protected abstract Vector3d getRelativeCoordinates(Vector3d vec);
	
	protected abstract double getDistanceToLocalEdge(Vector3d vec);
	protected abstract double getDistanceToLocalEdge(Vector2d vec);

	public abstract int hashCode();
	public abstract String toString();
	
	protected final Vector2d getRotatedCoordinates(Vector2d vec) {
		double[] tempVec = { vec.getX(),yVal,vec.getY() };
		rotation.applyTo(tempVec, tempVec);
		return new Vector2d(tempVec[0],tempVec[2]);
	}
	protected final Vector3d getRotatedCoordinates(Vector3d vec) {
		double[] tempVec = { vec.getX(),vec.getY(),vec.getZ() };
		rotation.applyTo(tempVec, tempVec);
		return new Vector3d(tempVec[0],tempVec[1],tempVec[2]);
	}
	
	protected final Vector2d getReverseRotatedCoordinates(Vector2d vec) {
		double[] tempVec = { vec.getX(),yVal,vec.getY() };
		rotation.applyInverseTo(tempVec, tempVec);
		return new Vector2d(tempVec[0],tempVec[2]);
	}
	protected final Vector3d getReverseRotatedCoordinates(Vector3d vec) {
		double[] tempVec = { vec.getX(),vec.getY(),vec.getZ() };
		rotation.applyInverseTo(tempVec, tempVec);
		return new Vector3d(tempVec[0],tempVec[1],tempVec[2]);
	}

	public final RegionTypes getType() { return type; }
	protected final int getPrime() { return PRIME; }
	
	public final  boolean isInside(Vector2i vec) {
		Vector2d relative = getRelativeCoordinates(vec.toDouble());
		Vector2d rotated  = getRotatedCoordinates(relative);
		if(isInBoundingBox(rotated))
			return isLocallyInside(rotated); 
		else
			return false;
	}
	public final  boolean isInside(Vector3i vec) {
		Vector3d relative = getRelativeCoordinates(vec.toDouble());
		Vector3d rotated  = getRotatedCoordinates(relative);
		if(isInBoundingBox(rotated))
			return isLocallyInside(rotated); 
		else
			return false;
	}
	
	public final Vector2i getRandomInternalPoint() {
		Vector2d relative = getRandLocalPoint();
		Vector2d rotated  = getReverseRotatedCoordinates(relative);
		return createOffsetPoint(rotated);
	}
	
	public final double getNormalizedDistanceToEdge(Vector2i vec) {
		Vector2d relative = getRelativeCoordinates(vec.toDouble());
		Vector2d rotated  = getRotatedCoordinates(relative);
		return getDistanceToLocalEdge(rotated);
	}
	
	public final double getNormalizedDistanceToEdge(Vector3i vec) {
		Vector3d relative = getRelativeCoordinates(vec.toDouble());
		Vector3d rotated  = getRotatedCoordinates(relative);
		return getDistanceToLocalEdge(rotated);
	}
	
	@Override
	public Vector2i getModifiedPoint(Vector2i vec) {
		return vec;
	}

	@Override
	public Vector3i getModifiedPoint(Vector3i vec) {
		return vec;
	}
	
}
