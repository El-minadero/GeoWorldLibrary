package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import java.util.Random;

import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.RotationConvention;
import org.apache.commons.math3.geometry.euclidean.threed.RotationOrder;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3d;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.RandomInterface;
import net.kevinmendoza.geoworldlibrary.utilities.HashCodeOperations;
abstract class Shape2D extends Shape {

	private final Vector2i center;
	protected final Random rand;
	private final double xAxis;
	private final double yAxis;
	private boolean queried;
	
	Shape2D(Vector2i center,double theta,RegionTypes type, double xAxis, double yAxis){
		super(type);
		this.setRotation(getRotation(theta));
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.center = center;
		this.queried = false;
		this.rand = new Random(HashCodeOperations.createVectorSeed(center));
		rand.nextDouble();
	}
	
	protected abstract double getDistanceToLocalEdge(Vector2d vec);
	protected abstract Vector2d getRandLocalPoint();
	protected abstract boolean isLocallyInside(Vector2d vec);
	
	private Rotation getRotation(double theta){
		return new Rotation(RotationOrder.YXZ,RotationConvention.FRAME_TRANSFORM,theta,0,0);
	}

	public int getInt(int i) { return rand.nextInt(i); }
	public double getDouble() { return rand.nextDouble(); }

	protected final boolean isInBoundingBox(Vector2d vec) {
		double x = vec.getX();
		double y = vec.getY();
		if(-xAxis<= x && x <= xAxis)
			if(-yAxis<=y && y<=yAxis)
				return true;
		return false;
	}
	protected final boolean isInBoundingBox(Vector3d vec) {
		double x = vec.getX();
		double y = vec.getZ();
		if(-xAxis<= x && x <= xAxis)
			if(-yAxis<=y && y<=yAxis)
				return true;
		return false;
	}
	
	protected final Vector2i createOffsetPoint(Vector2d vec) {
		return new Vector2i(vec.getX() + center.getX(), vec.getY() + center.getY());
	}

	protected final Vector2i get2Center() {
		return new Vector2i(center);
	}
	
	protected final Vector2d getRelativeCoordinates(Vector2d vec) {
		return new Vector2d(vec.getX() - center.getX(), vec.getY() - center.getY());
	}
	
	protected final Vector3d getRelativeCoordinates(Vector3d vec) {
		return new Vector3d(vec.getX() - center.getX(),0, vec.getZ() - center.getY());
	}
	
	public final Vector2i getCenter() {
		return new Vector2i(center);
	}
	
	@Override 
	public int hashCode() {
		int center = this.center.hashCode()*getPrime();
		center^=getType().hashCode();
		return center;
	}
	
	@Override
	public String toString() {
		return getType().toString() + " " + center.toString();
	}
}
