package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.ICacheFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.INodeRegion;

public class NullPrototypeCacheFactory implements ICacheFactory {

	@Override
	public INodeRegion makeNode(INodeRegion iNodeRegion) {
		return new NodeBuilder().setPrototype((INode)iNodeRegion).build();
	}

	@Override
	public INodeRegion makePrototype(Vector2i vec) {
		return new PrototypeBuilder().build();
	}

	@Override
	public void setSeed(long seed) {
		// TODO Auto-generated method stub

	}

}
