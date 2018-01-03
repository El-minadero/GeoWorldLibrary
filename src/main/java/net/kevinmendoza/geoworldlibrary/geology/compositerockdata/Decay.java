package net.kevinmendoza.geoworldlibrary.geology.compositerockdata;

public class Decay implements IDecay {

	private final double slope;
	private static final double origin = 1.0;
	
	Decay() {
		slope = 1;
	}
	
	Decay(double slope) {
		this.slope = slope;
	}
	
	public double getModifier(double distance) {
		double val = calculateSlope(distance);
		if(val > 1) {
			return 1.0;
		}
		else if(val < 0) {
			return 0.0;
		}
		return val;
	}
	
	private double calculateSlope(double x) {
		return slope*x + origin;
	}

}
