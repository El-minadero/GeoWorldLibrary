package net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration;

import java.awt.Color;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Set;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.utilities.HashCodeOperations;

class SinglePointLatticeGenerator extends AbstractGeneratorSettings
	implements IPointGenerator {

	private static final int POINT_AMOUNT = 1;
	
	SinglePointLatticeGenerator(PointGeneratorBuilder pointGeneratorBuilder) {
		super(pointGeneratorBuilder);
	}
	
	@Override
	public Set<Vector2i> getNeighborhood(Vector2i vec) {
		Set<Vector2i> centers = super.getNeighborhoodKeys(vec);
		Set<Vector2i> surroundingPoints = new HashSet<Vector2i>(8);
		for(Vector2i cent : centers){
			surroundingPoints.addAll(getFullVectors(cent,POINT_AMOUNT));
		}
		return surroundingPoints;
	}
	

}
