package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;

public class Ellipsoid extends Shape3D {

	private final double a;
	private final double b;
	private final double c;
	private final Vector3i center;
	private double a_2;
	private double b_2;
	private double c_2;
	
	Ellipsoid(Vector3i center, double a,double b,double c) {
		super(center, RegionTypes.ELLIPSOID,a,b,c);
		this.center = center;
		this.a = a;
		this.b = b;
		this.c = c;
		this.a_2 = a;
		this.b_2 = b;
		this.c_2 = c;
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
			double ap = getDouble()*a_2 - a;
			double bp = getDouble()*b_2 - b;
			vec= new Vector2d(ap,bp);
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
        if (!(o instanceof Ellipsoid)) {
            return false;
        }
       	Ellipsoid user = (Ellipsoid) o;
        return user.hashCode()==user.hashCode();
    }

	@Override
	protected boolean isLocallyInside(Vector3d vec) {
		double x =vec.getX()/a;
		double y =vec.getY()/b;
		double z =vec.getZ()/c;
		return (x*x + y*y + z*z <= 1);
	}

	@Override
	protected double getDistanceToLocalEdge(Vector3d vec) {
		double x = vec.getX()/a;
		double y = vec.getY()/b;
		double z = vec.getZ()/c;
		return Math.abs(1 - (x*x + z*z + y*y));
	}


	

}
