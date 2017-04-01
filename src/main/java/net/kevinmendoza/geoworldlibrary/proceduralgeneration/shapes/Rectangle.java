package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifierFactory;

class Rectangle extends Shape2D {

	private final double a;
	private final double b;
	private final double a_2;
	private final double b_2;

	Rectangle(Vector2i center,double xAxis, double yAxis, double angleToNorth) {
		super(center,angleToNorth,RegionTypes.RECTANGLE,xAxis,yAxis);
		a = xAxis;
		b = yAxis;
		a_2 = xAxis/2;
		b_2 = yAxis/2;
		
	}
	
	protected double getNormalizedDistance() {
		return (a+b)/2;
	}
	
	protected double getDistanceToLocalEdge(Vector2d vec) {
		double dx = Math.abs(a_2 - Math.abs(vec.getX()));
		double dz = Math.abs(b_2 - Math.abs(vec.getY()));
		if (dx > dz)
			return dz/b_2;
		else
			return dx/a_2;
	}

	@Override
	protected Vector2d getRandLocalPoint() {
		Vector2d vec = new Vector2d(-a_2 + this.getDouble()*a,-b_2 + this.getDouble()*b);
		return vec;
	}

	@Override
	protected boolean isLocallyInside(Vector2d vec) {
		double x = Math.abs(vec.getX());
		double y = Math.abs(vec.getY());
		return (x < a_2 && y < b_2);
	}
	
	@Override
    public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Rectangle)) {
			return false;
		}
		Rectangle user = (Rectangle) o;
		return user.hashCode() == user.hashCode();
	}

	@Override
	protected boolean isLocallyInside(Vector3d vec) {
		return isLocallyInside(new Vector2d(vec.getX(),vec.getZ()));
	}

	@Override
	protected double getDistanceToLocalEdge(Vector3d vec) {
		return getDistanceToLocalEdge(new Vector2d(vec.getX(),vec.getY()));
	}
	
}
