package net.kevinmendoza.geoworldlibrary.geology.rockparameters.other;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;

public interface GeologyData<T> {
	
	void merge(GeologyData<T> object);
	
	GeologyData<T> getValue(Order order);
	
	T getObject(Order order);

}
