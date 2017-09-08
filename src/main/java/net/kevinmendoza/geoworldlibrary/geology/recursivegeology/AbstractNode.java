package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.HashSet;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.Comparison;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.EmptyDataFactory;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.IGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.Order;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractAlteration;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractRock;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISingularGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.Surface;

abstract class AbstractNode extends Comparison implements IGeologyNode {

	private final IGeologyNode prototype;
	private HashSet<IGeologyNode> internal;
	private HashSet<IGeologyNode> external;
	private HashSet<IGeologyNode> total;
	
	AbstractNode(INodeBuilder nodeBuilder){
		prototype =  nodeBuilder.getPrototype();
		internal 	   = new HashSet<>();
		external 	   = new HashSet<>();
		total 		   = new HashSet<>();
	}
	AbstractNode(AbstractPrototype prototype){
		this.prototype = prototype;
		internal 	   = new HashSet<>();
		external 	   = new HashSet<>();
		total 		   = new HashSet<>();
	}
	
	public AbstractNode() {
		this.prototype = new NullPrototype();
		internal 	   = new HashSet<>();
		external 	   = new HashSet<>();
		total 		   = new HashSet<>();
	}
	
	public int getRGBDebugAtCoordinates(Vector3i query) {
		int rgb =0;
		for(IGeologyNode n : total) {
			rgb+=n.getRGBDebugAtCoordinates(query);
		}
		rgb+=prototype.getRGBDebugAtCoordinates(query);
		rgb+=additionalInfoRGBDebug(query);
		return rgb;
	}
	protected int additionalInfoRGBDebug(Vector3i query){
		return 0;
	}

	public int getSubOrder() { return prototype.getSubOrder(); }
	public Order getOrder() { return prototype.getOrder(); }

	public final String getName() { return prototype.getName(); }
	public final boolean isLeaf() { return false; }
	
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
			for(IGeologyNode obj : internal)
				obj.loadNearbyNodes(data);
		else if(!external.isEmpty())
			for(IGeologyNode obj : external)
				obj.loadNearbyNodes(data);
		total.addAll(internal);
		total.addAll(external);
		
	}
	
	public void primeGeneratedObjects(GenerationData data){
		prototype.primeLoadedObjects(data);
		if(!internal.isEmpty())
			for(IGeologyNode obj : internal)
				obj.primeLoadedObjects(data);
		else if(!external.isEmpty())
			for(IGeologyNode obj : external)
				obj.primeLoadedObjects(data);
	}
	
	protected abstract void cacheNearbyNodes(GenerationData metaData);
	
	protected final void setInternalList(Collection<IGeologyNode> i) {
		internal.addAll(i);
	}
	
	protected final void setExternalList(Collection<IGeologyNode> i) {
		external.addAll(i);
	}

	protected final ISingularGeologyData getPrototype3DData(ISingularGeologyData testData,Vector3i query) {
		return prototype.get3DGeologyData(testData, query);
	}
	protected final ISingularGeologyData getPrototype2DData(ISingularGeologyData testData,Vector2i query) {
		return prototype.get2DGeologyData(testData, query);
	}
	public final double getExternalDecay(Vector2i vec) { return prototype.getExternalDecay(vec); }
	public final double getExternalDecay(Vector3i vec) { return prototype.getExternalDecay(vec); }
	
	public final double getInternalDecay(Vector2i vec) { return prototype.getInternalDecay(vec); }
	public final double getInternalDecay(Vector3i vec) { return prototype.getInternalDecay(vec); }
	
	public final boolean isVectorInRegion(Vector2i vec)  { return prototype.isVectorInRegion(vec); }
	public final boolean isVectorInRegion(Vector3i vec)  { return prototype.isVectorInRegion(vec); }

	public Vector2i getRandomInternalPoint() { return prototype.getRandomInternalPoint(); }

	@Override
	public ISingularGeologyData get2DGeologyData(ISingularGeologyData testDat, Vector2i vec) {
		if(total.isEmpty())
			return prototype.get2DGeologyData(testDat,vec);
		HashSet<IGeologyNode> tempTotal = (HashSet<IGeologyNode>) total.clone();
		return getCombined2DConditions(testDat,vec,tempTotal);
	}

	public ISingularGeologyData get3DGeologyData(ISingularGeologyData testDat, Vector3i vec) {
		if(total.isEmpty())
			return prototype.get3DGeologyData(testDat,vec);
		HashSet<IGeologyNode> tempTotal = (HashSet<IGeologyNode>) total.clone();
		return getCombined3DConditions(testDat,vec,tempTotal);
	}

	protected abstract  ISingularGeologyData getCombined2DConditions(ISingularGeologyData testData, Vector2i vec
			, HashSet<IGeologyNode> tempTotal);

	protected abstract ISingularGeologyData getCombined3DConditions(ISingularGeologyData testData, Vector3i vec
			, HashSet<IGeologyNode> tempTotal);
	
	protected void mergeData(ISingularGeologyData testData, ISingularGeologyData dataNode, 
			Vector3i query,HashSet<IGeologyNode> objList) {
		for(IGeologyNode obj : objList)
			dataNode.merge(obj.get3DGeologyData(testData,query));
	}
	
	protected void mergeData(ISingularGeologyData testData, ISingularGeologyData data, 
			Vector2i vec,HashSet<IGeologyNode> objList) {
		for(IGeologyNode obj : objList)
			data.merge(obj.get2DGeologyData(testData,vec));
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
