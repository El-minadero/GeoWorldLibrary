package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;

import java.util.HashSet;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.INodeCache;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.NodeCacheBuilder;

public class NodeBuilder implements ICacheBuilder {

	private INode prototype;
	private INodeCache cache;
	
	public NodeBuilder() {
		prototype = new PrototypeBuilder().build();
		cache 	  = new NodeCacheBuilder().build();
	}
	
	public NodeBuilder setPrototype(INode prototype)	{ this.prototype=prototype; 	return this;		}
	public NodeBuilder setCache(INodeCache cache) 	{ this.cache = cache; 		return this; 	}

	public INodeCache getCache() 		{	return cache; 		}
	public INode getPrototype() 			{ 	return prototype; 	}
	
	public INode build() {
		return new Node(this);
	}
	
}
