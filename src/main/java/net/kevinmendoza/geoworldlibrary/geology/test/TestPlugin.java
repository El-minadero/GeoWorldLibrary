package net.kevinmendoza.geoworldlibrary.geology.test;

import net.kevinmendoza.geoworldlibrary.geology.regionmap.GeologicMap;
import net.kevinmendoza.geoworldlibrary.utilities.GeoWorldPlugin;

public class TestPlugin implements GeoWorldPlugin {

	public TestPlugin() {
		
	}
	
	@Override
	public GeoWorldPlugin GetInstance() {
		return this;
	}

	@Override
	public GeologicMap getGeologicMap() {
		return null;
	}

}
