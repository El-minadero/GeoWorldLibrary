package net.kevinmendoza.geoworldlibrary.geology.rockdata;

public interface IData {
	
	/**
	 * Applies a numeric modifier to reduce activity and metal modifiers within the data
	 * @param modifier a double value. values above 1 increase values, values below 1 decrease values. 
	 */
	void modifyData(double modifier);

	/**
	 *  Merges two IData objects into one. The input IData BulkComposition (if present) and Texture (if present) 
	 *  override the Object being operated upon.
	 *  
	 * @param data an IData Object
	 * @return IData data, an IData object
	 */
	IData merge(IData data);
	
	/**
	 *  gets weight of Data object
	 * 
	 * @return weight a weight value between 0 and 1
	 */
	double getWeight();

	IData copy();
	
	boolean equals(Object other);

}
