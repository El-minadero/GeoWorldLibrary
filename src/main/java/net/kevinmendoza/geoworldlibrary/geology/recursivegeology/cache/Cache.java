package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;
import com.flowpowered.math.vector.Vectori;
/**
 * 
 * @author kevinmendoza
 * 
 * temporarily stores Region data in a 'flywheel-type' data structure
 * key's are vector2i's and values are INodeRegions
 *
 */
abstract class Cache implements ICache{

	private HashMap<Vector2i,INodeRegion> geoMap;
	
	public Cache() {
		geoMap = new HashMap<>();
	}
	
	protected abstract INodeRegion convertToNode(INodeRegion node);
	
	/**
	 * gets a Set of all the INodeRegions stored in the map
	 * @param  Vector2i key to query
	 * @return Set<INodeRegion> key Set
	 */
	public final Set<INodeRegion> getMemberSet() {
		Set<INodeRegion> nodes = new HashSet<>();
		Set<Vector2i > mapKeys = geoMap.keySet();
		for(Vector2i vec : mapKeys) {
			if(geoMap.get(vec)!=null) {
				nodes.add(geoMap.get(vec));
			}
		}
		return nodes;
	}
	/**
	 * gets a Set of all the keys stored in the map
	 * @return Set<Vector2i> key Set
	 */
	public final Set<Vector2i> getKeySet() {
		Set<Vector2i> nodes = new HashSet<>();
		Set<Vector2i > mapKeys = geoMap.keySet();
		for(Vector2i vec : mapKeys) {
			if(geoMap.get(vec)!=null) {
				nodes.add(vec);
			}
		}
		return nodes;
	}
	/**
	 * adds an INodeRegion to a particular key
	 * @return Set<Vector2i> key Set
	 */
	public final void addKeyValuePair(Vector2i key, INodeRegion value) {
		Vector2i newKey = new Vector2i(key);
		while(geoMap.containsKey(newKey)) {
			newKey= new Vector2i(newKey.add(1,1));
		}
		geoMap.put(newKey,value);
	}

	public final void addNullValueToKey(Vector2i key) {
		geoMap.put(key, null);
	}
	
	private boolean isValid(Vector2i key) {
		return (geoMap.containsKey(key) && geoMap.get(key)!=null);
	}
	
	private final INodeRegion createNodeIfNecessary(Vector2i key) {
		INodeRegion temp = geoMap.get(key);
		if(temp.isLeaf()) { 
			temp = convertToNode(temp); 
			geoMap.remove(key);
			geoMap.put(key, temp);
		}
		return temp;
	}
	/**
	 * returns a Set of keys that should be built according to the generator, but have not been.
	 * 
	 * @param Set of keys to check
	 * @return keys which are not a part of the local cache
	 */
	protected final Set<Vector2i> getMissingNeighborKeys(Set<Vector2i> neighborhoodKeySet) {
		Set<Vector2i> missingVector2ieys = new HashSet<>();
		for(Vector2i key : neighborhoodKeySet) {
			if(!geoMap.containsKey(key)) {
				missingVector2ieys.add(key);
			}
		}
		return missingVector2ieys;
	}

	public final Set<INodeRegion> getInternalRegionsFromKeySet(Set<Vector2i> surroundingKeys, Vector2i check) {
		Set<INodeRegion> potentialNodes = new HashSet<>();
		for(Vector2i key : surroundingKeys) {
			if(isValid(key) && geoMap.get(key).isInside(check)) {
				createNodeIfNecessary(key);
				potentialNodes.add(geoMap.get(key));
			}
		}
		return potentialNodes;
	}

	public final Set<INodeRegion> getExternalRegionsFromKeySet(Set<Vector2i> surroundingKeys, Vector2i check) {
		Set<INodeRegion> potentialNodes = new HashSet<>();
		for(Vector2i key : surroundingKeys) {
			if(isValid(key) && !geoMap.get(key).isInside(check)) { 
				potentialNodes.add(geoMap.get(key));
			}
		}
		return potentialNodes;
	}

	public final Set<INodeRegion> getInternalRegions(Vector2i check) {
		Set<INodeRegion> potentialNodes = new HashSet<>();
		Set<Vector2i > mapKeys = getKeys();
		for(Vector2i key : mapKeys) {
			if(isValid(key) && geoMap.get(key).isInside(check)) { 
				createNodeIfNecessary(key);
				potentialNodes.add(geoMap.get(key));
			}
		}
		return potentialNodes;
	}
	public final Set<INodeRegion> getInternalRegions(Vector3i check) {
		Set<INodeRegion> potentialNodes = new HashSet<>();
		Set<Vector2i > mapKeys = getKeys();
		for(Vector2i key : mapKeys) {
			if(isValid(key) && geoMap.get(key).isInside(check)) { 
				createNodeIfNecessary(key);
				potentialNodes.add(geoMap.get(key));
			}
		}
		return potentialNodes;
	}

	public final Set<INodeRegion> getExternalRegions(Vector2i check) {
		Set<INodeRegion> potentialNodes = new HashSet<>();
		Set<Vector2i > mapKeys = getKeys();
		for(Vector2i key : mapKeys) {
			if(isValid(key) && !geoMap.get(key).isInside(check)) { 
				potentialNodes.add(geoMap.get(key));
			}
		}
		return potentialNodes;
	}

	public final Set<INodeRegion> getExternalRegions(Vector3i check) {
		Set<INodeRegion> potentialNodes = new HashSet<>();
		Set<Vector2i > mapKeys = getKeys();
		for(Vector2i key : mapKeys) {
			if(isValid(key) && !geoMap.get(key).isInside(check)) { 
				potentialNodes.add(geoMap.get(key));
			}
		}
		return potentialNodes;
	}

	public final INodeRegion getValue(Vector2i key) {
		return geoMap.get(key);
	}

	protected final Set<INodeRegion> keepClosestNodes(int i,
			Set<INodeRegion> internals, Set<INodeRegion> externals, Vector3i vec) {
		Set<INodeRegion> closestNodes = new HashSet<>();

		if(internals.size()<i) { 
			int remainder = i - internals.size();
			closestNodes.addAll(internals);
			closestNodes.addAll(sortAndReturnClosestNNodes(remainder, externals,vec));
		}
		else {
			closestNodes.addAll(sortAndReturnClosestNNodes(i, internals,vec));
		}

		return closestNodes;
	}

	protected final Set<INodeRegion> keepClosestNodes(int i,
			Set<INodeRegion> internals, Set<INodeRegion> externals, Vector2i vec) {
		Set<INodeRegion> closestNodes = new HashSet<>();

		if(internals.size()<i) { 
			int remainder = i - internals.size();
			closestNodes.addAll(internals);
			closestNodes.addAll(sortAndReturnClosestNNodes(remainder, externals,vec));
		}
		else {
			closestNodes.addAll(sortAndReturnClosestNNodes(i, internals,vec));
		}

		return closestNodes;
	}

	private final Set<INodeRegion> sortAndReturnClosestNNodes(int i, Set<INodeRegion> nodesToSort,Vector3i vec){
		Set<INodeRegion> closestNodes = new TreeSet<>(new Vec3DistComparator(vec));
		closestNodes.addAll(nodesToSort);
		return closestNodes;
	}

	private final Set<INodeRegion> sortAndReturnClosestNNodes(int i, Set<INodeRegion> nodesToSort,Vector2i vec){
		Set<INodeRegion> closestNodes = new TreeSet<>(new Vec2DistComparator(vec));
		closestNodes.addAll(nodesToSort);
		return closestNodes;
	}

	public final String getLocationString(Vector3i globalVector) {
		Set<INodeRegion> closestNodes = getClosestNodesToLocation(globalVector);
		String s = "";
		for(INodeRegion node : closestNodes) {
			s=s + node.getLocationData(globalVector);
		}
		return s;
	}

	private class Vec3DistComparator implements Comparator<INodeRegion>{
		private Vector3i vec3;

		Vec3DistComparator(Vector3i vec){
			this.vec3 = vec;
		}

		@Override
		public int compare(INodeRegion o1,INodeRegion o2) {
			return Double.compare(o1.getCenterDistance(vec3), o2.getCenterDistance(vec3));
		}

	}
	private class Vec2DistComparator implements Comparator<INodeRegion>{
		private Vector2i vec3;

		Vec2DistComparator(Vector2i vec){
			this.vec3 = vec;
		}

		@Override
		public int compare(INodeRegion o1,INodeRegion o2) {
			return Double.compare(o1.getCenterDistance(vec3), o2.getCenterDistance(vec3));
		}

	}
	
	public final  Set<Vector2i> getKeys() {
		return new HashSet<Vector2i>(geoMap.keySet());
	}
	protected void clearMap() { geoMap.clear(); }
}
