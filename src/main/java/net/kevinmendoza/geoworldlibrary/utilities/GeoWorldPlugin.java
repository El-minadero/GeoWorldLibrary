package net.kevinmendoza.geoworldlibrary.utilities;

import java.util.List;

import net.kevinmendoza.geoworldlibrary.geology.Geology;
import ninja.leaping.configurate.ConfigurationNode;

public interface GeoWorldPlugin {
	
	public GeoWorldPlugin GetInstance();

	public Geology getGeologicMap();

}
