package net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;
import com.google.common.reflect.TypeToken;

import net.kevinmendoza.geoworldlibrary.utilities.HashCodeOperations;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class MultiplierMapConfiguration {
	public static final TypeToken<MultiplierMapConfiguration> type = TypeToken.of(MultiplierMapConfiguration.class);
	
	@Setting
	private NoiseMapConfiguration noiseMap;
	@Setting
	private double multiplier;

	public MultiplierMapConfiguration(NoiseMapConfiguration noiseMap ,double multiplier){
		this.noiseMap = noiseMap;
		this.multiplier = multiplier;
	}

	public NoiseMap getMultiplierMap(long seed){
		NoiseMap map1 = noiseMap.getMultiFrequencyNoiseMap(seed);
		return NoiseMapFactory.MakeMultiplierNoiseMap(map1, multiplier);
	}

	public NoiseMap getMultiplierMap(Vector3i vec) {
		long seed = HashCodeOperations.createVectorSeed(vec);
		return getMultiplierMap(seed);
	}
	
	public NoiseMap getMultiplierMap(Vector2i vec) {
		long seed = HashCodeOperations.createVectorSeed(vec);
		return getMultiplierMap(seed);
	}
}
