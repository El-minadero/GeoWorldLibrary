package net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.probability.Distribution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.flowpowered.math.vector.Vector2d;

class PointCloudGenerator implements IPointCloud {

	private final  long SEED;
	private static long PRIME = 7;
	private final double SPACING;
	private Distribution distribution;
	
	PointCloudGenerator(long seed, double spacing,Distribution distribution) {
		SEED = seed^PRIME; SPACING=spacing; this.distribution=distribution;
	}
	
	public List<Vector2d> generatePointCloud() {
		int pointNumber = (int) distribution.getRVar();
		List<Vector2d> points = new ArrayList<>();
		Random random = new Random(SEED);
		for(int i = 0;i<pointNumber; i++)
			points.add(new Vector2d(random.nextDouble()*SPACING,random.nextDouble()*SPACING));
		return points;
	}
}
