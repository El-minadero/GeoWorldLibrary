package net.kevinmendoza.geoworldlibrary.geology.regionmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.geology.GeologicContainer;
import net.kevinmendoza.geoworldlibrary.geology.LithogenicOrder;
import net.kevinmendoza.geoworldlibrary.geology.StratigraphicColumn;
import net.kevinmendoza.geoworldlibrary.geology.geologicobject.GeologicObjectInterface;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.PointGeneratorFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.PointGeneratorInterface;


public abstract class GeologicRegionMap implements GeologicRegionMapInterface {
	
	private final double spacing;
	private final LithogenicOrder order;
	private PointGeneratorInterface pointQuery;
	private RegionMapCache<GeologicObjectInterface> regionCache;
	private long seed;
	
	public GeologicRegionMap(GeologicRegionMapBuilderGetInterface builder) {
		this.spacing   	= builder.getSpacing();
		this.order 	  	= builder.getOrder();
		seed 	  		= builder.getSeed();
		regionCache 	= new RegionMapCache<>();
	    pointQuery 		= PointGeneratorFactory.makePointGenerator(seed,(int)spacing);
	}
	
	@Override
	public void primeGenerationAt(Vector2i min) {
		Vector2i center = new Vector2i(min);
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
	}
	
	protected abstract GeologicObjectInterface buildGeologicRegionObject(Vector2i fullCenter);

	private boolean shouldBuildRegion(Vector2i center) {
		return true;
	}
	
	@Override
	public void setSeed(long seed) {
		this.seed  = seed;
		pointQuery = PointGeneratorFactory.makePointGenerator(seed,(int)spacing);
	}
	
	@Override
	public LithogenicOrder getOrder() {
		return order;
	}

	@Override
	public StratigraphicColumn getColumnAt(Vector2i center) {
		List<Vector2i> surroundingKeys = pointQuery.getFlooredCenterNeighborhood(center);
		List<GeologicObjectInterface> geologyRegions = regionCache.getNeighboringRegions(surroundingKeys);
		List<GeologicObjectInterface> relevantRegions = new ArrayList<>();
		for(GeologicObjectInterface geologyRegion : geologyRegions) {
			if(geologyRegion.isInSuperRegion(center)){
				relevantRegions.add(geologyRegion);
			}
		}
		return buildStratigraphicColumn(relevantRegions,center);
	}
	
	protected abstract StratigraphicColumn buildStratigraphicColumn(List<GeologicObjectInterface> relevantRegions,
																	Vector2i center);

}
