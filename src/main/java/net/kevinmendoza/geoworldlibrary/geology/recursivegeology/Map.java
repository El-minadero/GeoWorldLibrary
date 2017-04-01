package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.DataFactory;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.IGeologyData;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.PointGeneratorFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.PointGeneratorInterface;

 
final class Map extends AbstractNode {

	private long SEED;
	private final double SPACING;
	private final double FREQUENCY;
	private boolean debugMode;
	private PointGeneratorInterface pointQuery;
	private MapCache regionCache;
	private AbstractPrototypeFactory factory;
	private AbstractPrototype prototype;
	
	Map(IGeologyMapBuilder builder) {
		super(builder.getPrototype());
		prototype 		= builder.getPrototype();
		factory			= builder.getFactory();
		SPACING   		= builder.getSpacing();
		FREQUENCY       = builder.getFrequency();
		SEED	  		= builder.getSeed();
		debugMode 		= builder.debugMode();
		regionCache 	= new MapCache(factory,debugMode);
		pointQuery 		= PointGeneratorFactory.makePointGenerator(SEED,(int)SPACING);
	}
	public final void setSeed(long seed) {
		this.SEED = seed;
		pointQuery = PointGeneratorFactory.makePointGenerator(seed,(int)SPACING);
	}

	protected final void prime(GenerationData data) {
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
		Random rand = new Random(vec.hashCode());
		rand.nextDouble();
		return (rand.nextDouble()<=FREQUENCY);
	}
	private IGeologyNode buildGeologicRegionObject(Vector2i vec) {
		return factory.makePrototype(vec);
	}

	@Override
	protected IGeologyData getCombined2DConditions(IGeologyData testData, Vector2i query
			,HashSet<IGeologyNode> tempTotal) {
		IGeologyData dataNode = DataFactory.GetGeologyDataNode(testData.getID());
		dataNode.merge(getPrototype2DData(testData, query));
		mergeData(testData,dataNode,query,tempTotal);
		return dataNode;
	}

	@Override
	protected IGeologyData getCombined3DConditions(IGeologyData testData, Vector3i query
			,HashSet<IGeologyNode> tempTotal) {
		IGeologyData dataNode = DataFactory.GetGeologyDataNode(testData.getID());
		dataNode.merge(getPrototype3DData(testData, query));
		mergeData(testData,dataNode,query,tempTotal);
		return dataNode;
	}
	@Override
	public void debug() {

	}
	
}
