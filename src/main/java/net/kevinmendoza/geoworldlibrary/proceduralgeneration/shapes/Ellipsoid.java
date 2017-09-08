package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public class Ellipsoid implements IConic {

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
		zAxis = builder.getYAxis();
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
		return (x*x*xAxis2inv + y*y*zAxis2inv < 1);
	}

	@Override
	public boolean isInside(Vector3i point) {
		int x = point.getX();
		int y = point.getY();
		int z = point.getZ();
		return (x*x*xAxis2inv + y*y*yAxis2inv + z*z*zAxis2inv < 1);
	}

	@Override
	public double getResidual(Vector2i point) {
		int x = point.getX();
		int y = point.getY();
		return x*x*xAxis2inv + y*y*yAxis2inv - 1;
	}

	@Override
	public double getResidual(Vector3i point) {
		int x = point.getX();
		int y = point.getY();
		int z = point.getY();
		return x*x*xAxis2inv + y*y*yAxis2inv + z*z*zAxis2inv- 1;
	}
	public double getRootMeanAxis() { return this.rootMeanSquare; }
	public double getInvRootMeanAxis() { return this.inverseRootMeanSquare; }

	public static class Builder {

		private double xAxis;
		private double yAxis;
		private double zAxis;
		
		public Builder setXAxis(double x) { this.xAxis=x; return this; }
		public Builder setYAxis(double y) { this.yAxis=y; return this; }
		public Builder setZAxis(double z) { this.zAxis=z; return this; }
		
		public IConic build() {
			return new Ellipsoid(this);
		}
		
		public double getXAxis() { return xAxis; }
		public double getYAxis() { return yAxis; }
		public double getZAxis() { return zAxis; }
		
	}

}
