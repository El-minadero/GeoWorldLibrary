package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import java.util.Random;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.RandomInterface;
abstract class Shape implements RandomInterface {

	private final int PRIME = 41;
	private final Vector2i center;
	private final double cos;
	private final double sin;
	private final double rcos;
	private final double rsin;
	private final RegionTypes type;
	protected final Random rand;
	
	Shape(Vector2i center,double rotation,RegionTypes type){
		this.type = type;
		this.center = center;
		this.rand = new Random(center.hashCode()^type.ordinal());
		this.cos = Math.cos(rotation);
		this.sin = Math.sin(rotation);
		this.rcos = Math.cos(-rotation);
		this.rsin = Math.sin(-rotation);
	}
	
	public int getInt(int i) { return rand.nextInt(i); }
	public double getDouble() { return rand.nextDouble(); }
	public double getCenterDistance(Vector2i test) { return center.distance(test); }
	
	public boolean isInside(Vector2i vec) {
		Vector2d relative = getRelativeCoordinates(vec.toDouble());
		Vector2d rotated  = getRotatedCoordinates(relative);
		return isLocallyInside(rotated); 
	}
	
	public Vector2i getRandomInternalPoint() {
		Vector2d relative = getRandLocalPoint();
		Vector2d rotated  = getRotatedCoordinates(relative);
		return new Vector2i(rotated.getX()+center.getX(),rotated.getY() + center.getY()); 
	}
	
	public double distanceToEdge(Vector2i vec) {
		Vector2d relative = getRelativeCoordinates(vec.toDouble());
		Vector2d rotated  = getReverseRotatedCoordinates(relative);
		return getDistanceToLocalEdge(rotated);
	}
	
	protected abstract double getDistanceToLocalEdge(Vector2d vec);
	protected abstract Vector2d getRandLocalPoint();
	protected abstract boolean isLocallyInside(Vector2d vec);
	
	protected Vector2i getCenter() {
		return new Vector2i(center);
	}
	
	private Vector2d getRelativeCoordinates(Vector2d vec) {
		return new Vector2d(vec.getX() - center.getX(), vec.getY() - center.getY());
	}

	private Vector2d getRotatedCoordinates(Vector2d vec) {
		double x = vec.getX();
		double z = vec.getY();
		x = x*cos - z*sin;
		z = x*sin + z*cos;
		return new Vector2d(x,z);
	}
	
	private Vector2d getReverseRotatedCoordinates(Vector2d vec) {
		double x = vec.getX();
		double z = vec.getY();
		x = x*rcos - z*rsin;
		z = x*rsin + z*rcos;
		return new Vector2d(x,z);
	}
	
	
	@Override 
	public int hashCode() {
		int center = this.center.hashCode()*PRIME;
		center^=type.hashCode();
		return center;
	}
	
	@Override
	public String toString() {
		return type.toString() + " " + center.toString();
	}
}
