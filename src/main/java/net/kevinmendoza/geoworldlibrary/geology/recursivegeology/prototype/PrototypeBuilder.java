package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.prototype;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.factory.AbstractPrototypeFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.factory.INodeFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.IRegion;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;

public class PrototypeBuilder {
	
	private IRegion region;
	private INodeFactory factory;
	private NoiseMap noiseMap;
	private String name; 
	private IDecay internalDecay;
	private IDecay externalDecay;
	
	public PrototypeBuilder setRegion(IRegion region) 			{ this.region = region; 		return this; }
	public PrototypeBuilder setSelfFactory(INodeFactory factory)	{ this.factory = factory; 	return this; }
	public PrototypeBuilder setNoiseMap(NoiseMap map)				{ this.noiseMap = map; 		return this; }
	public PrototypeBuilder setName(String name)					{ this.name = name; 			return this; }
	public PrototypeBuilder setInternalDecay(IDecay decay)		{ this.internalDecay=decay; 	return this; }
	public PrototypeBuilder setExternalDecay(IDecay decay)		{ this.externalDecay=decay; 	return this; }
	
	public IRegion getRegion() 			{ return region;		}
	public INodeFactory getSelfFactory() { return factory; 	}
	
	public IPrototype buildNull() {
		return new NullPrototype(this);
	}
}
