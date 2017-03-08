package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologicalFactory.GeologyFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;

public class GeologyMapCache{

	private HashMap<Vector2i,CompositeGeologyInterface> geoMap;
	private GeologyFactory factory;
	
	public GeologyMapCache(GeologyFactory factory) {
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
	
	public void addRegion(Vector2i vec, CompositeGeologyInterface region) {
		geoMap.put(vec, region);
	}
	
	public List<CompositeGeologyInterface> getInternalRegions(List<Vector2i> surroundingKeys, Vector2i check) {
		List<CompositeGeologyInterface> potentialVectors = new ArrayList<>();
		for(Vector2i key : surroundingKeys) {
			if(geoMap.containsKey(key) && geoMap.get(key)!=null)
				if(geoMap.get(key).isVectorInRegion(check)) { {
					CompositeGeologyInterface temp = geoMap.get(key);
					if(temp.isLeaf()) {
						temp = factory.makeObject((GeologyPrototype)(temp));
						geoMap.put(key, temp);
					}
					potentialVectors.add(temp);
				}
			}
		}
		return potentialVectors;
	}
	
	public List<CompositeGeologyInterface> getExternalRegions(List<Vector2i> surroundingKeys, Vector2i check) {
		List<CompositeGeologyInterface> potentialVectors = new ArrayList<>();
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
