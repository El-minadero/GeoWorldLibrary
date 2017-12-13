package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;

import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.GenerationData;

public class NullAbstractNode extends AbstractNode {

	NullAbstractNode(INodeBuilder nodeBuilder) {
		super(nodeBuilder);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getLocationData(Vector3i globalVector) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void cacheNearbyNodes(GenerationData metaData) {
		// TODO Auto-generated method stub

	}

}
