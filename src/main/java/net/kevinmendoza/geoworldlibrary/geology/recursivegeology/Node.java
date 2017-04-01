package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;
import java.util.HashSet;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.DataFactory;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.IGeologyData;

class Node extends AbstractNode {

	private final NodeCache map;

	Node(NodeBuilder builder) {
		super(builder);
		map = new NodeCache(builder.getFactory(),builder.getSubObjectNumber(),this);
	}


	protected final void prime(GenerationData metaData) {
		Vector2i newVec = metaData.get2DCoordinate();
		if(isUnmodifiedVectorInRegion(newVec)) {
			setInternalList(map.getOverlappingObjects(newVec));
			setExternalList(map.getDistantObjects(newVec));
		}
	}
	
	@Override
	protected IGeologyData getCombined2DConditions(IGeologyData testData,
			Vector2i query,HashSet<IGeologyNode> tempTotal) {
		IGeologyData dataNode = DataFactory.GetGeologyDataNode(testData.getID());
		IGeologyData prototypeData = getPrototype2DData(testData,  query);
		if(isUnmodifiedVectorInRegion(query)) {
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
	protected IGeologyData getCombined3DConditions(IGeologyData testData,
			Vector3i query, HashSet<IGeologyNode> tempTotal) {
		IGeologyData dataNode = DataFactory.GetGeologyDataNode(testData.getID());
		IGeologyData prototypeData = getPrototype3DData(testData, query);
		if(isUnmodifiedVectorInRegion(query)) {
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
	public void debug() {
		map.buildAll();
	}

}



