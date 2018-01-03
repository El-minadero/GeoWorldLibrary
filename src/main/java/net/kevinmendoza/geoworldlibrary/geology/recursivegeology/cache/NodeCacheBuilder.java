package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache;

import java.util.HashSet;

class NodeCacheBuilder {

	private HashSet<ICache> caches;
	
	public NodeCacheBuilder() {
		caches = new HashSet<>();
	}
	
	public NodeCacheBuilder addCache(ICache cache) { caches.add(cache); return this;}
	public HashSet<ICache> getCaches() { return caches; }
	
	public INodeCache build() {
		return new NodeCache(this);
	}

}
