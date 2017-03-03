package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologicalFactory.GeologyFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;

public class RegionMapCache{

	private HashMap<Vector2i,GeologyObject> geoMap;
	private GeologyFactory factory;
	
	public RegionMapCache(GeologyFactory factory) {
		this.factory = factory;
		geoMap = new HashMap<>();
	}

	public List<Vector2i> getRegionCentersToBuild(List<Vector2i> centers){
		List<Vector2i> potentialVectors = new ArrayList<>();
		for(Vector2i vec : centers) {
			if(!geoMap.containsKey(vec)){
				potentialVectors.add(vec);
			}
		}
		return potentialVectors;
	}
	
	public void addRegion(Vector2i vec, GeologyObject region) {
		geoMap.put(vec, region);
	}
	
	public List<GeologyObject> getInternalRegions(List<Vector2i> surroundingKeys, Vector2i check) {
		List<GeologyObject> potentialVectors = new ArrayList<>();
		for(Vector2i key : surroundingKeys) {
			if(geoMap.containsKey(key) && geoMap.get(key)!=null)
				if(geoMap.get(key).isVectorInRegion(check)) { {
					GeologyObject temp = geoMap.get(key);
					if(temp.isPrototype()) {
						temp = factory.makeObject((GeologyPrototype)(temp));
						geoMap.put(key, temp);
					}
					potentialVectors.add(temp);
				}
			}
		}
		return potentialVectors;
	}
	
	public List<GeologyObject> getExternalRegions(List<Vector2i> surroundingKeys, Vector2i check) {
		List<GeologyObject> potentialVectors = new ArrayList<>();
		for(Vector2i key : surroundingKeys) {
			if(geoMap.containsKey(key) && geoMap.get(key)!=null)
				if(!geoMap.get(key).isVectorInRegion(check)) { {
					potentialVectors.add(geoMap.get(key));
				}
			}
		}
		return potentialVectors;
	}

}
