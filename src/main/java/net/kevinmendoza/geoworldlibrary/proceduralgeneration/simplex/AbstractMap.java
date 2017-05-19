package net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

abstract class AbstractMap implements NoiseMap {

	public boolean isPositive(Vector3i vec) {
		return (getNoise(vec)>=0);
	}

	public boolean isPositive(Vector2i vec){
		return (getNoise(vec)>=0);
	}
}
