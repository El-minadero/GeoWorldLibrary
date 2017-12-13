package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.factory.INodeFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node.INode;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.prototype.IPrototype;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.IPointGenerator;

final class MapCache extends Cache {

	private final IPointGenerator pointGenerator;
	private INodeFactory nodeFactory;
	
	public MapCache(ICacheBuilder builder) {
		pointGenerator	= builder.getPointGenerator();
		nodeFactory		= builder.getFactory();
	}
	public List<Vector2i> getLocalKeys(Vector3i globalVec) {
		Vector2i globalVec2i = new Vector2i(globalVec.getX(),globalVec.getZ());
		return pointGenerator.getFlooredCenterNeighborhood(globalVec2i);
	}
	@Override
	public List<Vector2i> getLocalKeys(Vector2i k) {
		List<Vector2i> vecList = pointGenerator.getFlooredCenterNeighborhood(k);
		return vecList;
	}
	@Override
	public INode populateKeyValue(Vector2i vec) {
		Vector2i fullCenter = pointGenerator.getFullCenter(vec);
		INode node 	= nodeFactory.makePrototype(fullCenter);
		addKeyValuePair(vec, node);
		return node;
	}

	@Override
	public String getLocationString(Vector3i globalVector) {
		List<INode> closestNodes = getClosestNodesToLocation(globalVector);
		String s = "";
		for(INode node : closestNodes) {
			s=s + node.getLocationData(globalVector);
		}
		return s;
	}
	
	public List<INode> getClosestNodesToLocation(Vector3i globalVec) {
		Vector2i vec = new Vector2i(globalVec.getX(),globalVec.getZ());
		List<INode> internals = super.getInternalRegions(vec);
		List<INode> externals = super.getExternalRegions(vec);
		List<INode> closestNodes = keepClosestNodes(3,internals,externals,globalVec);
		return closestNodes;
	}

	private List<INode> keepClosestNodes(int i,
			List<INode> internals, List<INode> externals, Vector3i vec) {
		List<INode> closestNodes = new ArrayList<>();
		
		if(internals.size()<i) { 
			int remainder = i - internals.size();
			closestNodes.addAll(internals);
			closestNodes.addAll(sortAndReturnClosestNNodes(remainder, externals,vec));
		}
		else {
			closestNodes.addAll(sortAndReturnClosestNNodes(i, internals,vec));
		}
	
		return closestNodes;
	}
	
	private List<INode> sortAndReturnClosestNNodes(int i, List<INode> nodesToSort,Vector3i vec){
		List<INode> closestNodes = new ArrayList<>();
		nodesToSort.sort( (INode n1, INode n2) 
				-> (Double.compare(n1.getCenterDistance(vec), n2.getCenterDistance(vec))));
		LinkedList<INode> sortedNodes = new LinkedList<>(nodesToSort);
		while(closestNodes.size()<i && !sortedNodes.isEmpty()) { closestNodes.add(sortedNodes.pop()); }
		return closestNodes;
	}

	@Override
	public final void populateIfNecessary() { }
	@Override
	public void setSeed(long seed) {
		nodeFactory.setSeed(seed);
		pointGenerator.setSeed(seed);
	}

}
