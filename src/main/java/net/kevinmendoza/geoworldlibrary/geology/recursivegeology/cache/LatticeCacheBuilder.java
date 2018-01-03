package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache;

import java.util.HashMap;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.IPointGenerator;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.PointGeneratorFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.IRegion;

class LatticeCacheBuilder  {

	private ICacheFactory cacheFactory;
	private IPointGenerator generator;
	
	public LatticeCacheBuilder() {
		generator =  PointGeneratorFactory
				.makeSinglePointLattice(0, 10, 0);
	}

	public LatticeCacheBuilder setFactory(ICacheFactory factory) 
	{ this.cacheFactory = factory; return this; }
	
	public LatticeCacheBuilder setPointGenerator(IPointGenerator generator) 
	{	this.generator = generator; return this; }
	
	
	public IPointGenerator getPointGenerator() 	{ return generator; 		}
	public ICacheFactory getFactory() 			{ return cacheFactory;	}
	
	public ICache build() {
		return new LatticeCache(this);
	}
	
}
