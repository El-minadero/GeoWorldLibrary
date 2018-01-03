package net.kevinmendoza.geoworldlibrary.geology.compositerockdata;

public class DecayFactory {

	public static IDecay getDefaultDecay() {
		return new Decay();
	}
	
	public static IDecay getDecay(double slope) {
		return new Decay(slope);
	}
}
