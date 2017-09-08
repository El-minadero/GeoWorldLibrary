package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.Comparison;

class NodeCache extends Comparison {

	private HashSet<IGeologyNode> geoMap;
	private Node node;
	
	/**
	 * 
	 * @param factory	
	 * @param subObjects number of sub objects to make
	 * @param node number of regions to make
	 */
	 NodeCache(INodeBuilder nodeBuilder,Node node) {
		this.node = node;
		this.geoMap = new HashSet<>();
		populateSubRegions(nodeBuilder);
	}


	 private void populateSubRegions(INodeBuilder builder) {
		 HashMap<Integer,AbstractPrototypeFactory> factoryMap = builder.getFactories();
		 for(Integer subNumber : factoryMap.keySet()) {
			 AbstractPrototypeFactory factory = factoryMap.get(subNumber);
			 for(int i = 0; i<subNumber;i++) {
				 IGeologyNode obj = factory.makePrototype(node.getRandomInternalPoint());
				 geoMap.add(obj);
			 }
		 }
	}
	
	private void buildObjects(Vector2i check) {
		HashSet<IGeologyNode> removalSet = new HashSet<>();
		for(IGeologyNode reg : geoMap) 
			if(reg.isVectorInRegion(check) && reg.isLeaf())
				removalSet.add(reg);
		geoMap.removeAll(removalSet);
		for(IGeologyNode reg : removalSet) {
			IGeologyNode node = reg.getFactory().makeObject((AbstractPrototype) reg);
			geoMap.add(node);
		}
	}
	
	 private HashSet<IGeologyNode> getOverlapObjects(Vector2i check) {
		buildObjects(check);
		HashSet<IGeologyNode> returnSet = new HashSet<>();
		for(IGeologyNode reg : geoMap) {
			if(reg.isVectorInRegion(check)){
				returnSet.add(reg);
			}
		}
		return returnSet;
	}

	 private HashSet<IGeologyNode> getDistObjects(Vector2i center) {
		HashSet<IGeologyNode> returnSet = new HashSet<>();
		for(IGeologyNode reg : geoMap) {
			if(!reg.isVectorInRegion(center)){
				if(!isZero(reg.getExternalDecay(center)))
					returnSet.add(reg);
			}
		}
		return returnSet;
	}

	 HashSet<IGeologyNode> getOverlappingObjects(Vector2i vec) {
		return getOverlapObjects(vec);
	 }

	 HashSet<IGeologyNode> getDistantObjects(Vector2i vec) {
		return getDistObjects(vec);
	 }

	 HashSet<IGeologyNode> getOverlappingObjects(Vector3i vec) {
		return getOverlapObjects(new Vector2i(vec.getX(),vec.getZ()));
	}

	 HashSet<IGeologyNode> getDistantObjects(Vector3i vec) {
		return getDistObjects(new Vector2i(vec.getX(),vec.getZ()));
	}

	public void buildAll() {
		HashSet<IGeologyNode> removalSet = new HashSet<>();
		for(IGeologyNode reg : geoMap) 
			removalSet.add(reg);
		geoMap.removeAll(removalSet);
		for(IGeologyNode reg : removalSet) {
			IGeologyNode node = reg.getFactory().makeObject((AbstractPrototype) reg);
			geoMap.add(node);
		}
	}
	
	

}
