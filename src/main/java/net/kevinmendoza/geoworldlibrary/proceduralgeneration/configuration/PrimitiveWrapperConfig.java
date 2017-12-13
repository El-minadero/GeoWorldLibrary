package net.kevinmendoza.geoworldlibrary.proceduralgeneration.configuration;

import java.util.Random;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;
import com.google.common.reflect.TypeToken;

import net.kevinmendoza.geoworldlibrary.utilities.HashCodeOperations;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

public class PrimitiveWrapperConfig {
	@ConfigSerializable
	public static class IntMinMax {
		public static final TypeToken<IntMinMax> type = TypeToken.of(IntMinMax.class);
		@Setting
		private int min;
		@Setting
		private int max;
		
		public IntMinMax(int min, int max){
			this.min = min; this.max=max;
		}
		
		public IntMinMax() {
			min=0;max=0;
		}

		public int getValue(long seed) {
			if(min==0&&max==0)
				return 0;
			Random rand = new Random(seed);
			rand.nextDouble();
			return min + rand.nextInt(max-min);
		}
		
		public int getValue(Vector3i vec) {
			if(min==0&&max==0)
				return 0;
			long seed = HashCodeOperations.createVectorSeed(vec);
			Random rand = new Random(seed);
			rand.nextDouble();
			return min + rand.nextInt(max-min);
		}
		public int getValue(Vector2i vec) {
			if(min==0&&max==0)
				return 0;
			long seed = HashCodeOperations.createVectorSeed(vec);
			Random rand = new Random(seed);
			rand.nextDouble();
			return min + rand.nextInt(max-min);
		}

		public int getValue(Random rand) {
			if(min==0&&max==0)
				return 0;
			return min + rand.nextInt(max-min);
		}
	}
	
	@ConfigSerializable
	public static class DoubleMinMax {
		public static final TypeToken<DoubleMinMax> type = TypeToken.of(DoubleMinMax.class);
		@Setting
		private double min;
		@Setting
		private double max;
		
		public DoubleMinMax(double min, double max){
			this.min = min; this.max=max;
		}
		
		public double getValue(long seed) {
			Random rand = new Random(seed);
			rand.nextDouble();
			return min + rand.nextDouble()*(max-min);
		}
	}
	@ConfigSerializable
	public static class TruncatedSkewedGaussianDistribution {
		public static final TypeToken<TruncatedSkewedGaussianDistribution> type = 
				TypeToken.of(TruncatedSkewedGaussianDistribution.class);
		@Setting
		private double min;
		@Setting
		private double max;
		@Setting
		private double skew;
		@Setting
		private double bias;
		
		public TruncatedSkewedGaussianDistribution(double min, double max, double skew, double bias){
			this.min = min; 
			this.max=max;
			this.skew=skew;
			this.bias=bias;
		}
		
		public double getValue(long seed) {
			Random rand = new Random(seed);
			rand.nextDouble();
			return getRVar(rand);
		}
		
		private double getRVar(Random rand) {
			double range = max - min;
			double mid = min + (range / 2.0);
			double unitGaussian = rand.nextGaussian();
			double biasFactor = Math.exp(bias);
			double retval = mid + (range
					* (biasFactor / (biasFactor + Math.exp(-unitGaussian / skew))
							- 0.5));
			return retval;
		}
	}
}
