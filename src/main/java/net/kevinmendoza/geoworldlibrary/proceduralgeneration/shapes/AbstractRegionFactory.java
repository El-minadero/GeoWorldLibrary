package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import java.util.HashMap;
import java.util.Random;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;

public class AbstractRegionFactory {
	 
	private static HashMap<RegionTypes,IRegionFactory> regionFactoryMap;
	
	private static IRegionFactory getRegionFactory(RegionTypes type) {
		if(regionFactoryMap==null) {
			regionFactoryMap = new HashMap<>();
		}
		if(!regionFactoryMap.containsKey(type)) {
			regionFactoryMap.put(type, type.createFactory());
		}
		return regionFactoryMap.get(type);
	}
	
	public static IRegionFactory getRegionFactoryType(RegionTypes type) { return getRegionFactory(type); }
	
}
