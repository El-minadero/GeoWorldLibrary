package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.IGeologyData;

public interface IGeology {
	
	public void primeGeneration(GenerationData metaData);
	
	public IGeologyData get2DGeologyData(IGeologyData testDat, Vector2i query);
	
	public IGeologyData get3DGeologyData(IGeologyData testDat, Vector3i query);
	
	
	
}
