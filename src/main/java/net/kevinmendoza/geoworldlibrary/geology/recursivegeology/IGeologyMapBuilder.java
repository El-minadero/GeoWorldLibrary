package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.Order;

public interface IGeologyMapBuilder {

	int getSpacing();
	Order getOrder();
	long getSeed();
	AbstractPrototypeFactory getSubObjectFactory();
	double getFrequency();

}
