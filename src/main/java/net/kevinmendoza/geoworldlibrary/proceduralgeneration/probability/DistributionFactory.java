package net.kevinmendoza.geoworldlibrary.proceduralgeneration.probability;

import java.util.Random;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.IRegion;

public class DistributionFactory {

	public static Distribution BuildUniformDistribution(long seed, double min, double max){
		return new UniformDistribution.UniformBuilder().setMax(max).setMin(min).setRandom(seed).Build();
	}

	public static Distribution BuildNormalDistribution(long seed, double min, double max, 
			double skew, double bias){
		return new TruncatedSkewDistribution.SkewBuilder()
				.setBias(bias).setMax(max).setMin(min).setSkew(skew).setRandom(seed).Build();
	}
	
	public static double UniformDoubleVarFromRegion(IRegion region, double min, double max) {
		double range = max-min;
		return region.getDouble()*range + min;
	}
	
	public static int UniformIntVarFromRegion(IRegion region, double min, double max){
		double range = max-min;
		return (int)(region.getDouble()*range + min);
	}
	
	public static double UniformDoubleVarFromVector(long seed, Vector2i vec, double min, double max) {
		Random rand = new Random(vec.hashCode()^seed);
		double range = max-min;
		return rand.nextDouble()*range + min;
	}
	
	public static int UniformIntVarFromVector(long seed, Vector2i vec, double min, double max){
		Random rand = new Random(vec.hashCode()^seed);
		double range = max-min;
		return (int)(rand.nextDouble()*range + min);
	}


}
