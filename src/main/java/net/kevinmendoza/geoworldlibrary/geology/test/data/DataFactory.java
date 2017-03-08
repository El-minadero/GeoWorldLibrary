package net.kevinmendoza.geoworldlibrary.geology.test.data;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydatainterface.Surface;

public class DataFactory {

	public static Surface CreateSurfaceInstance(int height) {
		return new TestSurface(height);
	}

}
