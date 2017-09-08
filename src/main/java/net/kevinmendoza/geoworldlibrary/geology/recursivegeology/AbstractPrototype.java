package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.awt.Color;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.Order;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISingularGeologyData;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifierFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;

public abstract class AbstractPrototype extends AbstractControlRegion {
	
	private final double INTERNAL_DECAY;
	private final double EXTERNAL_DECAY;
	private static int TOTAL;
	private final int SUB_ORDER;
	private final Region region;
	private final Order order;
	private final AbstractPrototypeFactory factory;

	public AbstractPrototype(IPrototypeBuilder builder) {
		super(builder.getControlMap());
		SUB_ORDER = TOTAL;
		factory = builder.getFactory();
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
		super();
		factory = new NullFactory();
		SUB_ORDER = TOTAL;
		TOTAL++;
		INTERNAL_DECAY= 1;
		EXTERNAL_DECAY= 1;
		this.order    = order;
		region 		  = null;
	}
	

	@Override
	public final AbstractPrototypeFactory getFactory() { return factory; }
	@Override
	public final int getRGBDebugAtCoordinates(Vector3i query) {
		int rgb =0;
		if(region!=null && region.isInside(query)) {
			rgb+=Color.PINK.getRGB();
		}
		rgb+=overrideRGBInfo(query);
		return rgb;
	}

	protected int overrideRGBInfo(Vector3i query) {
		return 0;
	}
	protected abstract ISingularGeologyData getGeologyData(ISingularGeologyData testData, Vector2i query);
	protected abstract ISingularGeologyData getGeologyData(ISingularGeologyData testData, Vector3i query);

	protected final ISingularGeologyData getProtected2DGeologyData(ISingularGeologyData t,Vector2i vec) { 
		ISingularGeologyData data = getGeologyData(t,vec);
		if(data!=null) {
			if(region!=null) {
				if(!region.isInside(vec)) {
					data.applyMultiplier(getPrototypeExternalDecay(vec));
				}
				data.applyMultiplier(getControlMapDecay(vec));
			}
		}
		return data;
	}
	
	protected final ISingularGeologyData getProtected3DGeologyData(ISingularGeologyData t,Vector3i vec) { 
		ISingularGeologyData data = getGeologyData(t,vec);
		if(data!=null) {
			if(region!=null) {
				if(!region.isInside(vec)) {
					data.applyMultiplier(getPrototypeExternalDecay(vec));
				}
				data.applyMultiplier(getControlMapDecay(vec));
			}
		}
		return data;
	}
	
	protected double getPrototypeExternalDecay(Vector3i vec) {
		if(region==null) {
			return 1;
		}
		return getPrototypeExternalDecay(PointModifierFactory.Extract2iVector(vec));
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
		return getPrototypeInternalDecay(PointModifierFactory.Extract2iVector(vec));
	}

	protected double getPrototypeInternalDecay(Vector2i vec) {
		if(region==null) {
			return 0;
		}
		double val = region.getNormalizedDistanceToEdge(vec)*INTERNAL_DECAY;
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

	public final boolean isLeaf() { return true; }
	

	@Override
	protected final boolean isVectorInsidePrototypeRegion(Vector2i center) {
		return region.isInside(center);
	}


	@Override
	protected final boolean isVectorInsidePrototypeRegion(Vector3i query) {
		return region.isInside(query);
	}
	
	public final int getDepth() { return 1; }
	public final int getSubOrder() { return SUB_ORDER; }
	public final Order getOrder() { return order; }
	public void debug() {}
	
	protected Vector2i getBlankRandomPoint() {
		if(region!=null) {
			return region.getRandomInternalPoint2i();
		}
		return new Vector2i(0,0);
	}
}
