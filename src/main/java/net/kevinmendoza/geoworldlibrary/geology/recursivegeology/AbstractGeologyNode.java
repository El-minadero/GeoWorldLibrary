package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.ArrayList;
import java.util.List;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.Geology;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydata.GeologyData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydata.GeologyDataContainer;

abstract class AbstractGeologyNode implements Geology {

	private final CompositeGeologyInterface prototype;
	private List<CompositeGeologyInterface> internal;
	private List<CompositeGeologyInterface> external;
	
	AbstractGeologyNode(CompositeGeologyInterface prototype){
		this.prototype = prototype;
		internal 		= new ArrayList<>();
		external 		= new ArrayList<>();
	}
	
	protected final boolean canBuildRegion(Vector2i vec) {
		return prototype.shouldBuildRegion(vec);
	}
	
	protected final void primeGenerationList(GenerationData data) {
		prototype.primeGeneration(data);
		if(!internal.isEmpty() )
			for(CompositeGeologyInterface obj : external)
				obj.primeGeneration(data);
		else if(!external.isEmpty())
			for(CompositeGeologyInterface obj : internal)
				obj.primeGeneration(data);
	}
	
	protected final void clearSubObjectList() {
		internal.clear();
		external.clear();
	}
	
	protected final void addToInternalList(List<CompositeGeologyInterface> i) {
		internal = i;
	}
	
	protected final void addToExternalList(List<CompositeGeologyInterface> i) {
		external = i;
	}
	
	public final <T extends GeologyData<T>> GeologyDataContainer<T> get2DGeologyData(Class<T> t,Vector2i query) {
		if(internal.isEmpty() && external.isEmpty())
			return prototype.<T>get2DGeologyData(t,query);
		List<CompositeGeologyInterface> objs = getRelevantObjects();
		return this.<T>get2DConditions(t,query,objs,internal.isEmpty());
	}

	public final <T extends GeologyData<T>> GeologyDataContainer<T> get3DGeologyData(Class<T> t,Vector3i query){
		if(internal.isEmpty() && external.isEmpty())
			return prototype.<T>get3DGeologyData(t,query);
		List<CompositeGeologyInterface> objs = getRelevantObjects();
		return this.<T>get3DConditions(t,query,objs,internal.isEmpty());
	}

	private <T extends GeologyData<T>> GeologyDataContainer<T> get2DConditions(Class<T> t, Vector2i query,
			List<CompositeGeologyInterface> objList, boolean b) {
		GeologyDataContainer<T> data=null;
		if(b) {
			data = prototype.<T>get2DGeologyData(t,query);
			for(CompositeGeologyInterface obj : objList)
				data.merge(obj.<T>get2DGeologyData(t,query),obj.getDecay(query));
		}
		else {
			for(CompositeGeologyInterface obj : objList) {
				if(data==null)
					data = obj.<T>get2DGeologyData(t,query);
				else
					data.merge(obj.<T>get2DGeologyData(t,query),obj.getDecay(query));
			}
		}
		return data;
	}

	private <T extends GeologyData<T>> GeologyDataContainer<T> get3DConditions(Class<T> t, Vector3i query
			,List<CompositeGeologyInterface> objList, boolean b) {
		GeologyDataContainer<T> data=null;
		if(b) {
			data = prototype.<T>get3DGeologyData(t,query);
			for(CompositeGeologyInterface obj : objList)
				data.merge(obj.<T>get3DGeologyData(t,query),obj.getDecay(query));
		}
		else {
			for(CompositeGeologyInterface obj : objList) {
				if(data==null)
					data = obj.<T>get3DGeologyData(t,query);
				else
					data.merge(obj.<T>get3DGeologyData(t,query),obj.getDecay(query));
			}
		}
		return data;
	}
	
	private List<CompositeGeologyInterface> getRelevantObjects() {
		if(internal.isEmpty())
			return external;
		return internal;
	}
}
