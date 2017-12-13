package net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.GeoWorldAlteration.Builder;

public class GeoWorldTestData extends AbstractDataTest {

	private GeoWorldTestData(IGeologyDataBuilder builder) {
		super(builder);
	}

	public static class Builder implements IGeologyDataBuilder {

		private final int ID = 0;
		private double v;
		
		public Builder setValue(double v) { this.v=v; return this; }
		
		public double getValue() { return v; }
		
		public int getID() { return ID; }
		
		public AbstractDataTest build() {
			return new GeoWorldTestData(this);
		}
		
	}
}
