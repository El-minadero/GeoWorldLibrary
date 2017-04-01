package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifierFactory;

class Ellipse extends Shape2D {

	private final double a;
	private final double b;
	private final double a_2;
	private final double b_2;
	private final double a2;
	private final double b2;

	Ellipse(Vector2i center, double majorAxis, double minorAxis, double angleToNorth) {
		super(center, angleToNorth, RegionTypes.ELLIPSE,majorAxis,minorAxis);
		a = majorAxis;
		b = minorAxis;
		a_2 = a*2;
		b_2  = b*2;
		a2 = a*a;
		b2 = b*b;
	}

	
	protected double getDistanceToLocalEdge(Vector2d vec) {
		double x = vec.getX()/a;
		double z = vec.getY()/b;
		return Math.abs(1 - (x*x + z*z));
	}

	@Override
	protected Vector2d getRandLocalPoint() {
		Vector2d vec;
		while(true) {
			double o,p;
			o = rand.nextGaussian()*a*0.5;
			p = rand.nextGaussian()*b*0.5;
			vec = new Vector2d(o,p);
			if(isLocallyInside(vec))
				break;
		}
		return vec;
	}
	@Override
	protected boolean isLocallyInside(Vector2d vec) {
		double x =vec.getX()/a;
		double z =vec.getY()/b;
		return (x*x + z*z <= 1);
	}
	
	
	@Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Ellipse)) {
            return false;
        }
       	Ellipse user = (Ellipse) o;
        return user.hashCode()==user.hashCode();
    }


	@Override
	protected boolean isLocallyInside(Vector3d vec) {
		return isLocallyInside(new Vector2d(vec.getX(),vec.getZ()));
	}


	@Override
	protected double getDistanceToLocalEdge(Vector3d vec) {
		return getDistanceToLocalEdge(new Vector2d(vec.getX(), vec.getZ()));
	}


	
}
