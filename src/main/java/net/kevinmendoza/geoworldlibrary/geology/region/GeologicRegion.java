package net.kevinmendoza.geoworldlibrary.geology.region;
import java.util.List;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.geology.geologicobject.GeologicObject;
import net.kevinmendoza.geoworldlibrary.geology.geologicobject.GeologicObjectInterface;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;


public abstract class GeologicRegion extends GeologicObject implements GeologicObjectInterface {

	private SubRegionMapCache mapCache;
	private final int subRegions;
	
	public GeologicRegion(GeologicRegionBuilderGetInterface builder) {
		super(builder);
		subRegions = builder.getSubRegionNumber();
		mapCache = new SubRegionMapCache();
		populateSubRegions();
	}
	
	protected abstract GeologicObjectInterface makeSubObject(Region region);
	
	protected abstract Region makeNewSubRegion(Vector2i center);
	
	protected Region getSuperRegion() {
		return super.getRegion();
	}

	private void populateSubRegions() {
		for(int i = 0;i<subRegions;i++) {
			Vector2i center = super.getRegion().getRandomInternalPoint();
			Region newRegion = makeNewSubRegion(center);
			mapCache.addRegion(newRegion);
		}
	}
	
	public boolean isInSuperRegion(Vector2i center) {
		boolean b = super.isInSuperRegion(center);
		/*if(b) {
			if(mapCache.isInRegions(center))
				return true;
			else
				return false;
		}*/
		return b;
	}

	protected List<GeologicObjectInterface> getOverlappingObjects(Vector2i center) {
		List<Region> regions = mapCache.getUninitializedRegionKeys(center);
		for(Region region : regions)
			mapCache.putObjectInRegion(makeSubObject(region));
		return mapCache.getOverlappingObjects(center);	
	}
}
