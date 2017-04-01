package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;

public interface IPrototypeBuilder {
	Region getRegion();
	Order getOrder();
	double getExternalDecayConstant();
	double getInternalDecayConstant();
	PointModifier getPointModifierMap();
}
