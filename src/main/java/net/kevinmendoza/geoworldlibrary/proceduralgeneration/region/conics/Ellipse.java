package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifierFactory;

class Ellipse implements IConic {

	private final double xAxis;
	private final double yAxis;
	private final double xAxis2inv;
	private final double yAxis2inv;
	private final double rootMeanSquare;
	private final double inverseRootMeanSquare;
	
	private Ellipse(Builder builder) {
		xAxis = builder.getXAxis();
		yAxis = builder.getYAxis();
		xAxis2inv = 1/(xAxis*xAxis);
		yAxis2inv = 1/(yAxis*yAxis);
		rootMeanSquare = Math.sqrt(xAxis*xAxis + yAxis*yAxis);
		inverseRootMeanSquare = 1/rootMeanSquare;
	}
	@Override
	public boolean isInside(Vector2i point) {
		int x = point.getX();
		int y = point.getY();
		double conicExp = x*x*xAxis2inv + y*y*yAxis2inv;
		return (conicExp <= 1);
	}

	@Override
	public boolean isInside(Vector3i point) {
		int x = point.getX();
		int y = point.getZ();
		double conicExp = x*x*xAxis2inv + y*y*yAxis2inv;
		return (conicExp <= 1);
	}

	@Override
	public double getResidual(Vector2i point) {
		int x = point.getX();
		int y = point.getY();
		return Math.abs(x*x*xAxis2inv + y*y*yAxis2inv - 1);
	}

	@Override
	public double getResidual(Vector3i point) {
		int x = point.getX();
		int y = point.getZ();
		return Math.abs(x*x*xAxis2inv + y*y*yAxis2inv - 1);
	}
	public double getRootMeanAxis() { return this.rootMeanSquare; }
	public double getInvRootMeanAxis() { return this.inverseRootMeanSquare; }
	@Override
	public String toString() {
		return " x:" + xAxis + " y:" + yAxis;
	}

	public static class Builder implements IConicBuilder {

		private double xAxis;
		private double yAxis;
		
		public Builder setAxis(double[] axis) {
			xAxis=axis[0];yAxis=axis[1]; return this;
		}
		
		public IConic build() {
			return new Ellipse(this);
		}
		
		public double getXAxis() { return xAxis; }
		public double getYAxis() { return yAxis; }
		
	}

	
}
