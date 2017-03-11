package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.ArrayList;
import java.util.List;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.PointGeneratorFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.PointGeneratorInterface;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;

 
final class GeologyMap extends AbstractGeologyNode {

	private long SEED;
	private final double SPACING;

	private PointGeneratorInterface pointQuery;
	private GeologyMapCache regionCache;
	private AbstractPrototypeFactory factory;
	
	GeologyMap(GeologyMapBuilder builder) {
		super(builder.getPrototype());
		factory			= builder.getFactory();
		SPACING   		= builder.getSpacing();
		SEED	  		= builder.getSeed();
		regionCache 	= new GeologyMapCache(factory);
		pointQuery 		= PointGeneratorFactory.makePointGenerator(SEED,(int)SPACING);
	}
	
	public final void setSeed(long seed) {
		this.SEED = seed;
		pointQuery = PointGeneratorFactory.makePointGenerator(seed,(int)SPACING);
	}

	public final void primeGeneration(GenerationData data) {
		clearSubObjectList();
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
		primeGenerationList(data);
	}

	private boolean shouldBuildRegion(Vector2i vec) { return canBuildRegion(vec); }

	private GeologyComposite buildGeologicRegionObject(Vector2i vec) {
		Region region = factory.makeRegion(vec);
		return factory.makePrototype(region);
	}
}
