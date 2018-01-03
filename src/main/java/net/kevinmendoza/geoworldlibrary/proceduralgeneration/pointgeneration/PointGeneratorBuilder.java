package net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration;

import java.awt.Color;

import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.probability.IProbability;

class PointGeneratorBuilder {
		
		public double frequency;
		private double ratio;
		private double spacing;
		private int radius;
		private long seed;
		private IProbability distribution;

		public PointGeneratorBuilder() {
			spacing 		= 50;
			seed 		= 1;
			ratio 		= 1;
			radius 		= 1;
		}
		public PointGeneratorBuilder withSpacing(double spacing)	{ this.spacing= spacing;	return this; 	}
		public PointGeneratorBuilder withSeed(long seed) 		{ this.seed = seed; 		return this; 	}
		public PointGeneratorBuilder withRatio(double ratio) { this.ratio = ratio; 	return this; 	}
		public PointGeneratorBuilder withDistribution(IProbability distribution)
									{ this.distribution = distribution; 	return this; 	}
		
		public SinglePointLatticeGenerator buildSinglePointLattice() {
			return new SinglePointLatticeGenerator(this);
		}
		public IPointCloud buildPointCloud() {
			return new PointCloud(this);
		}
		public IPointGenerator buildMultiPointLattice() {
			return new MultiPointLatticeGenerator(this);
		}
		public IProbability getDistribution() 
									{ return distribution; 	}
		public double getSpacing() 	{ return spacing; 	}
		public double getRandomRatio() 	{ return ratio; 	}
		public long getSeed() 		{ return seed;		}
		public int getRadius() 		{ return radius; }
	
}
