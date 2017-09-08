package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;
import java.util.HashSet;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.DefaultDataFactory;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.EmptyDataFactory;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.IGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISingularGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.Surface;

class Node extends AbstractNode {

	private final NodeCache map;
	private AbstractPrototypeFactory factory;

	Node(INodeBuilder builder) {
		super(builder);
		map = new NodeCache(builder,this);
	}


	protected final void cacheNearbyNodes(GenerationData metaData) {
		Vector2i newVec = metaData.get2DCoordinate();
		if(isVectorInRegion(newVec)) {
			setInternalList(map.getOverlappingObjects(newVec));
			setExternalList(map.getDistantObjects(newVec));
		}
	}
	
	@Override
	protected ISingularGeologyData getCombined2DConditions(ISingularGeologyData testData,
			Vector2i query,HashSet<IGeologyNode> tempTotal) {
		ISingularGeologyData dataNode = DefaultDataFactory.getEmptyDataObject(testData.getID());
		ISingularGeologyData prototypeData = getPrototype2DData(testData,  query);
		if(isVectorInRegion(query)) {
			dataNode.merge(prototypeData);
			mergeData(testData,dataNode, query,tempTotal);
			dataNode.merge(prototypeData,1-getInternalDecay( query));
		}
		else {
			dataNode.merge(prototypeData,getExternalDecay(query));
		}
		return dataNode;
	}

	@Override
	protected ISingularGeologyData getCombined3DConditions(ISingularGeologyData testData,
			Vector3i query, HashSet<IGeologyNode> tempTotal) {
		ISingularGeologyData dataNode = DefaultDataFactory.getEmptyDataObject(testData.getID());
		ISingularGeologyData prototypeData = getPrototype3DData(testData, query);
		if(isVectorInRegion(query)) {
			dataNode.merge(prototypeData);
			mergeData(testData,dataNode,query,tempTotal);
			dataNode.merge(prototypeData,1-getInternalDecay(query));
		}
		else {
			dataNode.merge(prototypeData,getExternalDecay(query));
		}
		return dataNode;
	}

	@Override
	public AbstractPrototypeFactory getFactory() { return factory; }


}



