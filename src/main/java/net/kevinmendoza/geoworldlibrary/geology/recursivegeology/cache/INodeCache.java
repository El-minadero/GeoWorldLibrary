package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache;

import java.util.List;
import java.util.Set;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public interface INodeCache {
	
	void loadNodes(Vector2i vec);
	void loadNodes(Vector3i vec);

	Set<INodeRegion> getNodes();

}
