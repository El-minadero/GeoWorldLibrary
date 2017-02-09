package net.kevinmendoza.geoworldlibrary.geology.region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.geology.geologicobject.GeologicObjectInterface;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;

public class SubRegionMapCache {

	private HashMap<Region,GeologicObjectInterface> geoMap;
	
	public SubRegionMapCache() {
		geoMap = new HashMap<>();
	}

	public boolean isInRegions(Vector2i check) {
		for(Region reg : geoMap.keySet()) {
			if(reg.isInside(check)){
				return true;
			}
		}
		return false;
	}
	
	public List<GeologicObjectInterface> getOverlappingObjects(Vector2i check) {
		List<GeologicObjectInterface> potentialVectors = new ArrayList<>();
		for(Region reg : geoMap.keySet()) {
			if(reg.isInside(check)){
				potentialVectors.add(geoMap.get(reg));
			}
		}
		return potentialVectors;
	}
	
	public List<Region> getUninitializedRegionKeys(Vector2i check)  {
		List<Region> potentialVectors = new ArrayList<>();
		for(Region reg : geoMap.keySet()) {
			if(geoMap.get(reg)==null && reg.isInside(check)){
				potentialVectors.add(reg);
			}
		}
		return potentialVectors;
	}
	
	public void addRegion(Region region) {
		geoMap.put(region,null);
	}

	public void putObjectInRegion(GeologicObjectInterface obj) {
		geoMap.put(obj.getRegion(), obj);
	}

}
