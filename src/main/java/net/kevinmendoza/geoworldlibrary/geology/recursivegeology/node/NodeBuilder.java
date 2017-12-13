package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.INodeCache;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.prototype.IPrototype;

public class NodeBuilder implements INodeBuilder {

	private IPrototype prototype;
	private INodeCache cache;
	
	public NodeBuilder setPrototype(IPrototype prototype) { this.prototype=prototype; return this;		}
	public NodeBuilder setCache(INodeCache cache) 		 { this.cache = cache; 		return this; 	}

	public INodeCache getCache() 		{ return cache; 		}
	public IPrototype getPrototype() 	{ return prototype; 	}
	
	public INode build() {
		return new Node(this);
	}
	
	
}
