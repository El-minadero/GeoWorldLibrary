package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.Alteration;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.Replacement;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.Surface;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.other.GeologyData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.other.GeologyDataFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModificationFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.RegionFactory;

public abstract class GeologyPrototype implements GeologyObject {
	
	public static interface PrototypeBuilder {
		Region getRegion();
		Order getOrder();
		double getExternalDecayConstant();
		double getInternalDecayConstant();
		PointModifier getOffsetMap();
	}
	
	private final double INTERNAL_DECAY;
	private final double EXTERNAL_DECAY;
	private static final double zeroThreshold = 0.05;
	private final Region region;
	private final Order order;

	public GeologyPrototype(PrototypeBuilder builder) {
		INTERNAL_DECAY= builder.getInternalDecayConstant();
		EXTERNAL_DECAY= builder.getExternalDecayConstant();
		order		  = builder.getOrder();
		region = RegionFactory.MakeRegionOffsetByMap(builder.getRegion(),builder.getOffsetMap());
	}
	protected abstract GeologyData<Surface> getSurfaceData(Vector2i query);
	protected abstract GeologyData<Alteration> getAlterationData(Vector3i query);
	protected abstract GeologyData<Replacement> getReplacementData(Vector3i query);
	
	public final GeologyData<Surface> getSurface(Vector2i query) {
		GeologyData<Surface> data = getSurfaceData(query);
		return GeologyDataFactory.<Surface>GetGeologyDataWithData(order,data);
	}
	
	public final GeologyData<Alteration> getAlteration(Vector3i query) {
		GeologyData<Alteration> data = getAlterationData(query);
		return GeologyDataFactory.<Alteration>GetGeologyDataWithData(order,data);
	}
	
	public final GeologyData<Replacement> getReplacement(Vector3i query) {
		GeologyData<Replacement> data = getReplacementData(query);
		return GeologyDataFactory.<Replacement>GetGeologyDataWithData(order,data);
	}

	private double getDecayMult(double decay, Vector2i vec) {
		double val =  Math.exp(-getSuperRegion().getNormalizedDistanceToEdge(vec)*decay);
		if(val > zeroThreshold)
			return val;
		else
			return 0;
	}
	
	protected double getInternalDecay(Vector2i vec) {
		double val = 1 - getDecayMult(INTERNAL_DECAY,vec);
		return val;
	}
	
	protected double getExternalDecay(Vector2i vec) {
		double val = getDecayMult(EXTERNAL_DECAY,vec);
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

	public final boolean isPrototype() { return true; }
	

	@Override
	public final boolean isVectorInRegion(Vector2i center) {
		return getSuperRegion().isInside(center);
	}


	@Override
	public final boolean isVectorInRegion(Vector3i query) {
		return getSuperRegion().isInside(PointModificationFactory.extract2iVector(query));
	}
	
	public boolean shouldBuildRegion(Vector2i vec) {
		return true;
	}

}
