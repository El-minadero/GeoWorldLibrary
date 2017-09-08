package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import com.google.common.reflect.TypeToken;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.configuration.PrimitiveWrapperConfig.IntMinMax;
import net.kevinmendoza.geoworldlibrary.utilities.HashCodeOperations;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class RegionConfiguration {
	public static final TypeToken<RegionConfiguration> type = TypeToken.of(RegionConfiguration.class);
	@Setting(comment="Dimension constraints. Goes at x, y, z")
	private List<IntMinMax> axis;
	@Setting
	private RegionTypes regionType;

	public RegionConfiguration(List<IntMinMax> constraints,RegionTypes type){
		this.axis = new ArrayList<>();
		this.regionType = type;
		axis.addAll(constraints);
		while(axis.size()<type.getConstraintNumber()) {
			axis.add(constraints.get(0));
		}
		
	}

	public Region getRegion(Vector2i vec) {
		long seed = HashCodeOperations.createVectorSeed(vec);
		Region region;
		if(regionType.is3D()) {
			int[] a = {axis.get(0).getValue(seed), axis.get(1).getValue(seed+2), axis.get(2).getValue(seed+4)};
			double[] axis = Doubles.toArray(Ints.asList(a));
			region = AbstractRegionFactory
					.getRegionFactoryType(regionType)
					.getRegion(vec,axis,true);
		}
		else {
			int[] a = {axis.get(0).getValue(seed), axis.get(1).getValue(seed+2)};
			double[] axis = Doubles.toArray(Ints.asList(a));
			region = AbstractRegionFactory
					.getRegionFactoryType(regionType)
					.getRegion(vec,axis,true);
		}
		return region;
	}

	public Region getRegion(Vector3i vec) {
		long seed = HashCodeOperations.createVectorSeed(vec);
		Region region=null;
		if(regionType.is3D()) {
			int[] a = {axis.get(0).getValue(seed), axis.get(1).getValue(seed+2), axis.get(2).getValue(seed+4)};
			double[] axis = Doubles.toArray(Ints.asList(a));
			region = AbstractRegionFactory
					.getRegionFactoryType(regionType)
					.getRegion(vec,axis,true);
		}
		else {
			int[] a = {axis.get(0).getValue(seed), axis.get(1).getValue(seed+2)};
			double[] axis = Doubles.toArray(Ints.asList(a));
			region = AbstractRegionFactory
					.getRegionFactoryType(regionType)
					.getRegion(vec,axis,true);
		}
		return region;
	}
	
}
