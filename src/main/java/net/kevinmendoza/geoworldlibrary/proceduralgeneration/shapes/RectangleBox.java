package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import java.util.Random;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public class RectangleBox extends Rectangle implements IBoundingBox {

	
	private RectangleBox(Builder builder) {
		super(builder);
	}
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

	
	public static class Builder implements RecBuilder {

		private int xDim;
		private int yDim;
		private Random rand;
		
		public Builder setXDim(int x) { xDim=x; return this; }
		public Builder setYDim(int y) { yDim=y; return this; }
		public Builder setRandom(Random rand) {this.rand = rand; return this; }
		public IBoundingBox build() {
			return new RectangleBox(this);
		}
		
		public int[] getDim2Array() { 
			int[] a = {xDim*2,yDim*2};
			return a; 
		}
		public int[] getDimArray() {
			int[] a = {xDim,yDim};
			return a;
		}
		public Random getRand() { return rand; }
		
	}

}
