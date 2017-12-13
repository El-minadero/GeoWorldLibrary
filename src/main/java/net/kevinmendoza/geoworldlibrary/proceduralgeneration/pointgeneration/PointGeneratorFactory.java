package net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.probability.Distribution;

public class PointGeneratorFactory {

	public static IPointGenerator makePointGenerator(long seed,int spacing,double frequency) {
		return new PointGenerator.PointGeneratorBuilder()
				.withSeed(seed)
				.withSpacing(spacing)
				.withFrequency(frequency).build();
	}
	
	public static IPointCloud makePointCloud(long seed,double spacing, Distribution distribution) {
		return new PointCloudGenerator(seed,spacing,distribution);
	}

	
}
