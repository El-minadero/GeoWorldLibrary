package net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;
import com.google.common.reflect.TypeToken;

import net.kevinmendoza.geoworldlibrary.utilities.HashCodeOperations;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class NoiseMapConfiguration {
	public static final TypeToken<NoiseMapConfiguration> type = TypeToken.of(NoiseMapConfiguration.class);
	@Setting
	private double[] frequencies;
	
	@Setting
	private double[] weights;
	
	
	public NoiseMapConfiguration(double[] frequencies, double[] weights) {
		this.frequencies = frequencies;
		this.weights = weights;
	}
	
	
	public NoiseMap getMultiFrequencyNoiseMap(long seed) {
		return NoiseMapFactory.MakeSimplexNoiseMap(frequencies, weights, seed);
	}
	
	public NoiseMap getMultiFrequencyNoiseMap(Vector2i vec) {
		long seed = HashCodeOperations.createVectorSeed(vec);
		return NoiseMapFactory.MakeSimplexNoiseMap(frequencies, weights, seed);
	}
	
	public NoiseMap getMultiFrequencyNoiseMap(Vector3i vec) {
		long seed = HashCodeOperations.createVectorSeed(vec);
		return NoiseMapFactory.MakeSimplexNoiseMap(frequencies, weights, seed);
	}

}
