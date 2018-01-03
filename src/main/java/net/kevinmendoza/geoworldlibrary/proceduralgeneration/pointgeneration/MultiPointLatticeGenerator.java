package net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.probability.IProbability;

class MultiPointLatticeGenerator extends AbstractGeneratorSettings implements IPointGenerator {

	private IProbability distribution;

	MultiPointLatticeGenerator(PointGeneratorBuilder pointGeneratorBuilder) {
		super(pointGeneratorBuilder);
		distribution = pointGeneratorBuilder.getDistribution();
	}
	
	@Override
	public Set<Vector2i> getNeighborhood(Vector2i vec) {
		Set<Vector2i> flooredNeighbors = getNeighborhoodKeys(vec);
		Set<Vector2i> vectorList = new HashSet<>();
		int amount;
		for(Vector2i vectors : flooredNeighbors) {
			amount = distribution.getRVar(vectors,getSeed());
			vectorList.addAll(super.getFullVectors(vectors, amount));
		}
		return vectorList;
	}


}
