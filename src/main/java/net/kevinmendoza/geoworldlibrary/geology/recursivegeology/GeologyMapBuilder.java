package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologicalFactory.GeologyFactory;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;

public interface GeologyMapBuilder {
	public static interface MapBuilder {

		GeologyPrototype getPrototype();
		int getSpacing();
		double getFrequency();
		Order getOrder();
		long getSeed();
		GeologyFactory getFactory();
	}
}
