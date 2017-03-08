package net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydata;

import java.util.HashMap;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;

class EmptyGeologyData implements GeologyData<Object>{

	EmptyGeologyData() {
	}
	
	@Override
	public Object get() { return this; }

	public void merge(GeologyData<Object> t, double mergeWeight) { }

	public boolean isEmpty() { return true; }
}
