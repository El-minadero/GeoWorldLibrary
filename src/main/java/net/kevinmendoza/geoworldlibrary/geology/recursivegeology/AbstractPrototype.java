package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.Collection;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Comparison;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.IGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifierFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.RegionFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.RegionPointGenerator;

public abstract class AbstractPrototype extends AbstractPointModifier {
	
	private final double INTERNAL_DECAY;
	private final double EXTERNAL_DECAY;
	private static int TOTAL;
	private final int SUB_ORDER;
	private final Region region;
	private final Order order;
	private final PointModifier pointModifier;

	public AbstractPrototype(IPrototypeBuilder builder) {
		super(builder.getPointModifierMap());
		pointModifier = builder.getPointModifierMap();
		SUB_ORDER = TOTAL;
		TOTAL++;
		INTERNAL_DECAY= builder.getInternalDecayConstant();
		EXTERNAL_DECAY= builder.getExternalDecayConstant();
		order		  = builder.getOrder();
		if(builder.getRegion()!=null)
			region = builder.getRegion();
		else
			region=null;
	}
	
	public AbstractPrototype(Order order) {
		super(PointModifierFactory.CreateNullPointOffset());
		pointModifier = PointModifierFactory.CreateNullPointOffset();
		SUB_ORDER = TOTAL;
		TOTAL++;
		INTERNAL_DECAY= 1;
		EXTERNAL_DECAY= 1;
		this.order    = order;
		region 		  = null;
	}
	public PointModifier getPointModifier() {
		return pointModifier;
	}
	
	protected abstract IGeologyData getGeologyData(IGeologyData testData, Vector2i query);
	protected abstract IGeologyData getGeologyData(IGeologyData testData, Vector3i query);

	protected final IGeologyData getProtected2DGeologyData(IGeologyData t,Vector2i query) { 
		IGeologyData data = getGeologyData(t,query);
		if(region!=null && !region.isInside(query)) {
			data.applyMultiplier(getExternalDecay(query));
		}
		return data;
	}
	
	protected final IGeologyData getProtected3DGeologyData(IGeologyData t,Vector3i query) { 
		IGeologyData data = getGeologyData(t,query);
		if(region!=null && !region.isInside(query)) {
			data.applyMultiplier(getExternalDecay(query));
		}
		return data;
	}
	
	protected double getPrototypeExternalDecay(Vector3i vec) {
		if(region==null) {
			return 1;
		}
		return getExternalDecay(PointModifierFactory.Extract2iVector(vec));
	}
	
	protected double getPrototypeExternalDecay(Vector2i vec) {
		if(region==null) {
			return 1;
		}
		double val = 1-region.getNormalizedDistanceToEdge(vec)*EXTERNAL_DECAY;
		if(val > 1)
			return 1;
		else if(val < ZERO_THRESHOLD)
			return 0;
		else
			return val;
	}
	
	protected final double getDistanceToEdge(Vector3i vec) {
		return region.getNormalizedDistanceToEdge(vec);
	}
	protected final double getDistanceToEdge(Vector2i vec) {
		return region.getNormalizedDistanceToEdge(vec);
	}
	
	protected double getPrototypeInternalDecay(Vector3i vec) {
		if(region==null) {
			return 1;
		}
		return getInternalDecay(PointModifierFactory.Extract2iVector(vec));
	}

	protected double getPrototypeInternalDecay(Vector2i vec) {
		if(region==null) {
			return 0;
		}
		double val = 1 - region.getNormalizedDistanceToEdge(vec)*INTERNAL_DECAY;
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
	
	public RegionPointGenerator getKey() {
		return region;
	}

	public final boolean isLeaf() { return true; }
	

	@Override
	protected final boolean isVectorInsidePrototype(Vector2i center) {
		return region.isInside(center);
	}


	@Override
	protected final boolean isVectorInsidePrototype(Vector3i query) {
		return region.isInside(query);
	}
	
	public final int getDepth() { return 1; }
	public final int getSubOrder() { return SUB_ORDER; }
	public final Order getOrder() { return order; }
	public void debug() {}
	
	protected Vector2i getBlankRandomPoint() {
		if(region!=null) {
			return region.getRandomInternalPoint();
		}
		return new Vector2i(0,0);
	}
}
