package net.kevinmendoza.geoworldlibrary.utilities;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;

public interface IGeoWorldPlugin {

	public IGeology getGeology(long seed,boolean newInstance);
	
	public String GetPluginID();
}
