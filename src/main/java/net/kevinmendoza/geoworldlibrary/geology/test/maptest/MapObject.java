package net.kevinmendoza.geoworldlibrary.geology.test.maptest;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologyPrototype;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydata.GeologyData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydatainterface.Surface;
import net.kevinmendoza.geoworldlibrary.geology.test.data.DataFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMapFactory;

final class MapObject extends GeologyPrototype {

	private NoiseMap offsetMap;

	MapObject() {
		super(Order.FIRST);
		double[] frequencies = { 1000, 500, 100};
		double[] weight 	 = { 10, 5, 5};
		NoiseMap map = NoiseMapFactory.MakeSimplexNoiseMap(frequencies, weight, 10);
		offsetMap = NoiseMapFactory.getMultiplierNoiseMap(map, 64, 100);
	
	}
	@Override
	public void primeGeneration(GenerationData metaData) {
		
	}

	@Override
	protected <T extends GeologyData<T>> T getGeologyData(T t,
			Vector2i query) {
		if(t.getName().equals(DataFactory.GetEmptySurfaceInstance().getName())) {
			double height = 10;// offsetMap.getNoise(query);
			return (T) DataFactory.CreateSurfaceInstance((int)height);
		}
		return null;
	}

	@Override
	protected <T extends GeologyData<T>> T getGeologyData(T t,
			Vector3i query) {
		return null;
	}
	
	@Override
	public boolean shouldBuildRegion(Vector2i vec) {
		return true;
	}

}
