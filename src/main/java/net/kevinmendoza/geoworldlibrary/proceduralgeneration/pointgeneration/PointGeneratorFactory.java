package net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.probability.IProbability;

public class PointGeneratorFactory {

	public static IPointGenerator makeSinglePointLattice(long seed, int spacing, double ratio) {
		return new PointGeneratorBuilder()
				.withSeed(seed)
				.withSpacing(spacing)
				.withRatio(ratio)
				.buildSinglePointLattice();
	}
	
	public static IPointGenerator makeMultiPointLattice(long seed, int spacing, double ratio,
														IProbability distribution) {
		return new PointGeneratorBuilder()
				.withSeed(seed)
				.withSpacing(spacing)
				.withDistribution(distribution)
				.withRatio(ratio)
				.buildMultiPointLattice();
	}
	
	public static IPointCloud makePointCloud(long seed, double spacing, double ratio, 
											IProbability distribution) {
		return new PointGeneratorBuilder()
				.withSeed(seed)
				.withSpacing(spacing)
				.withRatio(ratio)
				.withDistribution(distribution)
				.buildPointCloud();
	}

	
}
