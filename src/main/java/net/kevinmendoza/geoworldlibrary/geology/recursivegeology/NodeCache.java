package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Comparison;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.RegionPointGenerator;

class NodeCache extends Comparison {

	private HashSet<IGeologyNode> geoMap;
	private AbstractPrototypeFactory factory;
	
	/**
	 * 
	 * @param factory	
	 * @param subObjects number of sub objects to make
	 * @param node number of regions to make
	 */
	 NodeCache(AbstractPrototypeFactory factory, int subObjects,Node node) {
		this.factory = factory;
		geoMap = new HashSet<>();
		populateSubRegions(subObjects,node);
	}

	private void populateSubRegions(int subObjects, Node node) {
		for(int i = 0; i<subObjects;i++) {
			IGeologyNode obj = factory.makePrototype(node.getRandomInternalPoint());
			geoMap.add(obj);
		}
	}
	
	private void buildObjects(Vector2i check) {
		HashSet<IGeologyNode> removalSet = new HashSet<>();
		for(IGeologyNode reg : geoMap) 
			if(reg.isVectorInRegion(check) && reg.isLeaf())
				removalSet.add(reg);
		geoMap.removeAll(removalSet);
		for(IGeologyNode reg : removalSet) {
			IGeologyNode node = factory.makeObject((AbstractPrototype) reg);
			geoMap.add(node);
		}
	}
	
	 HashSet<IGeologyNode> getOverlappingObjects(Vector2i check) {
		buildObjects(check);
		HashSet<IGeologyNode> returnSet = new HashSet<>();
		for(IGeologyNode reg : geoMap) {
			if(reg.isVectorInRegion(check)){
				returnSet.add(reg);
			}
		}
		return returnSet;
	}

	 HashSet<IGeologyNode> getDistantObjects(
			Vector2i center) {
		 HashSet<IGeologyNode> returnSet = new HashSet<>();
		for(IGeologyNode reg : geoMap) {
			if(!reg.isVectorInRegion(center)){
				if(!isZero(reg.getExternalDecay(center)))
					returnSet.add(reg);
			}
		}
		return returnSet;
	}

	 HashSet<IGeologyNode> getOverlappingObjects(Vector3i query) {
		return getOverlappingObjects(new Vector2i(query.getX(),query.getZ()));
	}

	 HashSet<IGeologyNode> getDistantObjects(
			Vector3i query) {
		return getDistantObjects(new Vector2i(query.getX(),query.getZ()));
	}

	public void buildAll() {
		HashSet<IGeologyNode> removalSet = new HashSet<>();
		for(IGeologyNode reg : geoMap) 
			removalSet.add(reg);
		geoMap.removeAll(removalSet);
		for(IGeologyNode reg : removalSet) {
			IGeologyNode node = factory.makeObject((AbstractPrototype) reg);
			geoMap.add(node);
		}
	}
	
	

}
