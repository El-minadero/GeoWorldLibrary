package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.RectangleConic.Builder;

public class RectangularPrismConic implements IConic {

	private int y;
	private int x;
	private int x2;
	private int y2;
	private double rms;
	private int z;
	private int z2;

	public RectangularPrismConic(Builder builder) {
		x=builder.getX();
		y=builder.getY();
		z=builder.getZ();
		x2=x*2;
		z2=z*2;
		y2=y*2;
		rms = Math.sqrt(x*x+y*y+z*z);
	}

	@Override
	public boolean isInside(Vector2i point) {
		int xtemp = point.getX();
		int ytemp = point.getY();
		return (xtemp > -x && xtemp < x && ytemp > -z && ytemp < z);
	}

	@Override
	public boolean isInside(Vector3i point) {
		int xtemp = point.getX();
		int ytemp = point.getY();
		int ztemp = point.getZ();
		return (xtemp > -x && xtemp < x && ytemp > -y && ytemp < y &&
				ztemp < z && ztemp > -z);
	}

	@Override
	public double getResidual(Vector2i localPoint) {
		int dist1 = x -  Math.abs(localPoint.getX());
		int dist2 = z -  Math.abs(localPoint.getY());
		if(dist1< dist2) 
			return dist1/x;
		else
			return dist2/z;
	}

	@Override
	public double getResidual(Vector3i localPoint) {
		int dist1 = x -  Math.abs(localPoint.getX());
		int dist2 = y -  Math.abs(localPoint.getY());
		int dist3 = z -  Math.abs(localPoint.getZ());
		if(dist1< dist2 && dist1 < dist3) 
			return dist1;
		else if(dist2 < dist1 && dist2 < dist3)
			return dist2;
		else
			return dist3;
	}

	@Override
	public double getRootMeanAxis() {
		return rms;
	}

	@Override
	public double getInvRootMeanAxis() {
		return 1/rms;
	}
	
	public static class Builder {

		private int x;
		private int y;
		private int z;

		public Builder setXDim(int i) { x=i; return this; }
		public Builder setZDim(int i) { z=i; return this; }
		public int getX() { return x; }
		public int getY() { return y; }
		public int getZ() { return z; }
		public Builder setYDim(int i) { y=i; return this; }
		
		public IConic build() {
			return new RectangularPrismConic(this);
		}

	}

}
