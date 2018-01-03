package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache;

import com.flowpowered.math.vector.Vector2i;

public interface ICacheFactory {
 
		INodeRegion makeNode(INodeRegion iNodeRegion);

		INodeRegion makePrototype(Vector2i vec);

		void setSeed(long seed);
}
