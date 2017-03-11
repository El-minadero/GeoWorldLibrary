package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.ArrayList;
import java.util.List;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.Geology;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydata.GeologyData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydata.GeologyDataContainer;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydatainterface.Comparison;

abstract class AbstractGeologyNode extends Comparison implements Geology {

	private final GeologyComposite prototype;
	private List<GeologyComposite> internal;
	private List<GeologyComposite> external;
	
	AbstractGeologyNode(GeologyComposite prototype){
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
			for(GeologyComposite obj : external)
				obj.primeGeneration(data);
		else if(!external.isEmpty())
			for(GeologyComposite obj : internal)
				obj.primeGeneration(data);
	}
	
	protected final void clearSubObjectList() {
		internal.clear();
		external.clear();
	}
	
	protected final void setInternalList(List<GeologyComposite> i) {
		internal = i;
	}
	
	protected final void setExternalList(List<GeologyComposite> i) {
		external = i;
	}
	
	public final <T extends GeologyData<T>> GeologyDataContainer<T> get2DGeologyData(T t,Vector2i query) {
		if(internal.isEmpty() && external.isEmpty())
			return prototype.<T>get2DGeologyData(t,query);
		List<GeologyComposite> objs = getRelevantObjects();
		return this.<T>get2DConditions(t,query,objs,internal.isEmpty());
	}

	public final <T extends GeologyData<T>> GeologyDataContainer<T> get3DGeologyData(T t,Vector3i query){
		if(internal.isEmpty() && external.isEmpty())
			return prototype.<T>get3DGeologyData(t,query);
		List<GeologyComposite> objs = getRelevantObjects();
		return this.<T>get3DConditions(t,query,objs,internal.isEmpty());
	}

	private <T extends GeologyData<T>> GeologyDataContainer<T> get2DConditions(T t, Vector2i query,
			List<GeologyComposite> objList, boolean b) {
		GeologyDataContainer<T> data=null;
		if(b) {
			data = prototype.<T>get2DGeologyData(t,query);
			for(GeologyComposite obj : objList)
				data.merge(obj.<T>get2DGeologyData(t,query),obj.getDecay(query));
		}
		else {
			for(GeologyComposite obj : objList) {
				if(data==null)
					data = obj.<T>get2DGeologyData(t,query);
				else
					data.merge(obj.<T>get2DGeologyData(t,query),obj.getDecay(query));
			}
		}
		return data;
	}

	private <T extends GeologyData<T>> GeologyDataContainer<T> get3DConditions(T t, Vector3i query
			,List<GeologyComposite> objList, boolean b) {
		GeologyDataContainer<T> data=null;
		if(b) {
			data = prototype.<T>get3DGeologyData(t,query);
			for(GeologyComposite obj : objList)
				data.merge(obj.<T>get3DGeologyData(t,query),obj.getDecay(query));
		}
		else {
			for(GeologyComposite obj : objList) {
				if(data==null)
					data = obj.<T>get3DGeologyData(t,query);
				else
					data.merge(obj.<T>get3DGeologyData(t,query),obj.getDecay(query));
			}
		}
		return data;
	}
	
	private List<GeologyComposite> getRelevantObjects() {
		if(internal.isEmpty())
			return external;
		return internal;
	}
}
