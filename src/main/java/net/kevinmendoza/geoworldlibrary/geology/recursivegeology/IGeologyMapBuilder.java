package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;

public interface IGeologyMapBuilder {

	AbstractPrototype getPrototype();
	int getSpacing();
	Order getOrder();
	long getSeed();
	AbstractPrototypeFactory getFactory();
	boolean debugMode();
	double getFrequency();

}
