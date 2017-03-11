package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;

public interface GeologyMapBuilder {

	GeologyPrototype getPrototype();
	int getSpacing();
	double getFrequency();
	Order getOrder();
	long getSeed();
	AbstractPrototypeFactory getFactory();

}
