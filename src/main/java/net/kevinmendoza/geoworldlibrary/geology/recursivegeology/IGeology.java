package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.IGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.Order;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractAlteration;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractRock;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISingularGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.Surface;

public interface IGeology {
	
	/**
	 *  Uses the 2d coordinate stored in metaData to load all Objects close to the coordinate. 
	 *  
	 * @param metaData
	 */
	public void loadNearbyNodes(GenerationData metaData);
	
	/**
	 *  Uses metaData to update all geologic objects. the objects themselves can decide if they need this info.
	 * @param metaData
	 */
	public void primeLoadedObjects(GenerationData metaData);
	
	public ISingularGeologyData get2DGeologyData(ISingularGeologyData testDat, Vector2i query);
	
	public ISingularGeologyData get3DGeologyData(ISingularGeologyData testDat, Vector3i query);
	
	public Order getOrder();

	public int getRGBDebugAtCoordinates(Vector3i query);
	
	public ISingularGeologyData getStartingData(ISingularGeologyData dataType);
	
}
