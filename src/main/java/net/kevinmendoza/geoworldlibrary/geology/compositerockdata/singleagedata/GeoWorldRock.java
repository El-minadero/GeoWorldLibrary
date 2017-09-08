package net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata;

public class GeoWorldRock extends AbstractRock {

	protected GeoWorldRock(IRockBuilder builder) {
		super(builder);
	}
	
	public static class Builder implements IRockBuilder {
		private String rockName;
		private double cutoff;
		private int ID = 3;
		
		public Builder setName(String rockName) { this.rockName = rockName; return this; }
		public Builder setCutoff(double cutoff) { this.cutoff = cutoff; 	return this; }
		
		public int getID() { return ID; }
		public String getRock() { return rockName; }
		public double getMultiplierCutoff() { return cutoff; }
		
		public AbstractRock build() { return new GeoWorldRock(this); }	
	}

}
