package net.kevinmendoza.geoworldlibrary.geology.compositerockdata;

import java.util.HashMap;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISingularGeologyData;

class DataNode implements IGeologyData {

	HashMap<Order,ISingularGeologyData> surface;
	HashMap<Order,ISingularGeologyData> rock;
	HashMap<Order,ISingularGeologyData> alteration;
	
	DataNode(){
		surface = new HashMap<>();
		rock = new HashMap<>();
		alteration = new HashMap<>();
		for(Order order: Order.values()) {
			surface.put(order, EmptyDataFactory.getEmptyDataObject(1));
			rock.put(order,  EmptyDataFactory.getEmptyDataObject(3));
			alteration.put(order,  EmptyDataFactory.getEmptyDataObject(2));
		}
	}

}
