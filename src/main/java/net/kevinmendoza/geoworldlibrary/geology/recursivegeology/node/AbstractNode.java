package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;

import java.util.Set;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.INodeCache;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.INodeRegion;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.IData;

abstract class AbstractNode {
	
	private final INodeCache cache;
	
	AbstractNode(ICacheBuilder builder){
		cache = builder.getCache();
	}
	
	public final IData getData(Vector3i vector3i) {
		cache.loadNodes(vector3i);
		Set<INodeRegion> nodes = cache.getNodes();
		IData data1 = getDefaultData(vector3i);
		IData data2 = getCombinedNodeData(vector3i,nodes);
		double modifier 		= getModifier(vector3i);
		IData combinedData 	= prepareData(data1, data2, modifier);
		return combinedData;
	}

	public final IData getData(Vector2i vector2i) {
		cache.loadNodes(vector2i);
		Set<INodeRegion> nodes = cache.getNodes();
		IData data1 = getDefaultData(vector2i);
		IData data2 = getCombinedNodeData(vector2i,nodes);
		double modifier 			= getModifier(vector2i);
		IData 	combinedData 	= prepareData(data1, data2, modifier);
		return 	combinedData;
	}
	
	private IData prepareData(IData data1, IData data2, double modifier) {
		data1.modifyData(modifier);
		IData 	combinedData;
		if(data2!=null) {
			data2.modifyData(modifier);
			combinedData = data1.merge(data2);
		}
		else {
			combinedData = data1;
		}
		return 	combinedData;
	}

	private IData getCombinedNodeData(Vector2i vector2i, Set<INodeRegion> nodes) {
		IData data = null;
		IData tempData = null;
		double modifier;
		for(INodeRegion region : nodes) {
			INode node = (INode)region;
			tempData = node.getData(vector2i);
			if(!node.isInside(vector2i)) {
				modifier = node.getExternalMultiplier(vector2i);
				tempData.modifyData(modifier);
			}
			if(data==null) {
				data = tempData;
			}
			else {
				data.merge(tempData);
			}
		}
		return data;
	}

	private IData getCombinedNodeData(Vector3i vector3i, Set<INodeRegion> nodes) {
		IData data = null;
		IData tempData = null;
		double modifier;
		for(INodeRegion region : nodes) {
			INode node = (INode)region;
			tempData = node.getData(vector3i);
			if(!node.isInside(vector3i)) {
				modifier = node.getExternalMultiplier(vector3i);
				tempData.modifyData(modifier);
			}
			if(data==null) {
				data = tempData;
			}
			else {
				data.merge(tempData);
			}
		}
		return data;
	}
	
	protected abstract double getModifier(Vector2i vector2i);
	protected abstract double getModifier(Vector3i vector3i);
	
	public abstract IData getDefaultData(Vector2i vector2i);
	public abstract IData getDefaultData(Vector3i vector3i);
}
