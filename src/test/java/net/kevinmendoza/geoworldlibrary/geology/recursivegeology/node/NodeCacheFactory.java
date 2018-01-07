package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.ICacheFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.INodeRegion;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.IRegion;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.shape.ShapeFactory;

public class NodeCacheFactory implements ICacheFactory {

	@Override
	public INodeRegion makeNode(INodeRegion iNodeRegion) {
		return new NodeBuilder().setPrototype((INode)iNodeRegion).build();
	}

	@Override
	public INodeRegion makePrototype(Vector2i vec) {
		IRegion region = ShapeFactory.makeEllipse(5, 5, vec);
		return new PrototypeBuilder().setRegion(region).build();
	}

	@Override
	public void setSeed(long seed) {
	}

}
