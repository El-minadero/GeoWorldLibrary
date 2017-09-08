package net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

class PointGenerator implements IPointGenerator {

	private static long PRIME = 7;
	private final double SPACING;
	private final long SEED;
	private static final int[] X = {0,-1,-1,-1, 0, 1,1,1,-2,-2,-2 ,2,2,2 , -1,0,1 -1,0,1  };
	private static final int[] Z = {1, 1, 0,-1,-1,-1,0,1,-1,0,1   ,-1,0,1,  2,2,2,-2,-2,-2};
	
	private PointGenerator(PointGeneratorBuilder pointGeneratorBuilder) {
		SPACING = pointGeneratorBuilder.spacing;
		SEED = pointGeneratorBuilder.seed;
	}
	@Override
	public List<Vector2i> getFlooredCenterNeighborhood(Vector2i vec) {
		Vector2i center = new Vector2i(Math.floor((double)(vec.getX())/SPACING),
									   Math.floor((double)(vec.getY())/SPACING));
		List<Vector2i> surroundingCenters = new ArrayList<Vector2i>(8);
		surroundingCenters.add(center);
		for(int i =0;i<19;i++){
			surroundingCenters.add(new Vector2i(X[i]+center.getX(),Z[i]+center.getY()));
		}
		return surroundingCenters;
	}
	
	@Override
	public List<Vector2i> getFullCenterNeighborhood(Vector2i vec) {
		List<Vector2i> centers = getFlooredCenterNeighborhood(vec);
		List<Vector2i> surroundingPoints = new ArrayList<Vector2i>(8);
		for(Vector2i cent : centers){
			surroundingPoints.add(getFullCenter(cent));
		}
		return surroundingPoints;
	}
	
	@Override
	public Vector2i getFullCenter(Vector2i vec) {
		long i = (vec.getX() * 661) + (vec.getY() * 701) % (1024+ SEED);
		Random rand = new Random(i);
		rand.nextDouble();
		return new Vector2i(rand.nextDouble()*SPACING + vec.getX()*SPACING,
										   rand.nextDouble()*SPACING + vec.getY()*SPACING);
	}
	
	public static class PointGeneratorBuilder {
		
		private int spacing;
		private long seed;

		public PointGeneratorBuilder() {
			spacing = 50;
			seed = 1;
		}
		public PointGeneratorBuilder withSpacing(int spacing) {
			this.spacing = spacing;
			return this;
		}
		public PointGeneratorBuilder withSeed(long seed) {
			this.seed = seed;
			return this;
		}
		public PointGenerator build() {
			return new PointGenerator(this);
		}
	}

	@Override
	public int getRGBDebugVal(Vector3i query) {
		int rgb=0;
		int x = (int) (query.getX()%SPACING);
		int y = (int) (query.getZ()%SPACING);
		if(x==0 || y==0) {
			rgb = Color.WHITE.getRGB();
		}
		return rgb;
	}
}
