package net.kevinmendoza.geoworldlibrary.proceduralgeneration.probability;

import com.flowpowered.math.vector.Vector2i;

public interface IProbability {
	
	public double getRVar();

	public int getRVar(Vector2i vectors,long seed);
}
