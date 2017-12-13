package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.factory.INodeFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.IPointGenerator;

public interface ICacheBuilder {

	IPointGenerator getPointGenerator();

	INodeFactory getFactory();

}
