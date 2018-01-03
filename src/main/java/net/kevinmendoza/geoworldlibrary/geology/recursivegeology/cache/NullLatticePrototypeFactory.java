package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache;

import com.flowpowered.math.vector.Vector2i;

public class NullLatticePrototypeFactory implements ICacheFactory {
	
	private int spacing;

	public NullLatticePrototypeFactory(int spacing) {
		this.spacing = spacing;
	}
	
	@Override
	public INodeRegion makeNode(INodeRegion iNodeRegion) {
		return new NullNodeRegion(iNodeRegion);
	}

	@Override
	public INodeRegion makePrototype(Vector2i vec) {
		return new NullPrototypeRegion(vec.mul(spacing));
	}

	@Override
	public void setSeed(long seed) {
		
	}

}
