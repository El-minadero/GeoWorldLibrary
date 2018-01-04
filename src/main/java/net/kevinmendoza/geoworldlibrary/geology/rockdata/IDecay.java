package net.kevinmendoza.geoworldlibrary.geology.rockdata;

public interface IDecay {

	
	/**
	 * get a number between 0 - 1 representing the 'repressing' power of distance.
	 * Assume the parameter 'distance' is always  > 0 and usually less than 1, but not always.
	 * @param distance
	 * @return
	 */
	double getModifier(double distance);

}
