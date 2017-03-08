package net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydata;

import java.util.HashMap;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;

class ImplementedGeologyDataContainer<T extends GeologyData<T>> implements GeologyDataContainer<T>{

	private HashMap<Order,GeologyData<T>> map;
	
	ImplementedGeologyDataContainer(Order order, T t){
		map = new HashMap<>();
		map.put(order,t);
	}
	
	public boolean contains(Order order) {
		return map.containsKey(order);
	}
	
	public void merge(GeologyDataContainer<T> object, double mergeWeight) {
		for(Order order : Order.values()) {
			if(object.contains(order)) {
				if(map.containsKey(order)) {
					map.get(order).merge(object.getValue(order),mergeWeight);
				}
				else
					map.put(order, object.getValue(order));
			}
		}
	}

	public GeologyData<T> getValue(Order order) {
		if(map.containsKey(order))
			return map.get(order);
		else
			return null;
	}

	public T getObject(Order order) {
		if(map.containsKey(order))
			return map.get(order).get();
		else
			return null;
	}

	

}
