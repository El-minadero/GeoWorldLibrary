package net.kevinmendoza.geoworldlibrary.geology.compositerockdata;

public abstract class Comparison {

	protected static double ZERO_THRESHOLD = 0.001;
	
	protected static double weightedCompare(double val1, double val2, double valuePartition) {
		if(valuePartition >= 1)
			return val2;
		else if(valuePartition <=ZERO_THRESHOLD)
			return val1;
		else {
			return val1*(1-valuePartition) + val2*valuePartition;
		}
	}
	
	protected static boolean isZero(double val) {
		return (val < ZERO_THRESHOLD);
	}
}
