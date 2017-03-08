package net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydata;

public interface GeologyData<T> {
	
	T get();

	void merge(GeologyData<T> t, double mergeWeight);
	
	int hashCode();
	
	boolean equals(Object o);
}
