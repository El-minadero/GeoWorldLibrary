package net.kevinmendoza.geoworldlibrary.geology.test.object1test;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologyPrototype;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologyPrototypeBuilder;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydata.GeologyData;
import net.kevinmendoza.geoworldlibrary.geology.test.data.DataFactory;
import net.kevinmendoza.geoworldlibrary.geology.test.object1test.Object1Factory.PrototypeBuilder;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMapFactory;

final class Object1 extends GeologyPrototype {

	public NoiseMap map;
	
	public Object1(PrototypeBuilder builder) {
		super(builder);
		map = builder.getMap();
	}

	@Override
	public boolean shouldBuildRegion(Vector2i vec) {
		return false;
	}

	@Override
	public void primeGeneration(GenerationData metaData) {
	}

	@Override
	protected <T extends GeologyData<T>> T getGeologyData(T t, Vector2i query) {
		if(t.getName().equals(DataFactory.GetEmptySurfaceInstance().getName())) {
			int h = 100;// (int) map.getNoise(query);
			return (T) DataFactory.CreateSurfaceInstance(h);
		}
		return null;
	}

	@Override
	protected <T extends GeologyData<T>> T getGeologyData(T t, Vector3i query) {
		return null;
	}

}
