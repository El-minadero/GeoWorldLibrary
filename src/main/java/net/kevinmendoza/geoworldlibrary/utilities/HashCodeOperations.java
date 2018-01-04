package net.kevinmendoza.geoworldlibrary.utilities;

import java.util.Random;

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
	
	public static Random createVectorRandom(Vector2i vec) {
		long seed = createVectorSeed(vec);
		Random rand = new Random(seed);
		rand.nextDouble();
		return rand;
	}

	public static Random createVectorRandom(Vector3i vec) {
		long seed = createVectorSeed(vec);
		Random rand = new Random(seed);
		rand.nextDouble();
		return rand;
	}
	public static Random createVectorRandom(Vector2i vec, long seed) {
		long seed2 = createVectorSeed(vec,seed);
		Random rand = new Random(seed2);
		rand.nextDouble();
		return rand;
	}
	public static Random createVectorRandom(Vector3i vec, long seed) {
		long seed2 = createVectorSeed(vec,seed);
		Random rand = new Random(seed2);
		rand.nextDouble();
		return rand;
	}

	public static long createHash(int a, int b) {
		return (a*661) + b;
	}

	public static long createHash(int a, int b, int c) {
		return ((a*661) +b) + c*331;
	}
}
