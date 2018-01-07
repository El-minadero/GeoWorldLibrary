package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.INodeCache;

public class MapBuilder implements ICacheBuilder {

	private INodeCache cache;
	
	public MapBuilder setCache(INodeCache cache) { this.cache = cache; return this; 	}
	public IGeology build() 						{ return new Map(this);				}
	public INodeCache getCache() 				{ return cache;						}

}
