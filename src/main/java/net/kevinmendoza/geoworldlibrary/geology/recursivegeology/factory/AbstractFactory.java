package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.factory;

import java.util.Random;

abstract class AbstractFactory {

	private long SEED;
	private Random random;
	
	public final void setSeed(long seed) {
		this.SEED = seed; 
		this.random  = new Random(SEED);
	}
	
	protected long createVectorSeed(int hashCode) {
		return hashCode^SEED;
	}
	
	protected final long getSeed() {return SEED;}
	protected final Random getRandom() { return random;}
	
	public int hashCode() {
		return (int)getSeed();
	}
}
