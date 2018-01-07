package net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.utilities.HashCodeOperations;

abstract class AbstractGeneratorSettings {

	private final double SPACING;
	private final double RANDOM_SPACING;
	private final double STATIC_OFFSET;
	private long SEED;
	private final int RADIUS;
	private static final int[] X1 = {0,-1,-1,-1, 0, 1,1,1,};
	private static final int[] Z1 = {1, 1, 0,-1,-1,-1,0,1,};
	private static final int[] X2 = {0,-1,-1,-1, 0, 1,1,1,-2,-2,-2 ,2,2,2 , -1,0,1 -1,0,1  };
	private static final int[] Z2 = {1, 1, 0,-1,-1,-1,0,1,-1,0,1   ,-1,0,1,  2,2,2,-2,-2,-2};
	
	AbstractGeneratorSettings(PointGeneratorBuilder pointGeneratorBuilder) {
		RADIUS			= pointGeneratorBuilder.getRadius();
		SPACING			= pointGeneratorBuilder.getSpacing();
		double ratio	   	= pointGeneratorBuilder.getRandomRatio();
		SEED 			= pointGeneratorBuilder.getSeed();
		RANDOM_SPACING = SPACING*ratio;
		STATIC_OFFSET  = SPACING*(1-ratio);
	}
	
	public int getRGBDebugVal(Vector3i query) {
		int rgb=0;
		int x = (int) (query.getX()%SPACING);
		int y = (int) (query.getZ()%SPACING);
		if(x==0 || y==0) {
			rgb = Color.WHITE.getRGB();
		}
		return rgb;
	}
	final long getSeed() { return SEED; }
	public final void setSeed(long seed2) { this.SEED = seed2; }
	
	protected int getNeighborLength(){ 
		if (RADIUS ==1)
			return X1.length; 
		else
			return X2.length;
	}
	protected int getX(int index) { 
		if (RADIUS ==1)
			return X1[index]; 
		else
			return X2[index];
	}
	protected int getZ(int index) { 
		if (RADIUS ==1)
			return Z1[index]; 
		else
			return Z2[index];
	}
	
	public Vector2i getFlooredVector(Vector2i vec) {
		Vector2i center = new Vector2i(Math.floor((double)(vec.getX())/SPACING),
				   Math.floor((double)(vec.getY())/SPACING));
		return center;
	}
	
	public final Set<Vector2i> getFullVectors(Vector2i vec,int amount) {
		Set<Vector2i> vectorSet = new HashSet<Vector2i>();
		Random rand  = HashCodeOperations.createVectorRandom(vec, SEED);
		for(int i =0;i<amount;i++) {
			int x = (int) (STATIC_OFFSET + rand.nextDouble()*RANDOM_SPACING + SPACING*vec.getX());
			int y = (int) (STATIC_OFFSET + rand.nextDouble()*RANDOM_SPACING + SPACING*vec.getY());
			vectorSet.add(new Vector2i(x,y));
		}
		return vectorSet;
	}
	
	public final Vector2i turnKeyToPoint(Vector2i vec) {
		return new Vector2i(vec.getX()*SPACING,vec.getY()*SPACING);
	}
	
	public Set<Vector2i> getNeighborhoodKeys(Vector2i vec) {
		Vector2i center = getFlooredVector(vec);
		Set<Vector2i> surroundingCenters = new HashSet<Vector2i>(8);
		surroundingCenters.add(center);
		for(int i =0;i<getNeighborLength();i++){
			surroundingCenters.add(new Vector2i(getX(i)+center.getX(),getZ(i)+center.getY()));
		}
		return surroundingCenters;
	}
}
