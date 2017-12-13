package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;

import java.util.Collection;
import java.util.HashSet;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISingularGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.prototype.IPrototype;

abstract class AbstractNode implements INode {

	private final IPrototype prototype;
	private HashSet<INode> internal;
	private HashSet<INode> external;
	private HashSet<INode> total;
	
	AbstractNode(INodeBuilder nodeBuilder){
		prototype 		= nodeBuilder.getPrototype();
		internal 	   	= new HashSet<>();
		external 	   	= new HashSet<>();
		total 		   	= new HashSet<>();
	}
	
	public INode convertToNode() { return this; }
	public final String getName() { return prototype.getName(); }
	public final boolean isLeaf() { return false; }
	public final double getCenterDistance(Vector3i vec) { return prototype.getCenterDistance(vec); }
	public final double getCenterDistance(Vector2i vec) { return prototype.getCenterDistance(vec); }
	protected final String getPrototypeInfo(Vector3i vec) { return  "Node:" + prototype.getLocationData(vec); }
	protected abstract void cacheNearbyNodes(GenerationData metaData);
	protected final void setInternalList(Collection<INode> i) { internal.addAll(i); }
	protected final void setExternalList(Collection<INode> i) { external.addAll(i); }
	
	public int getRGBDebugAtCoordinates(Vector3i query) {
		int rgb =0;
		for(INode n : total) {
			rgb+=n.getRGBDebugAtCoordinates(query);
		}
		rgb+=prototype.getRGBDebugAtCoordinates(query);
		return rgb;
	}
	
	public final void loadNearbyNodes(GenerationData metaData) {
		clearChildren();
		GenerationData data2 = metaData.getCopy();
		cacheNearbyNodes(data2);
		primeGenerationList(data2);
	}
	
	public final void primeLoadedObjects(GenerationData metaData) {
		GenerationData data2 = metaData.getCopy();
		primeGeneratedObjects(data2);
	}

	
	private void clearChildren() {
		internal.clear();
		external.clear();
		total.clear();
	}
	
	private final void primeGenerationList(GenerationData data) {
		prototype.loadNearbyNodes(data);
		if(!internal.isEmpty() )
			for(INode obj : internal)
				obj.loadNearbyNodes(data);
		else if(!external.isEmpty())
			for(INode obj : external)
				obj.loadNearbyNodes(data);
		total.addAll(internal);
		total.addAll(external);
		
	}
	
	public void primeGeneratedObjects(GenerationData data){
		prototype.primeLoadedObjects(data);
		if(!internal.isEmpty())
			for(INode obj : internal)
				obj.primeLoadedObjects(data);
		else if(!external.isEmpty())
			for(INode obj : external)
				obj.primeLoadedObjects(data);
	}

	protected final ISingularGeologyData getPrototype3DData(ISingularGeologyData testData,Vector3i query) {
		return prototype.get3DGeologyData(testData, query);
	}
	protected final ISingularGeologyData getPrototype2DData(ISingularGeologyData testData,Vector2i query) {
		return prototype.get2DGeologyData(testData, query);
	}
	public final double getExternalDecay(Vector2i vec) { return prototype.getExternalDecay(vec); }
	public final double getExternalDecay(Vector3i vec) { return prototype.getExternalDecay(vec); }
	
	protected final double getInternalDecay(Vector2i vec) { return prototype.getInternalDecay(vec); }
	protected final double getInternalDecay(Vector3i vec) { return prototype.getInternalDecay(vec); } 
	
	public final boolean isInside(Vector2i vec)  { return prototype.isInside(vec); }
	public final boolean isInside(Vector3i vec)  { return prototype.isInside(vec); }

	public final Vector2i getRandomInternalPoint2i() { return prototype.getRandomInternalPoint2i(); }
	public final Vector3i getRandomInternalPoint3i() { return prototype.getRandomInternalPoint3i(); }

	public final ISingularGeologyData get2DGeologyData(ISingularGeologyData testData, Vector2i vec) {
		ISingularGeologyData protoData = prototype.get2DGeologyData(testData,vec);
		if(total.isEmpty()) { return protoData; }
		HashSet<INode> tempTotal  = (HashSet<INode>) total.clone();
		ISingularGeologyData data = mergeData(testData,vec,tempTotal);
		data.merge(protoData,1-getInternalDecay(vec));
		return data;
	}
	
	public final ISingularGeologyData get3DGeologyData(ISingularGeologyData testData, Vector3i vec) {
		ISingularGeologyData protoData = prototype.get3DGeologyData(testData,vec);
		if(total.isEmpty()) { return protoData; }
		HashSet<INode> tempTotal  = (HashSet<INode>) total.clone();
		ISingularGeologyData data = mergeData(testData,vec,tempTotal);
		data.merge(protoData,1-getInternalDecay(vec));
		return data;
	}
	
	protected ISingularGeologyData mergeData(ISingularGeologyData testData,
			Vector3i vec,HashSet<INode> objList) {
		ISingularGeologyData data = prototype.get3DGeologyData(testData,vec);
		for(INode obj : objList)
			data.merge(obj.get3DGeologyData(testData,vec));
		return data;
	}
	
	protected ISingularGeologyData mergeData(ISingularGeologyData testData, 
			Vector2i vec,HashSet<INode> objList) {
		ISingularGeologyData data = prototype.get2DGeologyData(testData,vec);
		for(INode obj : objList)
			data.merge(obj.get2DGeologyData(testData,vec));
		return data;
	}

	@Override
	public final boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof AbstractNode)) {
			return false;
		}
		AbstractNode user = (AbstractNode) o;
		return user.hashCode()==user.hashCode();
	}
	
	@Override
	public final int hashCode(){
		return prototype.hashCode();
	}
	@Override
	public final ISingularGeologyData getStartingData(ISingularGeologyData dataType) {
		return prototype.getStartingData(dataType);
	}
}
