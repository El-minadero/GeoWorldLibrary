package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node.INode;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.prototype.IPrototype;

abstract class Cache implements INodeCache {

	private HashMap<Vector2i,INode> geoMap;
	
	public Cache() {
		geoMap = new HashMap<>();
	}
	
	protected final INode makeGeologyNode(IPrototype prototype) {
		return prototype.convertToNode();
	}
	public abstract List<Vector2i> getLocalKeys(Vector2i k);
	
	public final List<INode> getMemberList() {
		List<INode> nodes = new ArrayList<>();
		for(Vector2i vec : geoMap.keySet()) {
			if(geoMap.get(vec)!=null) {
				nodes.add(geoMap.get(vec));
			}
		}
		return nodes;
	}
	
	public final List<Vector2i> getKeyList() {
		List<Vector2i> nodes = new ArrayList<>();
		for(Vector2i vec : geoMap.keySet()) {
			if(geoMap.get(vec)!=null) {
				nodes.add(vec);
			}
		}
		return nodes;
	}
	public final void addKeyValuePair(Vector2i key, INode value) {
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
	
	private boolean isInRegion(Vector2i key,Vector2i check) {
		INode temp = geoMap.get(key);
		return temp.isInside(check);
	}
	
	private final INode createNodeIfNecessary(Vector2i key) {
		INode temp = geoMap.get(key);
		if(temp.isLeaf()) {
			temp = makeGeologyNode((IPrototype)temp);
		}
		geoMap.remove(key);
		geoMap.put(key, temp);
		return temp;
	}
	
	public final List<Vector2i> getMissingNeighborKeys(List<Vector2i> neighborhoodKeyList) {
		List<Vector2i> missingVector2ieys = new ArrayList<>();
		for(Vector2i key : neighborhoodKeyList) {
			if(!geoMap.containsKey(key)) {
				missingVector2ieys.add(key);
			}
		}
		return missingVector2ieys;
	}
	
	public final List<INode> getInternalRegionsFromKeyList(List<Vector2i> surroundingKeys, Vector2i check) {
		List<INode> potentialNodes = new ArrayList<>();
		for(Vector2i key : surroundingKeys) {
			if(isValid(key)) {
				if(isInRegion(key,check)) { 
					createNodeIfNecessary(key);
					potentialNodes.add(geoMap.get(key));
				}
			}
		}
		return potentialNodes;
	}

	public final List<INode> getExternalRegionsFromKeyList(List<Vector2i> surroundingKeys, Vector2i check) {
		List<INode> potentialNodes = new ArrayList<>();
		for(Vector2i key : surroundingKeys) {
			if(isValid(key)) {
				if(!isInRegion(key,check)) { 
					potentialNodes.add(geoMap.get(key));
				}
			}
		}
		return potentialNodes;
	}
	
	public final List<INode> getInternalRegions(Vector2i check) {
		List<INode> potentialNodes = new ArrayList<>();
		for(Vector2i key : geoMap.keySet()) {
			if(isValid(key)) {
				if(isInRegion(key,check)) { 
					createNodeIfNecessary(key);
					potentialNodes.add(geoMap.get(key));
				}
			}
		}
		return potentialNodes;
	}

	public final LinkedList<INode> getExternalRegions(Vector2i check) {
		LinkedList<INode> potentialNodes = new LinkedList<>();
		for(Vector2i key : geoMap.keySet()) {
			if(isValid(key)) {
				if(!isInRegion(key,check)) { 
					potentialNodes.add(geoMap.get(key));
				}
			}
		}
		return potentialNodes;
	}

	public INode getValue(Vector2i key) {
		return geoMap.get(key);
	}
}
