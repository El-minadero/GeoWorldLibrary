package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import java.util.Random;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.RectangleBox.Builder;

class RectangleConic implements IConic {

	private int y;
	private int x;
	private int x2;
	private int y2;
	private double rms;

	public RectangleConic(Builder builder) {
		x=builder.getX();
		y=builder.getY();
		x2=x*2;
		y2=y*2;
		rms = Math.sqrt(x*x+y*y);
	}

	@Override
	public boolean isInside(Vector2i point) {
		int xtemp = point.getX();
		int ytemp = point.getY();
		return (xtemp > -x && xtemp < x && ytemp > -y && ytemp < y);
	}

	@Override
	public boolean isInside(Vector3i point) {
		int xtemp = point.getX();
		int ytemp = point.getZ();
		return (xtemp > -x && xtemp < x && ytemp > -y && ytemp < y);
	}

	@Override
	public double getResidual(Vector2i localPoint) {
		int dist1 = x -  Math.abs(localPoint.getX());
		int dist2 = y -  Math.abs(localPoint.getY());
		if(dist1< dist2) 
			return dist1/x;
		else
			return dist2/y;
	}

	@Override
	public double getResidual(Vector3i localPoint) {
		int dist1 = x -  Math.abs(localPoint.getX());
		int dist2 = y -  Math.abs(localPoint.getZ());
		if(dist1< dist2) 
			return dist1;
		else
			return dist2;
	}

	@Override
	public double getRootMeanAxis() {
		return rms;
	}

	@Override
	public double getInvRootMeanAxis() {
		return 1/rms;
	}
	@Override
	public String toString() {
		return "x:" + x + "y:" + y;
	}
	
	public static class Builder {

		private int x;
		private int y;

		public Builder setXDim(int i) { x=i; return this; }
		public int getX() { return x; }
		public int getY() { return y; }
		public Builder setYDim(int i) { y=i; return this; }
		
		public IConic build() {
			return new RectangleConic(this);
		}

	}

}
