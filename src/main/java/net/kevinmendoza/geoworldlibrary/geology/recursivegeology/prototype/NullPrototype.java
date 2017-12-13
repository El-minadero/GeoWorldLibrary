package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.prototype;

import java.util.ArrayList;
import java.util.List;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.DefaultDataFactory;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.Order;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISingularGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.factory.AbstractPrototypeFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.factory.INodeFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node.INode;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.IRegion;

final class NullPrototype implements IPrototype {

	private IRegion region;
	private INodeFactory factory;
	public NullPrototype(PrototypeBuilder prototypeBuilder) {
		region = prototypeBuilder.getRegion();
		factory = prototypeBuilder.getSelfFactory();
	}

	@Override
	public String getName() { return "Null Prototype"; }

	@Override
	public boolean isLeaf() { return true; }

	@Override
	public INode convertToNode() { return factory.makeNode(this); }

	@Override
	public boolean isInside(Vector2i vec) { return region.isInside(vec); }

	@Override
	public boolean isInside(Vector3i vec) { return region.isInside(vec); }

	@Override
	public double getCenterDistance(Vector3i vec) { return region.getDistanceToCenter(vec); }

	@Override
	public double getCenterDistance(Vector2i vec) { return region.getDistanceToCenter(vec); }

	@Override
	public Vector2i getRandomInternalPoint2i() { return new Vector2i(0,0); }

	@Override
	public Vector3i getRandomInternalPoint3i() { return new Vector3i(0,0,0); }

	@Override
	public double getExternalDecay(Vector2i vec) { return 0; }

	@Override
	public double getExternalDecay(Vector3i vec) { return 0; }

	@Override
	public void loadNearbyNodes(GenerationData metaData) { }

	@Override
	public void primeLoadedObjects(GenerationData metaData) { }

	@Override
	public ISingularGeologyData get3DGeologyData(ISingularGeologyData testDat,
			Vector3i vec) {
		ISingularGeologyData data = DefaultDataFactory.getTestData(1);
		return data;
	}

	@Override
	public ISingularGeologyData get2DGeologyData(ISingularGeologyData testDat,
			Vector2i vec) {
		ISingularGeologyData data = DefaultDataFactory.getTestData(1);
		return data;
	}

	@Override
	public ISingularGeologyData getStartingData(ISingularGeologyData dataType) {
		ISingularGeologyData data = DefaultDataFactory.getTestData(1);
		return data;
	}

	@Override
	public int getRGBDebugAtCoordinates(Vector3i query) { return 1; }

	@Override
	public String getLocationData(Vector3i globalVector) { return null; }

	@Override
	public double getInternalDecay(Vector2i vec) { return 1 ;}

	@Override
	public double getInternalDecay(Vector3i vec) { return 1 ; }



	

}
