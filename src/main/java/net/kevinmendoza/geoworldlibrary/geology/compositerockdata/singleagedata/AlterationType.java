package net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata;

public enum AlterationType {
	HEAT,PRESSURE,HYDROTHERMAL,
	WEATHERING {
		@Override
		public boolean isCumulative() {
			return true;
		}
	};

	public boolean isCumulative() { return false; }
}
