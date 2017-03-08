package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.Random;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.geology.Geology;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;

public class GeologicalFactory {
	
	private static abstract class FactorySeed {
		
		private long SEED;
		private Random random;
		
		public final void setSeed(long seed) {
			this.SEED = seed; 
			this.random  = new Random(SEED);
		}
		
		protected final long getSeed() {return SEED;}
		protected final Random getRandom() { return random;}
	}
	
	public static abstract class GeologyFactory extends FactorySeed {
		public abstract GeologyNode makePrototype(Region superRegion);
		public abstract GeologyNode makePrototype(Vector2i vec);
		public abstract GeologyNode makeObject(GeologyPrototype prototype);
		public abstract Region makeRegion(Vector2i vec);
	}
	
	public static abstract class GeologyMapFactory extends FactorySeed {
		public abstract Geology createGeologyMap();
		
		protected final Geology makeGeologyMap(GeologyMapBuilder builder) {
			return new GeologyMap(builder);
		}
	}

	
	
}
