package net.kevinmendoza.geoworldlibrary.geology;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydata.GeologyData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydata.GeologyDataContainer;


public interface Geology {
	
	public void primeGeneration(GenerationData metaData);
	
	public <T extends GeologyData<T>> GeologyDataContainer<T> get2DGeologyData(T t, Vector2i surface);
	
	public <T extends GeologyData<T>> GeologyDataContainer<T>  get3DGeologyData(T t,Vector3i query);
	
}
