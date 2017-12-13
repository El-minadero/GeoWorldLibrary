package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

class Ellipsoid implements IConic {

	private final double xAxis;
	private final double yAxis;
	private final double zAxis;
	private final double xAxis2inv;
	private final double yAxis2inv;
	private final double zAxis2inv;
	private final double rootMeanSquare;
	private final double inverseRootMeanSquare;
	
	private Ellipsoid(Builder builder) {
		xAxis = builder.getXAxis();
		yAxis = builder.getYAxis();
		zAxis = builder.getZAxis();
		xAxis2inv = 1/(xAxis*xAxis);
		yAxis2inv = 1/(yAxis*yAxis);
		zAxis2inv = 1/(zAxis*zAxis);
		rootMeanSquare = Math.sqrt(xAxis*xAxis + yAxis*yAxis + zAxis*zAxis);
		inverseRootMeanSquare = 1/rootMeanSquare;
	}
	@Override
	public boolean isInside(Vector2i point) {
		int x = point.getX();
		int y = point.getY();
		double val = x*x*xAxis2inv + y*y*zAxis2inv;
		return (val <= 1);
	}

	@Override
	public boolean isInside(Vector3i point) {
		int x = point.getX();
		int y = point.getY();
		int z = point.getZ();
		return (x*x*xAxis2inv + y*y*yAxis2inv + z*z*zAxis2inv <= 1);
	}

	@Override
	public double getResidual(Vector2i point) {
		int x = point.getX();
		int z = point.getY();
		return Math.abs(x*x*xAxis2inv + z*z*zAxis2inv - 1);
	}

	@Override
	public double getResidual(Vector3i point) {
		int x = point.getX();
		int y = point.getY();
		int z = point.getZ();
		return Math.abs(x*x*xAxis2inv + y*y*yAxis2inv + z*z*zAxis2inv- 1);
	}
	public double getRootMeanAxis() { return this.rootMeanSquare; }
	public double getInvRootMeanAxis() { return this.inverseRootMeanSquare; }

	public static class Builder implements IConicBuilder {

		private double xAxis;
		private double yAxis;
		private double zAxis;
		
		public Builder setAxis(double[] axes) {
			xAxis=axes[0];yAxis=axes[1];zAxis=axes[2]; return this;
		}
		
		public IConic build() {
			return new Ellipsoid(this);
		}
		
		public double getXAxis() { return xAxis; }
		public double getYAxis() { return yAxis; }
		public double getZAxis() { return zAxis; }
		
	}

}
