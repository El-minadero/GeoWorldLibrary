package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache;

import com.flowpowered.math.vector.Vector2i;

public class NullPrototypeFactory implements ICacheFactory {

	@Override
	public INodeRegion makeNode(INodeRegion iNodeRegion) {
		return new NullNodeRegion(iNodeRegion);
	}

	@Override
	public INodeRegion makePrototype(Vector2i vec) {
		return new NullPrototypeRegion(vec);
	}

	@Override
	public void setSeed(long seed) {
		
	}

}
