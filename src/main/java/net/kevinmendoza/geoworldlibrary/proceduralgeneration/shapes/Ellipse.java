package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;

class Ellipse extends Shape implements Region {

	private final double a;
	private final double b;

	Ellipse(Vector2i center, double majorAxis, double minorAxis, double angleToNorth) {
		super(center, angleToNorth, RegionTypes.ELLIPSE);
		a = majorAxis;
		b = minorAxis;
	}
	
	protected double getDistanceToLocalEdge(Vector2d vec) {
		double x = Math.abs(vec.getX())/a;
		double z = Math.abs(vec.getY())/b;
		return 1 - (x*x + z*z);
	}

	@Override
	protected Vector2d getRandLocalPoint() {
		double theta = getDouble()*Math.PI*2;
		double cos = Math.cos(theta);
		double sin = Math.sin(theta);
		double x  = b*cos;
		double y  = a*sin;
		double rbound = (a*b)/ Math.sqrt(x*x + y*y);
		double rad = getDouble()*rbound;
		return new Vector2d(cos*rad,sin*rad);
	}

	@Override
	protected boolean isLocallyInside(Vector2d vec) {
		double x = Math.abs(vec.getX())/a;
		double z = Math.abs(vec.getY())/b;
		return (x*x + z*z <= 1);
	}
	
	@Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Rectangle)) {
            return false;
        }
       	Ellipse user = (Ellipse) o;
        return user.hashCode()==user.hashCode();
    }

}
