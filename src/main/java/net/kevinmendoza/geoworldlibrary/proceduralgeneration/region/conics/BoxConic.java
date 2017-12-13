package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics.Ellipsoid.Builder;

class BoxConic implements IConic {

	private double y;
	private double x;
	private double x2;
	private double y2;
	private double rms;
	private double z;
	private double z2;

	public BoxConic(Builder builder) {
		x=builder.getX();
		y=builder.getY();
		z=builder.getZ();
		x2=x*2;
		z2=z*2;
		y2=y*2;
		rms = Math.sqrt(x*x+y*y+z*z);
	}

	@Override
	public boolean isInside(Vector2i podouble) {
		double xtemp = podouble.getX();
		double ytemp = podouble.getY();
		return (xtemp >= -x && xtemp <= x && ytemp >= -z && ytemp <= z);
	}

	@Override
	public boolean isInside(Vector3i podouble) {
		double xtemp = podouble.getX();
		double ytemp = podouble.getY();
		double ztemp = podouble.getZ();
		return (xtemp >= -x && xtemp <= x && ytemp >= -y && ytemp <= y &&
				ztemp <= z && ztemp >= -z);
	}

	@Override
	public double getResidual(Vector2i localPodouble) {
		double dist1 = (x -  Math.abs(localPodouble.getX()))/rms;
		double dist2 = (z -  Math.abs(localPodouble.getY()))/rms;
		if(dist1> dist2) 
			return dist1;
		else
			return dist2;
	}

	@Override
	public double getResidual(Vector3i localPodouble) {
		double dist1 = (x -  Math.abs(localPodouble.getX()))/rms;
		double dist2 = (y -  Math.abs(localPodouble.getY()))/rms;
		double dist3 = (z -  Math.abs(localPodouble.getZ()))/rms;
		if(dist1> dist2 && dist1 >dist3) 
			return dist1;
		else if(dist2 >dist1 && dist2 > dist3)
			return dist2;
		else
			return dist3;
	}

	
	public static class Builder implements IConicBuilder {

		private double x;
		private double y;
		private double z;

		public Builder setAxis(double[] axes) {
			x=axes[0];y=axes[1];z=axes[2]; return this;
		}
		public double getX() { return x; }
		public double getY() { return y; }
		public double getZ() { return z; }
		public Builder setYDim(double i) { y=i; return this; }
		
		public IConic build() {
			return new BoxConic(this);
		}

	}

}
