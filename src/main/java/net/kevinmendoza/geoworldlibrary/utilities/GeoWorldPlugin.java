package net.kevinmendoza.geoworldlibrary.utilities;

import java.util.List;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import ninja.leaping.configurate.ConfigurationNode;

public interface GeoWorldPlugin {
	
	public IGeology getGeology(long seed);

}
