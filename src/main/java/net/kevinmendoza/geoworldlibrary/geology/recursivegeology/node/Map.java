package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;

import java.util.HashSet;
import java.util.List;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.DefaultDataFactory;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISingularGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.INodeCache;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.IPointGenerator;

 
final class Map extends AbstractNode {

	private INodeCache regionCache;
	
	Map(IGeologyMapBuilder builder) {
		super(builder);
		regionCache 		= builder.getCache();
	}
	@Override
	public String getLocationData(Vector3i globalVector) {
		return regionCache.getLocationString(globalVector);
	}

	public final void setSeed(long seed) {
		regionCache.setSeed(seed);
	}

	protected final void cacheNearbyNodes(GenerationData data) {
		Vector2i center = data.get2DCoordinate();
		List<Vector2i> neighborhoodKeyList = regionCache.getLocalKeys(center);
		List<Vector2i> pointsToBuild = regionCache.getMissingNeighborKeys(neighborhoodKeyList);
		if(!pointsToBuild.isEmpty()) {
			for(Vector2i vec : pointsToBuild) {
				regionCache.populateKeyValue(vec);
			}
		}
	}
	
	void partitionChildrenFromCache(GenerationData data) {
		Vector2i center = data.get2DCoordinate();
		List<Vector2i> surroundingKeys  = regionCache.getLocalKeys(center);
		setInternalList(regionCache.getInternalRegionsFromKeyList(surroundingKeys,center));
		setExternalList(regionCache.getExternalRegionsFromKeyList(surroundingKeys,center));
	}

}
