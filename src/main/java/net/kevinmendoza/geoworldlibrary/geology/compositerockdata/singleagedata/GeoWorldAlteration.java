package net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata;

public class GeoWorldAlteration extends AbstractAlteration {

	protected GeoWorldAlteration(IAlterationBuilder builder) {
		super(builder);
	}

	public static class Builder implements IAlterationBuilder {

		private final int ID = 2;
		private double heat;
		private double pressure;
		private double weathering;
		private double hydrothermal;
		
		public Builder setHeat(double heat) { this.heat = heat; return this; }
		public Builder setHydrothermal(double hydrothermal) { this.hydrothermal = hydrothermal; return this; }
		public Builder setPressure(double pressure) { this.pressure = pressure; return this; }
		public Builder setWeathering(double weathering) { this.weathering = weathering; return this; }
		
		public double getHeat() { return heat; }
		public double getPressure() {return pressure;}
		public double getWeathering() { return weathering; }	
		public double getHydrothermal() { return hydrothermal;}
		
		public int getID() { return ID; }
		
		public AbstractAlteration Build() {
			return new GeoWorldAlteration(this);
		}
		
	}
}
