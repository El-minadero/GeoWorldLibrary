package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.ArrayList;
import java.util.List;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.Geology;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologicalFactory.GeologyFactory;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GenerationData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.Alteration;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.Replacement;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.Surface;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.other.GeologyData;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;

public interface GeologyObject extends Geology {

	public boolean isVectorInRegion(Vector2i center);
	public boolean isVectorInRegion(Vector3i query);
	public Region getSuperRegion();
	public boolean isPrototype();
	
	public static class GeologyObjectBuilder {
		private GeologyPrototype prototype;
		private int number;
		private GeologyFactory factory;
		
		public GeologyObjectBuilder setPrototype(GeologyPrototype prototype) {
			this.prototype = prototype;
			return this;
		}
		
		public GeologyObjectBuilder setSubObjectNumber(int number) {
			this.number = number;
			return this;
		}
		
		public GeologyObjectBuilder setFactory(GeologyFactory factory) {
			this.factory = factory;
			return this;
		}
		
		public GeologyObject build() { return new GeoObj(this); }
		
		GeologyPrototype getPrototype() { return prototype;}
		GeologyFactory getFactory()     { return factory;  }
		int getSubObjectNumber()		{ return number;   }
	}
	
	final class GeoObj implements GeologyObject {

		private final GeologyPrototype prototype;
		private final GeologyObjectMap map;
		
		private List<GeologyObject> internal;
		private List<GeologyObject> external;
		
		private GeoObj(GeologyObjectBuilder builder) {
			prototype = builder.getPrototype();
			internal = new ArrayList<>();
			external = new ArrayList<>();
			map = new GeologyObjectMap(builder.getFactory(),builder.getSubObjectNumber(),prototype.getSuperRegion());
		}
		
		public final boolean isPrototype() { return false; }
		public final boolean isVectorInRegion(Vector3i query) { return prototype.isVectorInRegion(query); }
		public final boolean isVectorInRegion(Vector2i center) { return prototype.isVectorInRegion(center); }
		public final Region getSuperRegion() { return prototype.getSuperRegion(); }

		public final void primeGeneration(GenerationData metaData) {
			internal.clear();
			external.clear();
			Vector2i location = metaData.get2DCoordinate();
			if(isVectorInRegion(location)) {
				internal = map.getOverlappingObjects(location );
				external = map.getDistantObjects(location);
				for(GeologyObject obj : internal)
					obj.primeGeneration(metaData);
				for(GeologyObject obj : external)
					obj.primeGeneration(metaData);
			}
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
			GeologyData<Surface> surf=null;
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
			GeologyData<Alteration> alt=null;
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
			GeologyData<Replacement> rep=null;
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
	}
	
}

