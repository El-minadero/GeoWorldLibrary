package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.Order;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.INodeCache;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.factory.AbstractPrototypeFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.IPointGenerator;

public interface IGeologyMapBuilder extends INodeBuilder {

	IPointGenerator getPointGenerator();
	
	public Map buildMap();

}
