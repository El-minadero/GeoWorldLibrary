package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.DefaultDataFactory;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.Order;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISingularGeologyData;

public class NullPrototype implements IGeologyNode {

	@Override
	public void loadNearbyNodes(GenerationData metaData) {}

	@Override
	public void primeLoadedObjects(GenerationData metaData) {}

	@Override
	public ISingularGeologyData get2DGeologyData(ISingularGeologyData testDat,
			Vector2i query) { return DefaultDataFactory.getEmptyDataObject(testDat.getID()); }

	@Override
	public ISingularGeologyData get3DGeologyData(ISingularGeologyData testDat,
			Vector3i query) { return DefaultDataFactory.getEmptyDataObject(testDat.getID()); }

	@Override
	public Vector2i getRandomInternalPoint() { return null; }

	@Override
	public double getExternalDecay(Vector2i vec) { return 0;}

	@Override
	public double getExternalDecay(Vector3i vec) { return 0; }

	@Override
	public double getInternalDecay(Vector2i vec) { return 0; }

	@Override
	public double getInternalDecay(Vector3i vec) { return 0; }

	@Override
	public boolean isVectorInRegion(Vector2i center) { return false; }

	@Override
	public boolean isVectorInRegion(Vector3i query) { return false; }

	@Override
	public String getName() { return "NULL PROTOTYPE"; }

	@Override
	public boolean isLeaf() { return false; }

	@Override
	public AbstractPrototypeFactory getFactory() { return null; }

	@Override
	public int getSubOrder() { return 0; }

	@Override
	public Order getOrder() { return null; }

	@Override
	public ISingularGeologyData getStartingData(ISingularGeologyData dataType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRGBDebugAtCoordinates(Vector3i query) { return 0; }

}
