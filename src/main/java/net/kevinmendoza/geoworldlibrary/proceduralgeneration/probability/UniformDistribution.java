package net.kevinmendoza.geoworldlibrary.proceduralgeneration.probability;

import java.util.Random;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.utilities.HashCodeOperations;

class UniformDistribution implements IProbability {

	private double min;
	private Random rand;
	private double range;

	UniformDistribution(UniformBuilder builder) {
		this.rand = builder.rand;
		this.min = builder.min;
		this.range = builder.max - builder.min;
	}

	public double getRVar() {
		return rand.nextDouble()*range + min;
	}
	public int getRVar(Vector2i vec,long seed) {
		Random rand2 = HashCodeOperations.createVectorRandom(vec, seed);
		if(range<0.000001) {
			return (int) min;
		}
		else {
			return (int) (rand2.nextInt((int)range) + min);
		}
	}
	static class UniformBuilder { 
		private Random rand = new Random(1);
		private double max  = 1;
		private double min  = 0;

		public UniformBuilder setRandom(long seed){ this.rand=new Random(seed); return this; }
		public UniformBuilder setMax(double m) { this.max=m;  return this; }
		public UniformBuilder setMin(double m) { this.min=m;  return this; }
		public IProbability Build() { return new UniformDistribution(this); }
	}

}
