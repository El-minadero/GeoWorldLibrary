package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologicalFactory.GeologyFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;

class GeologyObjectMap {

	private HashMap<Region,GeologyObject> geoMap;
	private GeologyFactory factory;
	
	/**
	 * 
	 * @param factory	
	 * @param subObjects number of sub objects to make
	 * @param superRegion number of regions to make
	 */
	public GeologyObjectMap(GeologyFactory factory, int subObjects,Region superRegion) {
		this.factory = factory;
		geoMap = new HashMap<>();
		populateSubRegions(subObjects,superRegion);
	}

	private void populateSubRegions(int subObjects, Region superRegion) {
		for(int i = 0; i<subObjects;i++) {
			Region subRegion = factory.makeRegion(superRegion.getRandomInternalPoint());
			GeologyObject obj = factory.makePrototype(subRegion);
			geoMap.put(obj.getSuperRegion(), obj);
		}
	}

	public boolean isVectorInRegions(Vector2i check) {
		for(Region reg : geoMap.keySet()) {
			if(reg.isInside(check)){
				return true;
			}
		}
		return false;
	}
	
	public List<GeologyObject> getOverlappingObjects(Vector2i check) {
		List<GeologyObject> potentialVectors = new ArrayList<>();
		for(Region reg : geoMap.keySet()) {
			if(reg.isInside(check)){
				GeologyObject temp= geoMap.get(reg);
				if(temp.isPrototype()) {
					temp = factory.makeObject((GeologyPrototype)(temp));
					geoMap.put(reg, temp);
				}
				potentialVectors.add(temp);
			}
		}
		return potentialVectors;
	}

	public List<GeologyObject> getDistantObjects(
			Vector2i center) {
		List<GeologyObject> potentialVectors = new ArrayList<>();
		for(Region reg : geoMap.keySet()) {
			if(!reg.isInside(center)){
				potentialVectors.add(geoMap.get(reg));
			}
		}
		return potentialVectors;
	}

	public List<GeologyObject> getOverlappingObjects(Vector3i query) {
		return getOverlappingObjects(new Vector2i(query.getX(),query.getZ()));
	}

	public List<GeologyObject> getDistantObjects(
			Vector3i query) {
		return getDistantObjects(new Vector2i(query.getX(),query.getZ()));
	}

}