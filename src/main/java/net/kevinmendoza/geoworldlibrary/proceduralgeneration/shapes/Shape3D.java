package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import java.util.Random;

import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.utilities.HashCodeOperations;

abstract class Shape3D extends Shape {

	private final Vector3i center;
	private final Rotation rotation;
	protected final Random rand;
	private double xAxis;
	private double yAxis;
	private double zAxis;
	
	Shape3D(Vector3i center,RegionTypes type,double xAxis,double yAxis, double zAxis){
		super(type,center.getY());
		this.rand = new Random(HashCodeOperations.createVectorSeed(center));
		rand.nextDouble();
		this.setRotation(createRotationMatrix());
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.zAxis = zAxis;
		this.center = center;
		this.rotation = createRotationMatrix();
	}
	
	private Rotation createRotationMatrix() {
		double b = rand.nextDouble()*2 - 1;
		double c = rand.nextDouble()*2 - 1;
		double d = rand.nextDouble()*2 - 1;
		double s = rand.nextDouble()*Math.PI*2;
		double sin = Math.sin(s/2);
		double a = Math.cos(s/2);
		b*=sin;
		c*=sin;
		d*=sin;
		return new Rotation(a,b,c,d,true);
	}
	
	protected final boolean isInBoundingBox(Vector2d vec) {
		double x = vec.getX();
		double y = vec.getY();
		if(-xAxis<= x && x <= xAxis)
			if(-zAxis<=y && y<=zAxis)
				return true;
		return false;
	}
	protected final boolean isInBoundingBox(Vector3d vec) {
		double x = vec.getX();
		double y = vec.getY();
		double z = vec.getZ();
		if(-xAxis<= x && x <= xAxis)
			if(-yAxis<=y && y<=yAxis)
				if(-zAxis<=z && z<=zAxis)
					return true;
		return false;
	}
	
	public int getInt(int i) { return rand.nextInt(i); }
	public double getDouble() { return rand.nextDouble(); }
	
	protected Vector2i createOffsetPoint(Vector2d vec) {
		return new Vector2i(vec.getX() + center.getX(),vec.getY()+center.getZ());
	}

	protected abstract double getDistanceToLocalEdge(Vector2d vec);
	protected abstract Vector2d getRandLocalPoint();
	protected abstract boolean isLocallyInside(Vector2d vec);
	
	protected final Vector2i get2Center() {
		return new Vector2i(center.getX(),center.getZ());
	}
	protected final Vector3i get3Center() {
		return new Vector3i(center);
	}
	
	protected final Vector2d getRelativeCoordinates(Vector2d vec) {
		return new Vector2d(vec.getX() - center.getX(), vec.getY() - center.getZ());
	}
	
	protected final Vector3d getRelativeCoordinates(Vector3d vec) {
		return new Vector3d(vec.getX() - center.getX(), vec.getY() - center.getY(), vec.getZ() - center.getZ());
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
