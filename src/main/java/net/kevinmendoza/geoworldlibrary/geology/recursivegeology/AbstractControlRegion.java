package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Comparison;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.IGeologyData;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMapFactory;

abstract class AbstractControlRegion extends Comparison implements IGeologyNode {

	private NoiseMap controlMap;
	
	AbstractControlRegion(NoiseMap controlMap){
		this.controlMap = controlMap;
	}
	
	AbstractControlRegion(){
		controlMap = NoiseMapFactory.MakeSingleValueMap(1.0);
	}
	
	public final Vector2i getRandomInternalPoint() {
		Vector2i vec = getBlankRandomPoint();
		return vec;
	}
	
	/*
	 * Decays control final world expression of these prototypes.
	 * External decay:
	 * 		0 outside
	 * 		1 inside.
	 * Internal decay:
	 * 		0 at region edge;
	 * 		1 internal to edge
	 * Control map operates on the internal decay, going from 0 - 1.
	 */
	protected final double getControlMapDecay(Vector2i vec) { return controlMap.getNoise(vec); }
	protected final double getControlMapDecay(Vector3i vec) { return controlMap.getNoise(vec); }
	
	public final double getExternalDecay(Vector2i vec) { return getPrototypeExternalDecay(vec); }
	public final double getExternalDecay(Vector3i vec) { return getPrototypeExternalDecay(vec); }
	
	public final double getInternalDecay(Vector2i vec) { return getPrototypeInternalDecay(vec); }
	public final double getInternalDecay(Vector3i vec) { return getPrototypeInternalDecay(vec); }
	
	public final boolean isVectorInRegion(Vector2i vec) { return isVectorInsidePrototypeRegion(vec); }
	public final boolean isVectorInRegion(Vector3i vec) { return isVectorInsidePrototypeRegion(vec); }
	
	public final IGeologyData get2DGeologyData(IGeologyData t,Vector2i query) { 
		return getProtected2DGeologyData(t,query);
	}
	
	public final IGeologyData get3DGeologyData(IGeologyData t,Vector3i query) { 
		return getProtected3DGeologyData(t,query);
	}
	
	protected abstract Vector2i getBlankRandomPoint();
	
	protected abstract IGeologyData getProtected2DGeologyData(IGeologyData t,Vector2i query);
	protected abstract IGeologyData getProtected3DGeologyData(IGeologyData t,Vector3i query);

	protected abstract double getPrototypeExternalDecay(Vector2i vec);
	protected abstract double getPrototypeExternalDecay(Vector3i vec);
	
	protected abstract double getPrototypeInternalDecay(Vector2i vec);
	protected abstract double getPrototypeInternalDecay(Vector3i vec);
	
	protected abstract boolean isVectorInsidePrototypeRegion(Vector2i vec);
	protected abstract boolean isVectorInsidePrototypeRegion(Vector3i vec);
	
}
