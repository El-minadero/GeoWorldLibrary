package net.kevinmendoza.geoworldlibrary.utilities;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;

public interface IGeologyFactory {

	IGeology getIGeology(long seed,boolean newInstance);
}

