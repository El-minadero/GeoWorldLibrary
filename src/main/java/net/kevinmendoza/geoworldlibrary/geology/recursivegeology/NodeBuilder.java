package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.HashMap;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;

public class NodeBuilder implements INodeBuilder {
	
	private IGeologyNode prototype;
	private HashMap<Integer, AbstractPrototypeFactory> factoryMap;

	public INodeBuilder setPrototype(AbstractPrototype prototype) {
		this.prototype = prototype;
		return this;
	}
	
	public INodeBuilder setFactories(
			HashMap<Integer, AbstractPrototypeFactory> factoryMap) {
		this.factoryMap = factoryMap; return this;
	}

	public INodeBuilder setSingleFactory(int subObjectNumber,AbstractPrototypeFactory factory) {
		factoryMap = new HashMap<>();
		factoryMap.put(subObjectNumber, factory);
		return this;
	}
	
	public Node build() { return new Node(this); }

	public IGeologyNode getPrototype() { return prototype;}

	public HashMap<Integer, AbstractPrototypeFactory> getFactories() { return factoryMap; }
}
