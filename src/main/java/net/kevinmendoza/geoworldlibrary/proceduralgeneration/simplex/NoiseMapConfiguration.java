package net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex;

import java.util.List;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import com.google.common.reflect.TypeToken;

import net.kevinmendoza.geoworldlibrary.utilities.HashCodeOperations;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class NoiseMapConfiguration {
	@Setting
	private List<Double> frequencies;
	@Setting
	private List<Double> weights;
	private double[] freq;
	private double[] w;
	private boolean shouldInit;
	
	
	public NoiseMapConfiguration(double[] frequencies, double[] weights) {
		this.frequencies = Doubles.asList(frequencies);
		this.weights = Doubles.asList(weights);
		shouldInit = true;
	}
	private void init() {
		if(shouldInit) {
			freq = Doubles.toArray(frequencies);
			w = Doubles.toArray(weights);
			shouldInit=false;
		}
	}
	
	public NoiseMap getMultiFrequencyNoiseMap(long seed) {
		init();
		return NoiseMapFactory.MakeSimplexNoiseMap(freq, w, seed);
	}
	
	public NoiseMap getMultiFrequencyNoiseMap(Vector2i vec) {
		init();
		long seed = HashCodeOperations.createVectorSeed(vec);
		return NoiseMapFactory.MakeSimplexNoiseMap(freq, w, seed);
	}
	
	public NoiseMap getMultiFrequencyNoiseMap(Vector3i vec) {
		init();
		long seed = HashCodeOperations.createVectorSeed(vec);
		return NoiseMapFactory.MakeSimplexNoiseMap(freq, w, seed);
	}

}
