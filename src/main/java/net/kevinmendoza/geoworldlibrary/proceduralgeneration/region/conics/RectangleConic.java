package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics;

import java.util.Random;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

class RectangleConic implements IConic {

	private double y;
	private double x;
	private double x2;
	private double y2;
	private double rms;

	public RectangleConic(Builder builder) {
		x=builder.getXAxis();
		y=builder.getYAxis();
		x2=x*2;
		y2=y*2;
		rms = Math.sqrt(x*x+y*y);
	}

	@Override
	public boolean isInside(Vector2i point) {
		int xtemp = point.getX();
		int ytemp = point.getY();
		return (xtemp >= -x && xtemp <= x && ytemp >= -y && ytemp <= y);
	}

	@Override
	public boolean isInside(Vector3i point) {
		int xtemp = point.getX();
		int ytemp = point.getZ();
		return (xtemp >= -x && xtemp <= x && ytemp >= -y && ytemp <= y);
	}

	@Override
	public double getResidual(Vector2i localPoint) {
		double dist1 = (x -  Math.abs(localPoint.getX()))/rms;
		double dist2 = (y -  Math.abs(localPoint.getY()))/rms;
		if(dist1> dist2) 
			return dist1;
		else
			return dist2;
	}

	@Override
	public double getResidual(Vector3i localPoint) {
		double dist1 = (x -  Math.abs(localPoint.getX()))/rms;
		double dist2 = (y -  Math.abs(localPoint.getZ()))/rms;
		if(dist1> dist2) 
			return dist1;
		else
			return dist2;
	}


	@Override
	public String toString() {
		return "x:" + x + "y:" + y;
	}
	
	public static class Builder implements IConicBuilder {

		private double xAxis;
		private double yAxis;
		
		public Builder setAxis(double[] axis) {
			xAxis=axis[0];yAxis=axis[1]; return this;
		}
		
		public IConic build() {
			return new RectangleConic(this);
		}
		
		public double getXAxis() { return xAxis; }
		public double getYAxis() { return yAxis; }
	}

}
