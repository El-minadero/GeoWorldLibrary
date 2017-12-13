package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache;

import java.util.List;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node.INode;

public class CacheTestClass extends Cache implements INodeCache {

	@Override
	public List<Vector2i> getLocalKeys(Vector3i globalVec) { return null; }

	@Override
	public INode populateKeyValue(Vector2i flooredKey) { return null; }

	@Override
	public String getLocationString(Vector3i globalVector) { return null; }

	@Override
	public List<INode> getClosestNodesToLocation(Vector3i globalVec) { return null; }

	@Override
	public void populateIfNecessary() { }

	@Override
	public void setSeed(long seed) { }

	@Override
	public List<Vector2i> getLocalKeys(Vector2i k) { return null; }

	

}
