package net.kevinmendoza.geoworldlibrary.utilities;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public class HashCodeOperations {
	
	public static long createVectorSeed(Vector2i vec) {
		return (vec.getX()*661) + vec.getY();
	}

	public static long createVectorSeed(Vector3i vec) {
		return ((vec.getX()*661) + vec.getY()) + vec.getZ()*331;
	}
}
