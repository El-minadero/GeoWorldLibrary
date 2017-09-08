package net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata;

public interface ISingularGeologyData {

	/**
	 * Intended to increase or decrease current data value by a multiplied amount
	 */
	void applyMultiplier(double multiplier);
	
	/**
	 * Used when averaging two data values. MergeWeight should be between 0-1, and indicates the percentage of the value 
	 * to set the incoming data to. Data is averaged by:  data*mergeWeight + prevData*(1-mergeWeight)
	 */
	void merge(ISingularGeologyData data,double mergeWeight);
	
	/**
	 * Used to completely replace original data. Users may want to implement special conditions for data override.
	 */
	void merge(ISingularGeologyData data);
	
	/**
	 * Gets the data ID of this geology data object. 
	 * 1 == surface;
	 * 2 == alteration;
	 * 3 == rock;
	 * @return the data ID;
	 */
	int getID();
}
