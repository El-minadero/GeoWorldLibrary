package net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.probability.IProbability;

import java.util.Set;

import com.flowpowered.math.vector.Vector2i;

class PointCloud extends AbstractGeneratorSettings implements IPointCloud {

	private IProbability distribution;
	
	public PointCloud(PointGeneratorBuilder pointGeneratorBuilder) {
		super(pointGeneratorBuilder);
		distribution = pointGeneratorBuilder.getDistribution();
	}

	public Set<Vector2i> generatePointCloud() {
		Vector2i zero = new Vector2i(0,0);
		double amount = distribution.getRVar();
		return super.getFullVectors(zero, (int)amount);
	}
	
	public Set<Vector2i> generatePointCloud(Vector2i center) {
		double amount = distribution.getRVar();
		return super.getFullVectors(center, (int)amount);
	}
}
