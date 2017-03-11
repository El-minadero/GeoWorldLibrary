package net.kevinmendoza.geoworldlibrary.geology.test.data;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydata.GeologyData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydatainterface.Surface;

public class DataFactory {
	
	private static Surface emptySurface;

	public static Surface CreateSurfaceInstance(int height) {
		return new TestSurface(height);
	}

	public static Surface GetEmptySurfaceInstance() {
		if(emptySurface==null) {
			emptySurface = new EmptySurface();
		}
		return emptySurface;
	}
	
	private static class EmptySurface extends Surface{

		@Override
		public Surface get() {
			return null;
		}

		@Override
		public void merge(GeologyData<Surface> t, double mergeWeight) { }

		@Override
		public int getHeight() { return 0; }
		
	}
}
