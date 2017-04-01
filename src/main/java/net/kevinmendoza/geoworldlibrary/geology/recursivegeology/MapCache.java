package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeologyNode;
import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Comparison;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;

class MapCache extends Comparison {

	private HashMap<Vector2i,IGeologyNode> geoMap;
	private AbstractPrototypeFactory factory;
	private boolean debugMode;
	
	public MapCache(AbstractPrototypeFactory factory, boolean b) {
		debugMode = b;
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
	
	public void addRegion(Vector2i vec, IGeologyNode region) {
		geoMap.put(vec, region);
	}
	
	public LinkedList<IGeologyNode> getInternalRegions(List<Vector2i> surroundingKeys, Vector2i check) {
		LinkedList<IGeologyNode> potentialVectors = new LinkedList<>();
		IGeologyNode temp = null;
		for(Vector2i key : surroundingKeys) {
			if(geoMap.containsKey(key) && geoMap.get(key)!=null) {
				temp = geoMap.get(key);
				if(temp.isVectorInRegion(check)) { 
					if(temp.isLeaf()) {
						temp =  factory.makeObject((AbstractPrototype)(temp));
						geoMap.put(key, (IGeologyNode) temp);
					}
					potentialVectors.add(temp);
				}
			}
		}
		return potentialVectors;
	}

	public LinkedList<IGeologyNode> getExternalRegions(List<Vector2i> surroundingKeys, Vector2i check) {
		LinkedList<IGeologyNode> potentialVectors = new LinkedList<>();
		for(Vector2i key : surroundingKeys) {
			if(geoMap.containsKey(key) && geoMap.get(key)!=null) {
				if(!geoMap.get(key).isVectorInRegion(check)) { 
					if(!isZero(geoMap.get(key).getExternalDecay(check))) 
						potentialVectors.add(geoMap.get(key));
				}
			}
		}
		return potentialVectors;
	}

}
