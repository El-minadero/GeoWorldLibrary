package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache;

import java.util.Set;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.IPointCloud;

class CloudCache extends Cache  {

	private final double searchRadius;
	private final IPointCloud pointCloudGenerator;
	private final ICacheFactory nodeFactory;
	private final Vector3i center;
	private final int MAX_INTERNAL_COUNT = 5;
	
	public CloudCache(CloudCacheBuilder builder) {
		super();
		searchRadius			= builder.getSearchRadius();
		nodeFactory			= builder.getFactory();
		pointCloudGenerator	= builder.getPointCloudGenerator();
		center				= builder.getCenter3i();
	}

	@Override
	public void setSeed(long seed) {
		clearMap();
		pointCloudGenerator.setSeed(seed);
		nodeFactory.setSeed(seed);
	}
	
	@Override
	protected INodeRegion convertToNode(INodeRegion node) {
		Object object = nodeFactory.makeNode(node);
		return (INodeRegion)object;
	}

	public final Set<INodeRegion> getClosestNodesToLocation(Vector3i globalVec) {
		Vector3i vec = globalVec.sub(center);
		getLocalKeys(globalVec);
		Set<INodeRegion> internals = getInternalRegions(vec);
		Set<INodeRegion> externals = getExternalRegions(vec);
		Set<INodeRegion> closestNodes = keepClosestNodes(MAX_INTERNAL_COUNT  ,internals,externals,globalVec);
		return closestNodes;
	}
	
	private void populateCache(Vector3i vec) {
		Set<Vector2i> points = pointCloudGenerator.generatePointCloud();
		for(Vector2i point : points) {
			INodeRegion region = nodeFactory.makePrototype(point);
			addKeyValuePair(point,region);
		}
	}
	
	private void populateCache(Vector2i vec) {
		Set<Vector2i> points = pointCloudGenerator.generatePointCloud();
		for(Vector2i point : points) {
			INodeRegion region = this.nodeFactory.makePrototype(point);
			addKeyValuePair(point,region);
		}
	}
		
	@Override
	public Set<Vector2i> getLocalKeys(Vector3i globalVec) {
		Set<Vector2i> keySet = getKeySet();
		if(keySet.isEmpty()) { 
			populateCache(globalVec); 
			keySet = getLocalKeys(globalVec);
		}
		else {
			keySet.removeIf(p -> globalVec.distance(new Vector3i(p.getX(),globalVec.getY(),p.getY())) >= searchRadius);
		}
		return keySet;
	}

	@Override
	public Set<Vector2i> getLocalKeys(Vector2i globalVec) {
		Set<Vector2i> keySet = getKeySet();
		if(keySet.isEmpty()) { 
			populateCache(globalVec); 
			keySet = getLocalKeys(globalVec);
		}
		else {
			keySet.removeIf(p -> globalVec.distance(p) <= searchRadius);
		}
		return keySet;
	}
	

}
