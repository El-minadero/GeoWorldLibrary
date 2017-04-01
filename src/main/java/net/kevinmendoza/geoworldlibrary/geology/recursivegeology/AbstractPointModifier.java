package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.TreeSet;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Comparison;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.IGeologyData;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifierFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.RegionPointGenerator;

abstract class AbstractPointModifier extends Comparison implements IGeologyNode {

	private PointModifier modifier;
	
	AbstractPointModifier(PointModifier modifier){
		this.modifier = modifier;
	}
	
	public AbstractPointModifier(AbstractPrototype prototype) {
		this.modifier = prototype.getPointModifier();
	}
	
	public final void nullMap() {
		modifier = PointModifierFactory.CreateNullPointOffset();
	}
	public final Vector2i getRandomInternalPoint() {
		Vector2i vec = getBlankRandomPoint();
		return vec;
	}
	public Vector2i modifyVector(Vector2i vec) {
		return modifier.getOffsetPoint(vec);
	}
	public Vector3i modifyVector(Vector3i vec) {
		Vector3i mod = modifier.getOffsetPoint(vec);
		return new Vector3i(mod.getX(),vec.getY(),mod.getZ());
	}
	public final double getExternalDecay(Vector2i vec) {
		Vector2i mod = modifyVector(vec);
		return getPrototypeExternalDecay(mod);
	}
	public final double getExternalDecay(Vector3i vec) {
		Vector3i mod = modifyVector(vec);
		return getPrototypeExternalDecay(mod);
	}
	public final double getInternalDecay(Vector2i vec) {
		Vector2i mod = modifyVector(vec);
		return getPrototypeExternalDecay(mod);
	}
	public final double getInternalDecay(Vector3i vec) {
		Vector3i mod = modifyVector(vec);
		return getPrototypeExternalDecay(mod);
	}
	public final boolean isVectorInRegion(Vector2i vec) {
		Vector2i mod = modifyVector(vec);
		return isVectorInsidePrototype(mod);
	}
	public final boolean isVectorInRegion(Vector3i vec) {
		Vector3i mod = modifyVector(vec);
		return isVectorInsidePrototype(mod);
	}
	public final boolean isUnmodifiedVectorInRegion(Vector2i vec) {
		return isVectorInsidePrototype(vec);
	}
	public final boolean isUnmodifiedVectorInRegion(Vector3i vec) {
		return isVectorInsidePrototype(vec);
	}
	
	public final IGeologyData get2DGeologyData(IGeologyData t,Vector2i query) { 
		Vector2i vec = modifyVector(query);
		return getProtected2DGeologyData(t,vec);
	}
	
	public final IGeologyData get3DGeologyData(IGeologyData t,Vector3i query) { 
		Vector3i vec = modifyVector(query);
		return getProtected3DGeologyData(t,vec);
	}
	
	protected abstract Vector2i getBlankRandomPoint();
	
	protected abstract IGeologyData getProtected2DGeologyData(IGeologyData t,Vector2i query);
	protected abstract IGeologyData getProtected3DGeologyData(IGeologyData t,Vector3i query);

	protected abstract double getPrototypeExternalDecay(Vector2i vec);
	protected abstract double getPrototypeExternalDecay(Vector3i vec);
	
	protected abstract double getPrototypeInternalDecay(Vector2i vec);
	protected abstract double getPrototypeInternalDecay(Vector3i vec);
	
	protected abstract boolean isVectorInsidePrototype(Vector2i vec);
	protected abstract boolean isVectorInsidePrototype(Vector3i vec);
	
}
