package net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;
import com.google.common.reflect.TypeToken;

import net.kevinmendoza.geoworldlibrary.utilities.HashCodeOperations;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class MultiFrequencyNoiseMapConfiguration {
	public static final TypeToken<MultiFrequencyNoiseMapConfiguration> type = TypeToken.of(MultiFrequencyNoiseMapConfiguration.class);

	@Setting
	double baseFrequency;
	
	@Setting
	int octaves;
	
	@Setting
	int power;

	public MultiFrequencyNoiseMapConfiguration(double baseFrequency, int octaves, int power){
		this.baseFrequency=baseFrequency;
		this.octaves=octaves;
		this.power=power;
	}

	public NoiseMap getMultiplierMap(long seed){
		NoiseMap map = NoiseMapFactory.MakePowerOctaveMap(baseFrequency,octaves, power, seed);
		return  map;
	}

	public NoiseMap getMultiplierMap(Vector3i vec) {
		long seed = HashCodeOperations.createVectorSeed(vec);
		NoiseMap map = NoiseMapFactory.MakePowerOctaveMap(baseFrequency,octaves, power, seed);
		return map;
	}

	public NoiseMap getMultiplierMap(Vector2i vec) {
		long seed = HashCodeOperations.createVectorSeed(vec);
		NoiseMap map = NoiseMapFactory.MakePowerOctaveMap(baseFrequency,octaves, power, seed);
		return map;
	}

}
