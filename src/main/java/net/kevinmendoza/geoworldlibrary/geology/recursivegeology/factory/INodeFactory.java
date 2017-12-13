package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.factory;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node.INode;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.prototype.IPrototype;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.IRegion;

public interface INodeFactory {

	INode makePrototype(Vector2i fullCenter);

	INode makeNode(IPrototype prototype);

	void setSeed(long seed);

	INode makePrototype(IRegion sourceRegion);

}
