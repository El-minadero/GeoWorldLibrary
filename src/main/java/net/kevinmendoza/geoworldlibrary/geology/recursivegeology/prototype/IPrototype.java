package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.prototype;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node.INode;

public interface IPrototype extends INode {

	double getInternalDecay(Vector2i vec);

	double getInternalDecay(Vector3i vec);

	INode convertToNode();
	
	
}
