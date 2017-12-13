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
	
	public static long createVectorSeed(Vector2i vec, long seed) {
		long i = (vec.getX() * 661) + (vec.getY() * 701) % (1024+ seed);
		return i;
	}
	public static long createVectorSeed(Vector3i vec, long seed) {
		long i = createVectorSeed(vec)*seed;
		return i;
	}
}
