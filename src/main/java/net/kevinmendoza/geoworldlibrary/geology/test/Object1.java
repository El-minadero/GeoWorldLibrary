package net.kevinmendoza.geoworldlibrary.geology.test;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologyPrototype;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.Alteration;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.Replacement;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.Surface;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.other.GeologyData;

public class Object1 extends GeologyPrototype {

	public Object1(PrototypeBuilder builder) {
		super(builder);
	}

	@Override
	public void primeGeneration(GenerationData metaData) {
		
	}

	@Override
	protected GeologyData<Surface> getSurfaceData(Vector2i query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected GeologyData<Alteration> getAlterationData(Vector3i query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected GeologyData<Replacement> getReplacementData(Vector3i query) {
		// TODO Auto-generated method stub
		return null;
	}


}
