package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.ArrayList;
import java.util.List;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.Geology;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologicalFactory.GeologyFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologyMapBuilder.MapBuilder;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.Alteration;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.Replacement;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.Surface;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.other.GeologyData;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.PointGeneratorFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.PointGeneratorInterface;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;

 
final class GeologyMap implements Geology {

	private long SEED;
	private final double SPACING;

	private PointGeneratorInterface pointQuery;
	private RegionMapCache regionCache;
	private GeologyFactory factory;
	private GeologyPrototype prototype;
	private Order order;

	private List<GeologyObject> internal;
	private List<GeologyObject> external;

	private int HEIGHT;

	GeologyMap(MapBuilder builder) {
		internal 		= new ArrayList<>();
		external 		= new ArrayList<>();
		prototype 		= builder.getPrototype();
		order 			= builder.getOrder();
		factory			= builder.getFactory();
		SPACING   		= builder.getSpacing();
		SEED	  		= builder.getSeed();
		regionCache 	= new RegionMapCache(factory);
		pointQuery 		= PointGeneratorFactory.makePointGenerator(SEED,(int)SPACING);
	}

	public final void primeGeneration(GenerationData data) {
		internal.clear();
		external.clear();
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
		internal = regionCache.getInternalRegions(surroundingKeys,center);
		external = regionCache.getExternalRegions(surroundingKeys,center);
		if(!internal.isEmpty() )
			for(GeologyObject obj : external)
				obj.primeGeneration(data);
		else if(!external.isEmpty())
			for(GeologyObject obj : internal)
				obj.primeGeneration(data);
	}

	protected boolean shouldBuildRegion(Vector2i vec) { return prototype.shouldBuildRegion(vec); }

	public final Order getOrder() { return order; }
	public final void setSeed(long seed) {
		this.SEED = seed;
		pointQuery = PointGeneratorFactory.makePointGenerator(seed,(int)SPACING);
	}

	public final GeologyData<Surface> getSurface(Vector2i query) {
		if(internal.isEmpty() && external.isEmpty())
			return prototype.getSurface(query);
		List<GeologyObject> objs = getRelevantObjects();
		return getSurfaceConditions(query,objs,internal.isEmpty());
	}

	public final GeologyData<Alteration> getAlteration(Vector3i query) {
		if(internal.isEmpty() && external.isEmpty())
			return prototype.getAlteration(query);
		List<GeologyObject> objs = getRelevantObjects();
		return getAlterationConditions(query,objs,internal.isEmpty());
	}

	public final GeologyData<Replacement> getReplacement(Vector3i query) {
		if(internal.isEmpty() && external.isEmpty())
			return prototype.getReplacement(query);
		List<GeologyObject> objs = getRelevantObjects();
		return getReplacementConditions(query,objs,internal.isEmpty());
	}

	private GeologyData<Surface> getSurfaceConditions(Vector2i location,List<GeologyObject> objList, boolean b) {
		GeologyData<Surface> surf =null ;
		if(b) {
			surf = prototype.getSurface(location);
			for(GeologyObject obj : objList)
				surf.merge(obj.getSurface(location));
		}
		else {
			for(GeologyObject obj : objList) {
				if(surf==null)
					surf = obj.getSurface(location);
				else
					surf.merge(obj.getSurface(location));
			}
		}
		return surf;
	}

	private GeologyData<Alteration> getAlterationConditions(Vector3i query,List<GeologyObject> objList, boolean b) {
		GeologyData<Alteration> alt=null ;
		if(b) {
			alt = prototype.getAlteration(query);
			for(GeologyObject obj : objList)
				alt.merge(obj.getAlteration(query));
		}
		else {
			for(GeologyObject obj : objList) {
				if(alt==null)
					alt = obj.getAlteration(query);
				else
					alt.merge(obj.getAlteration(query));
			}
		}
		return alt;
	}

	private GeologyData<Replacement> getReplacementConditions(Vector3i query,List<GeologyObject> objList,boolean b) {
		GeologyData<Replacement> rep=null ;
		if(b) {
			rep = prototype.getReplacement(query);
			for(GeologyObject obj : objList)
				rep.merge(obj.getReplacement(query));
		}
		else {
			for(GeologyObject obj : objList) {
				if(rep==null)
					rep = obj.getReplacement(query);
				else
					rep.merge(obj.getReplacement(query));
			}
		}
		return rep;
	}
	private List<GeologyObject> getRelevantObjects() {
		if(internal.isEmpty())
			return external;
		return internal;
	}

	private GeologyObject buildGeologicRegionObject(Vector2i vec) {
		Region region = factory.makeRegion(vec);
		return factory.makePrototype(region);
	}


}
