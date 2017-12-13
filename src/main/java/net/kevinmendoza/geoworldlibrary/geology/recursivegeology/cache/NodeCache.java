package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.Comparison;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.factory.AbstractPrototypeFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.factory.INodeFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node.INode;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node.INodeBuilder;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.IPointGenerator;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.IRegion;

class NodeCache extends Cache {

	private final double searchRadius;
	private HashMap<Integer,INodeFactory> factoryMap;
	private final IRegion sourceRegion;
	
	public NodeCache(NodeCacheBuilder builder) {
		double s			= builder.getSearchRadius();
		searchRadius = s*s;
		factoryMap		= builder.getFactoryMap();
		sourceRegion		= builder.getSourceRegion();
	}

	@Override
	public INode populateKeyValue(Vector2i vec) {
		return null;
	}

	@Override
	public String getLocationString(Vector3i globalVector) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vector2i> getLocalKeys(Vector2i k) {
		List<Vector2i> locals = new ArrayList<>();
		List<Vector2i> keys = super.getKeyList();
		for(Vector2i key : keys) {
			INode node = super.getValue(key);
			if(node.getCenterDistance(k)<searchRadius){
				locals.add(key);
			}
		}
		return locals;
	}
	@Override
	public List<Vector2i> getLocalKeys(Vector3i k) {
		List<Vector2i> locals = new ArrayList<>();
		List<Vector2i> keys = super.getKeyList();
		for(Vector2i key : keys) {
			INode node = super.getValue(key);
			if(node.getCenterDistance(k)<searchRadius){
				locals.add(key);
			}
		}
		return locals;
	}

	@Override
	public List<INode> getClosestNodesToLocation(Vector3i globalVec) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void populateIfNecessary() {
		for(int i : factoryMap.keySet()) {
			Vector2i key = sourceRegion.getRandom2iPoint();
			INode value = factoryMap.get(i).makePrototype(sourceRegion);
			super.addKeyValuePair(key, value);
		}
		factoryMap.clear();
	}

	@Override
	public void setSeed(long seed) {
		for(INodeFactory f : factoryMap.values()) {
			f.setSeed(seed);
		}
	}

	
	 
	

}
