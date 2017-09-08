package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.Random;

import com.flowpowered.math.vector.Vector2i;

final class MapData {
	private long SEED;
	private final double SPACING;
	private final double FREQUENCY;
	
	MapData(IGeologyMapBuilder builder){
		SEED = builder.getSeed();
		SPACING = builder.getSpacing();
		FREQUENCY = builder.getFrequency();
	}

	public long getSeed() { return SEED; }
	public int getSpacing() { return (int)SPACING; }

	public void setSeed(long seed2) { SEED = seed2; }

	public boolean shouldBuild(Vector2i vec ) {
		Random rand = new Random(vec.hashCode());
		rand.nextDouble();
		return (rand.nextDouble()<=FREQUENCY);
	}
}
