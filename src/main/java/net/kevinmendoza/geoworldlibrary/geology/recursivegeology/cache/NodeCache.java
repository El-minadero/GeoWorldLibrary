package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;
import com.flowpowered.math.vector.Vectori;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node.INode;

class NodeCache implements INodeCache {

	private static final int externalLimit = 4;
	private Vector2i vector2i;
	private HashSet<ICache> 		cacheSet;
	private Set<INodeRegion> 	internalNodes;
	private Set<INodeRegion> 	externalNodes;
	
	NodeCache(NodeCacheBuilder builder){
		internalNodes 	= new HashSet<>();
		externalNodes 	= new HashSet<>();
		cacheSet 		= builder.getCaches();
	}
	
	@Override
	public final void loadNodes(Vector2i vec) {
		clearNodes();
		vector2i = vec;
		for(ICache cache : cacheSet) {
			Set<Vector2i> keys = cache.getLocalKeys(vec);
			internalNodes.addAll(cache.getInternalRegionsFromKeySet(keys, vec));
			externalNodes.addAll(cache.getExternalRegionsFromKeySet(keys, vec));
		}
	}
	
	@Override
	public final void loadNodes(Vector3i vec) {
		clearNodes();
		vector2i = make2DVec(vec);
		for(ICache cache : cacheSet) {
			Set<Vector2i> keys = cache.getLocalKeys(vector2i);
			internalNodes.addAll(cache.getInternalRegionsFromKeySet(keys, vector2i));
			externalNodes.addAll(cache.getExternalRegionsFromKeySet(keys, vector2i));
		}
	}
	
	private Vector2i make2DVec(Vector3i vec1) {
		return new Vector2i(vec1.getX(),vec1.getZ());
	}
	
	private void clearNodes() {
		internalNodes.clear();
		externalNodes.clear();
	}

	public final Set<INodeRegion>  getNodes()  {
		if(internalNodes.isEmpty()) {
			TreeSet<INodeRegion> newNodes = new TreeSet<>(new NodeComparator(vector2i));
			newNodes.addAll(externalNodes);
			int externalNodes = 0;
			Set<INodeRegion> returningSet = new HashSet<>();
			for(INodeRegion region : newNodes) {
				if(externalNodes>externalLimit) {
					break;
				}
				returningSet.add(region);
				externalNodes++;
			}
			return returningSet;
		}
		else {
			return internalNodes;
		}
	}
	
	private static class NodeComparator implements Comparator<INodeRegion> {
		
		private final Vector2i vec;
		NodeComparator(Vector2i vec){
			this.vec = vec;
		}
		
		@Override
		public int compare(INodeRegion o1, INodeRegion o2) {
			double dist1 = o1.getCenterDistance(vec);
			double dist2 = o2.getCenterDistance(vec);
			if(dist1 > dist2) 	{ return  1;	}
			else 				{ return -1;	}
		}
	}
}
