package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;
import com.flowpowered.math.vector.Vectori;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.IPointGenerator;

final class LatticeCache extends Cache implements ICache {

	private final IPointGenerator pointGenerator;
	private ICacheFactory nodeFactory;
	private final int MAX_INTERNAL_COUNT = 4;
	
	public LatticeCache(LatticeCacheBuilder builder) {
		super();
		pointGenerator	= builder.getPointGenerator();
		nodeFactory		= builder.getFactory();
	}
	
	@Override
	protected INodeRegion convertToNode(INodeRegion node) {
		Object object = nodeFactory.makeNode(node);
		return (INodeRegion) object;
	}
	
	public final Set<Vector2i> getLocalKeys(Vector2i key){
		Set<Vector2i> keySet = getKeySet();
		Set<Vector2i> surroundingKeys = pointGenerator.getNeighborhoodKeys(key);
		Set<Vector2i> asymmetricSet = new HashSet<>(surroundingKeys);
		asymmetricSet.removeAll(keySet);
 		if(!asymmetricSet.isEmpty()) { populateCache(asymmetricSet); }
		return surroundingKeys;
	}
	
	private void populateCache(Set<Vector2i> surroundingKeys) {
		for(Vector2i point : surroundingKeys) {
			INodeRegion region = nodeFactory.makePrototype(point);
			addKeyValuePair(point, region);
		}
	}

	public final Set<INodeRegion> getClosestNodesToLocation(Vector3i globalVec) {
		Vector2i vec = get2DGlobalVec(globalVec);
		Set<INodeRegion> internals = getInternalRegions(vec);
		Set<INodeRegion> externals = getExternalRegions(vec);
		Set<INodeRegion> closestNodes = keepClosestNodes(MAX_INTERNAL_COUNT ,internals,externals,globalVec);
		return closestNodes;
	}
	
	private Vector2i get2DGlobalVec(Vector3i vec) {
		return new Vector2i(vec.getX(),vec.getZ());
	}
	
	public final Set<Vector2i> getLocalKeys(Vector3i globalVec) {
		Vector2i globalVec2i = new Vector2i(globalVec.getX(),globalVec.getZ());
		return getLocalKeys(globalVec2i);
	}
	

	@Override
	public void setSeed(long seed) {
		nodeFactory.setSeed(seed);
		pointGenerator.setSeed(seed);
	}


}
