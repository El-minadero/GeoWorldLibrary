package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;

import java.util.HashSet;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.INodeCache;

public class NodeBuilder implements ICacheBuilder {

	private IPrototype prototype;
	private INodeCache cache;
	
	public NodeBuilder setPrototype(IPrototype prototype) { this.prototype=prototype; 	return this;		}
	public NodeBuilder setCache(INodeCache cache) 	{ this.cache = cache; 		return this; 	}

	public INodeCache getCache() 	{	return cache; 		}
	public IPrototype getPrototype() 	{ 	return prototype; 	}
	
	public INode build() {
		return new Node(this);
	}
	
}
