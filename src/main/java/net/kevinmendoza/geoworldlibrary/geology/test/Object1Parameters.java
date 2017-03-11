package net.kevinmendoza.geoworldlibrary.geology.test;

import java.util.Random;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModificationFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.RegionFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.RegionTypes;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMapFactory;

public class Object1Parameters implements ObjectParameters {

	private double ExternalDecay = 2;
	private double InternalDecay = 1;
	
	private double baseOctave = 1200;
	private int octaveNumber = 3;
	private int coordinateOffset = 2000;
	private int octavePower =1;
	
	private int maxSubObjects = 5;
	private int minSubObjects = 2;
	
	private double[] noisefrequencies = { 500.0, 100.0, 50.0 };
	private double[] noiseWeights = { 10, 5, 7 };
	private double multiplier = 200;
	
	private int maxRegionWidth = 1000;
	private int minRegionWidth = 700;
	
	public Object1Parameters() {}
	
	public double getExternalDecayConstant() {
		return ExternalDecay/4;
	}
	public double getInternalDecayConstant() {
		return InternalDecay/4;
	}
	public Order getOrder() {
		return Order.FIRST;
	}
	public Region getRegion(Region superRegion) {
		int a = superRegion.getInt(maxRegionWidth - minRegionWidth) + minRegionWidth;
		int b = superRegion.getInt(maxRegionWidth - minRegionWidth) + minRegionWidth;
		double angle  = superRegion.getDouble()*Math.PI*2;
		return RegionFactory.MakeRegionType(RegionTypes.ELLIPSE,
				superRegion.getRandomInternalPoint(), a, b, angle);
	}
	public PointModifier getOffsetMap(Region superRegion) {
		NoiseMap map = NoiseMapFactory.MakePowerOctaveMap(baseOctave, octaveNumber,octavePower, (long)superRegion.getInt(10));
		map = NoiseMapFactory.getMultiplierNoiseMap(map, 0, coordinateOffset);
		return PointModificationFactory.CreatePointOffsetMultiplier(map);
	}
	public PointModifier getOffsetMap(Vector2i vec) {
		NoiseMap map = NoiseMapFactory.MakePowerOctaveMap(baseOctave, octaveNumber,octavePower, vec.hashCode());
		map = NoiseMapFactory.getMultiplierNoiseMap(map, 0, coordinateOffset);
		return PointModificationFactory.CreatePointOffsetMultiplier(map);
	}
	public Region getRegion(Vector2i vec) {
		Random rand = new Random(vec.hashCode());
		int a = rand.nextInt(500) + 200;
		int b = rand.nextInt(500) + 200;
		double angle  = rand.nextDouble()*Math.PI*2;
		return RegionFactory.MakeRegionType(RegionTypes.ELLIPSE, vec, a, b, angle);
	}
	public int getSubObjectNumber(Vector2i vec) {
		Random rand = new Random(vec.hashCode());
		return rand.nextInt(maxSubObjects - minSubObjects) + minSubObjects;
	}

	@Override
	public NoiseMap getNoiseMap(Vector2i vec) {
		NoiseMap baseMap = NoiseMapFactory.MakeSimplexNoiseMap(noisefrequencies, noiseWeights, vec.hashCode());
		return NoiseMapFactory.getMultiplierNoiseMap(baseMap, multiplier/2, multiplier);
	}
	
}
