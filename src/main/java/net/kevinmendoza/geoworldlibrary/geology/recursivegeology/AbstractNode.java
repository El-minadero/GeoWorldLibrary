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

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Comparison;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.DataFactory;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.IGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;

abstract class AbstractNode extends Comparison implements IGeologyNode {

	private final IGeologyNode prototype;
	private HashSet<IGeologyNode> internal;
	private HashSet<IGeologyNode> external;
	private HashSet<IGeologyNode> total;
	
	AbstractNode(NodeBuilder nodeBuilder){
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
	
	@Override
	public final void primeGeneration(GenerationData metaData) {
		clearChildren();
		Vector2i vec = metaData.get2DCoordinate();
		Vector2i mod = prototype.modifyVector(vec);
		GenerationData data2 = metaData.getCopy();
		data2.set2DCoordinate(mod);
		prime(data2);
		primeGenerationList(data2);
	}
	
	protected final IGeologyData getPrototype2DData(IGeologyData data, Vector2i vec) 
	{ return prototype.get2DGeologyData(data, vec); }
	protected final IGeologyData getPrototype3DData(IGeologyData data, Vector3i vec)
	{ return prototype.get3DGeologyData(data, vec); }
	
	public final double getExternalDecay(Vector2i vec) { return prototype.getExternalDecay(vec); }
	public final double getExternalDecay(Vector3i vec) { return prototype.getExternalDecay(vec); }
	
	public final double getInternalDecay(Vector2i vec) { return prototype.getExternalDecay(vec); }
	public final double getInternalDecay(Vector3i vec) { return prototype.getExternalDecay(vec); }
	
	public final boolean isVectorInRegion(Vector2i vec)  { return prototype.isVectorInRegion(vec); }
	public final boolean isVectorInRegion(Vector3i vec)  { return prototype.isVectorInRegion(vec); }
	
	public boolean isUnmodifiedVectorInRegion(Vector2i vec) { return prototype.isUnmodifiedVectorInRegion(vec); }
	public boolean isUnmodifiedVectorInRegion(Vector3i vec) { return prototype.isUnmodifiedVectorInRegion(vec); }
	
	public final  Vector2i modifyVector(Vector2i vec)  { return prototype.modifyVector(vec); }
	public final  Vector3i modifyVector(Vector3i vec)  { return prototype.modifyVector(vec); }

	public Vector2i getRandomInternalPoint() { return prototype.getRandomInternalPoint(); }


	@Override
	public IGeologyData get2DGeologyData(IGeologyData testDat, Vector2i query) {
		return getProtected2DGeologyData(testDat, query);
	}

	public IGeologyData get3DGeologyData(IGeologyData testDat, Vector3i query) {
		return getProtected3DGeologyData(testDat, query);
	}
	
	protected void clearChildren() {
		internal.clear();
		external.clear();
		total.clear();
	}
	private final void primeGenerationList(GenerationData data) {
		prototype.primeGeneration(data);
		if(!internal.isEmpty() )
			for(IGeologyNode obj : internal)
				obj.primeGeneration(data);
		else if(!external.isEmpty())
			for(IGeologyNode obj : external)
				obj.primeGeneration(data);
		total.addAll(internal);
		total.addAll(external);
		
	}
	
	protected abstract void prime(GenerationData metaData);
	
	protected final void setInternalList(Collection<IGeologyNode> i) {
		internal.addAll(i);
	}
	
	protected final void setExternalList(Collection<IGeologyNode> i) {
		external.addAll(i);
	}

	protected IGeologyData getProtected2DGeologyData(IGeologyData testDat,Vector2i vec) {
		if(total.isEmpty())
			return prototype.get2DGeologyData(testDat,vec);
		HashSet<IGeologyNode> tempTotal = (HashSet<IGeologyNode>) total.clone();
		return getCombined2DConditions(testDat,vec,tempTotal);
	}

	protected IGeologyData getProtected3DGeologyData(IGeologyData testDat,Vector3i vec){
		if(total.isEmpty())
			return prototype.get3DGeologyData(testDat,vec);
		HashSet<IGeologyNode> tempTotal = (HashSet<IGeologyNode>) total.clone();
		return getCombined3DConditions(testDat,vec,tempTotal);
	}

	protected abstract  IGeologyData getCombined2DConditions(IGeologyData testData, Vector2i vec
			, HashSet<IGeologyNode> tempTotal);

	protected abstract IGeologyData getCombined3DConditions(IGeologyData testData, Vector3i vec
			, HashSet<IGeologyNode> tempTotal);
	
	protected void mergeData(IGeologyData testData, IGeologyData data, 
			Vector3i vec,HashSet<IGeologyNode> objList) {
		for(IGeologyNode obj : objList)
			data.merge(obj.get3DGeologyData(testData,vec));
	}
	
	protected void mergeData(IGeologyData testData, IGeologyData data, 
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

	protected Vector2i getBlankRandomPoint() {
		return prototype.getRandomInternalPoint();
	}

	public int getSubOrder() { return prototype.getSubOrder(); }
	public Order getOrder() { return prototype.getOrder(); }

	public final String getName() { return prototype.getName(); }
	public final boolean isLeaf() { return false; }
}
