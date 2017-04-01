package net.kevinmendoza.geoworldlibrary.geology.rockparameters;

public interface IGeologyData {

	void applyMultiplier(double multiplier);
	
	void merge(IGeologyData data,double mergeWeight);
	void merge(IGeologyData data);
	
	boolean isEmpty();
	
	boolean isLeaf();
	
	int getID();
	
	IGeologyData getOrderData(Order order);
	
	
}
