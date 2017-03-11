package net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydatainterface;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydata.GeologyData;

public abstract class Surface extends Comparison implements GeologyData<Surface> {

	public abstract int getHeight();
	
	public String getName() {
		return "surface";
	}
}
