package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;

import java.util.HashMap;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.INodeCache;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.factory.AbstractPrototypeFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.factory.INodeFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.prototype.IPrototype;

public interface INodeBuilder {
	
	public INodeCache   	getCache();
	public IPrototype 	getPrototype();

}
