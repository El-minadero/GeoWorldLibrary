package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydata.GeologyData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydata.GeologyDataContainer;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydata.GeologyDataFactory;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydatainterface.Comparison;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModificationFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.RegionFactory;

public abstract class GeologyPrototype extends Comparison implements GeologyComposite {
	
	private final double INTERNAL_DECAY;
	private final double EXTERNAL_DECAY;
	private final Region region;
	private final Order order;

	public GeologyPrototype(GeologyPrototypeBuilder builder) {
		INTERNAL_DECAY= builder.getInternalDecayConstant();
		EXTERNAL_DECAY= builder.getExternalDecayConstant();
		order		  = builder.getOrder();
		region = RegionFactory.MakeRegionOffsetByMap(builder.getRegion(),builder.getOffsetMap());
	}
	
	public GeologyPrototype(Order order) {
		INTERNAL_DECAY= 0;
		EXTERNAL_DECAY= 0;
		this.order    = order;
		region 		  = null;
	}
	
	protected abstract <T extends GeologyData<T>> T getGeologyData(T t, Vector2i query);
	protected abstract <T extends GeologyData<T>> T getGeologyData(T t, Vector3i query);
	
	public <T extends GeologyData<T>> GeologyDataContainer<T> get2DGeologyData(T t,Vector2i query) {
		T data = this.<T>getGeologyData(t,query);
		return GeologyDataFactory.<T>CreateGeologyDataContainer(order,data);
	}
	
	public <T extends GeologyData<T>> GeologyDataContainer<T> get3DGeologyData(T t,Vector3i query) {
		T data = this.<T>getGeologyData(t,query);
		return GeologyDataFactory.<T>CreateGeologyDataContainer(order,data);
	}
	
	public double getDecay(Vector3i vec) {
		return getDecay(PointModificationFactory.extract2iVector(vec));
	}
	
	public double getDecay(Vector2i vec) {
		if(isVectorInRegion(vec))
			return getInternalDecay(vec);
		else
			return getExternalDecay(vec);
	}
	
	protected double getInternalDecay(Vector2i vec) {
		double val = getSuperRegion().getNormalizedDistanceToEdge(vec)*INTERNAL_DECAY;
		if(val > 1)
			return 1;
		else if(val < ZERO_THRESHOLD)
			return 0;
		else
			return val;
	}
	
	protected double getExternalDecay(Vector2i vec) {
		double val = 1-getSuperRegion().getNormalizedDistanceToEdge(vec)*INTERNAL_DECAY;
		if(val > 1)
			return 1;
		else if(val < ZERO_THRESHOLD)
			return 0;
		else
			return val;
	}
	
	@Override
	public final int hashCode() {
		return region.hashCode();
	}
	
	@Override
	public final boolean equals(Object o) {
		return region.equals(o);
	}
	
	public final Region getSuperRegion() {
		return region;
	}

	public final boolean isLeaf() { return true; }
	

	@Override
	public final boolean isVectorInRegion(Vector2i center) {
		return getSuperRegion().isInside(center);
	}


	@Override
	public final boolean isVectorInRegion(Vector3i query) {
		return getSuperRegion().isInside(PointModificationFactory.extract2iVector(query));
	}

}
