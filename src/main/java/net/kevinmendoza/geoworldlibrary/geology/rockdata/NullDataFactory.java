package net.kevinmendoza.geoworldlibrary.geology.rockdata;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public class NullDataFactory implements IDataFactory {

	@Override
	public IData getData(Vector2i modifiedPoint) {
		return DataFactory.getDefaultData();
	}

	@Override
	public IData getData(Vector3i modifiedPoint) {
		return DataFactory.getDefaultData();
	}

}
