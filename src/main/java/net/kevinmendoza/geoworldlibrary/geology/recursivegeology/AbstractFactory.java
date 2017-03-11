package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.Random;

abstract class AbstractFactory {
	private long SEED;
	private Random random;
	
	public final void setSeed(long seed) {
		this.SEED = seed; 
		this.random  = new Random(SEED);
	}
	
	protected final long getSeed() {return SEED;}
	protected final Random getRandom() { return random;}
}
