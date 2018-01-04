package net.kevinmendoza.geoworldlibrary.geology.rockdata;

public class Decay implements IDecay {

	private double slope = -0.5;
	private static final double origin = 1.0;
	
	Decay(double slope) {
		this.slope*=slope;
	}
	
	public Decay() {}

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
