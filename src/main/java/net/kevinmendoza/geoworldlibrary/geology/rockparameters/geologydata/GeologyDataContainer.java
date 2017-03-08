package net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydata;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;

public interface GeologyDataContainer<T> {
	
	boolean contains(Order order);
	
	void merge(GeologyDataContainer<T> object,double mergeWeight);
	
	GeologyData<T> getValue(Order order);
	
	T getObject(Order order);

}
