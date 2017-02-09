package net.kevinmendoza.geoworldlibrary.utilities;

import java.util.List;

import net.kevinmendoza.geoworldlibrary.geology.GeologicContainer;
import ninja.leaping.configurate.ConfigurationNode;

public interface GeoWorldPluginInterface {
	
	public GeoWorldPluginInterface GetInstance();
	
	public List<GeologicContainer> getGeologicContainers();
}
