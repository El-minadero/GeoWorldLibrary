package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;

class GeologyMapCache{

	private HashMap<Vector2i,GeologyComposite> geoMap;
	private AbstractPrototypeFactory factory;
	
	public GeologyMapCache(AbstractPrototypeFactory factory) {
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
	
	public void addRegion(Vector2i vec, GeologyComposite region) {
		geoMap.put(vec, region);
	}
	
	public List<GeologyComposite> getInternalRegions(List<Vector2i> surroundingKeys, Vector2i check) {
		List<GeologyComposite> potentialVectors = new ArrayList<>();
		for(Vector2i key : surroundingKeys) {
			if(geoMap.containsKey(key) && geoMap.get(key)!=null)
				if(geoMap.get(key).isVectorInRegion(check)) { {
					GeologyComposite temp = geoMap.get(key);
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
	
	public List<GeologyComposite> getExternalRegions(List<Vector2i> surroundingKeys, Vector2i check) {
		List<GeologyComposite> potentialVectors = new ArrayList<>();
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
