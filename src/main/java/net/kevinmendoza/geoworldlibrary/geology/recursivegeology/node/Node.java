package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;
import java.util.HashSet;
import java.util.List;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.DefaultDataFactory;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.EmptyDataFactory;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.IGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISingularGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.Surface;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.INodeCache;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.factory.INodeFactory;

class Node extends AbstractNode {

	private final INodeCache cache;

	Node(NodeBuilder nodeBuilder) {
		super(nodeBuilder);
		cache = nodeBuilder.getCache();
	}
	@Override
	public String getLocationData(Vector3i globalVector) {
		return "";
	}

	protected final void cacheNearbyNodes(GenerationData metaData) {
		Vector2i newVec = metaData.get2DCoordinate();
		if(isInside(newVec)) {
			cache.populateIfNecessary();
			List<Vector2i> vec = cache.getKeyList();
			setInternalList(cache.getInternalRegionsFromKeyList(vec, newVec));
			setExternalList(cache.getExternalRegionsFromKeyList(vec, newVec));
		}
	}


}



