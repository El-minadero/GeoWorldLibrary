package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.PointGeneratorFactory;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.DefaultDataFactory;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.EmptyDataFactory;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.IGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISingularGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.Surface;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.IPointGenerator;

 
final class Map extends AbstractNode {

	private final MapData data;
	private IPointGenerator pointQuery;
	private MapCache regionCache;
	private AbstractPrototypeFactory factory;
	
	Map(IGeologyMapBuilder builder) {
		super();
		data = new MapData(builder);
		factory			= builder.getSubObjectFactory();
		regionCache 	= new MapCache(factory);
		pointQuery 		= PointGeneratorFactory.makePointGenerator(data.getSeed(),data.getSpacing());
	}
	@Override
	protected int additionalInfoRGBDebug(Vector3i query){
		return pointQuery.getRGBDebugVal(query);
	}
	public final void setSeed(long seed) {
		data.setSeed(seed);
		pointQuery = PointGeneratorFactory.makePointGenerator(seed,data.getSpacing());
	}

	protected final void cacheNearbyNodes(GenerationData data) {
		Vector2i center = data.get2DCoordinate();
		List<Vector2i> pointsToBuild = regionCache.getRegionCentersToBuild(
				pointQuery.getFlooredCenterNeighborhood(center));
		if(!pointsToBuild.isEmpty()) {
			for(Vector2i vec : pointsToBuild) {
				if(shouldBuildRegion(vec)) {
					regionCache.addRegion(vec, buildGeologicRegionObject(pointQuery.getFullCenter(vec)));
				}
				else {
					regionCache.addRegion(vec, null);
				}
			}
		}
		List<Vector2i> surroundingKeys  = pointQuery.getFlooredCenterNeighborhood(center);
		setInternalList(regionCache.getInternalRegions(surroundingKeys,center));
		setExternalList(regionCache.getExternalRegions(surroundingKeys,center));
	}

	public boolean shouldBuildRegion(Vector2i vec) { 
		return data.shouldBuild(vec);
	}
	private IGeologyNode buildGeologicRegionObject(Vector2i vec) {
		return factory.makePrototype(vec);
	}

	@Override
	protected ISingularGeologyData getCombined2DConditions(ISingularGeologyData testData, Vector2i query
			,HashSet<IGeologyNode> tempTotal) {
		ISingularGeologyData data = DefaultDataFactory.getEmptyDataObject(testData.getID());
		for(IGeologyNode obj : tempTotal) {
				data.merge(obj.get2DGeologyData(testData,query));
		}
		return data;
	}

	@Override
	protected ISingularGeologyData getCombined3DConditions(ISingularGeologyData testData, Vector3i query
			,HashSet<IGeologyNode> tempTotal) {
		ISingularGeologyData data = DefaultDataFactory.getEmptyDataObject(testData.getID());
		for(IGeologyNode obj : tempTotal) {
				data.merge(obj.get3DGeologyData(testData,query));
		}
		return data;
	}

	public AbstractPrototypeFactory getFactory() { return null; }
	public void setFactory(AbstractPrototypeFactory factory) {}
	
}
