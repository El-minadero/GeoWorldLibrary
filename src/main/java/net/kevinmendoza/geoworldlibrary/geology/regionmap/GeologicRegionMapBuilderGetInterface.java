package net.kevinmendoza.geoworldlibrary.geology.regionmap;

import net.kevinmendoza.geoworldlibrary.geology.LithogenicOrder;

public interface GeologicRegionMapBuilderGetInterface {

	int getSpacing();
	double getFrequency();
	LithogenicOrder getOrder();
	long getSeed();
}
