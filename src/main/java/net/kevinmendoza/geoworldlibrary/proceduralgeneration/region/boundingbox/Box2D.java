package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingbox;

import java.util.Random;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingbox.Rectangle.RecBuilder;

class Box2D extends Rectangle implements IBoundingBox {

	
	private Box2D(Builder builder) {
		super(builder);
	}
	public boolean isOverXDim(Vector3i vec) { return !isCoordinateInBounds(vec.getX(),0); }
	public boolean isOverYDim(Vector3i vec) { return false; }
	public boolean isOverZDim(Vector3i vec) { return !isCoordinateInBounds(vec.getZ(),1); }
	
	public boolean isOverXDim(Vector2i vec) { return !isCoordinateInBounds(vec.getX(),0); }
	public boolean isOverYDim(Vector2i vec) { return false; }
	public boolean isOverZDim(Vector2i vec) { return !isCoordinateInBounds(vec.getY(),1); }
	public double getArea() { return getDim(1)*getDim(0)*4;  }
	
	@Override
	public Vector2i getRandomInternalPoint2i() {
		return new Vector2i(getRandomCoordinateInBounds(0),
							getRandomCoordinateInBounds(1));
	}

	@Override
	public Vector3i getRandomInternalPoint3i() {
		return new Vector3i(getRandomCoordinateInBounds(0),
							0,
							getRandomCoordinateInBounds(1));
	}

	@Override
	public boolean isInside(Vector2i point) {
		return (isCoordinateInBounds(point.getX(), 0) && 
				isCoordinateInBounds(point.getY(), 1));
	}

	@Override
	public boolean isInside(Vector3i point) {
		return (isCoordinateInBounds(point.getX(), 0) && 
				isCoordinateInBounds(point.getZ(), 1));
	}

	
	public static class Builder implements IBoxBuilder {

		private int[] axis;
		private Random rand;
		
		public Builder setAxis(int[] axis) { this.axis=axis; return this; }
		public Builder setRandom(Random rand) {this.rand = rand; return this; }
		public IBoundingBox build() {
			return new Box2D(this);
		}
		
		public int[] getAxis() { return axis;  }
		public Random getRandom() { return rand; }
		
	}

}
