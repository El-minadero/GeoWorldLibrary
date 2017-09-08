package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.HashMap;

public interface INodeBuilder {

	public INodeBuilder setPrototype(AbstractPrototype prototype);
	public INodeBuilder setFactories(HashMap<Integer,AbstractPrototypeFactory> factoryMap);
	public INodeBuilder setSingleFactory(int subObjectNumber,AbstractPrototypeFactory factory);
	
	public Node build();
	public IGeologyNode getPrototype();
	public HashMap<Integer,AbstractPrototypeFactory> getFactories();

}
