package net.kevinmendoza.geoworldlibrary.geology;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.Alteration;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.Replacement;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.Surface;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.other.GeologyData;


public interface Geology {
	
	public void primeGeneration(GenerationData metaData);
	
	public GeologyData<Surface> getSurface(Vector2i surface);
	
	public GeologyData<Alteration>  getAlteration(Vector3i query);
	
	public GeologyData<Replacement>  getReplacement(Vector3i query);
}
