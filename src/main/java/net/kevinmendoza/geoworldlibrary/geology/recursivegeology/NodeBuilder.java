package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;

public final class NodeBuilder implements INodeBuilder {
	
	private IGeologyNode prototype;
	private int number;
	private AbstractPrototypeFactory factory;
	private PointModifier modifier;

	public NodeBuilder setPrototype(AbstractPrototype prototype) {
		this.prototype = prototype;
		this.modifier = prototype.getPointModifier();
		return this;
	}

	public NodeBuilder setSubObjectNumber(int number) {
		this.number = number;
		return this;
	}

	public NodeBuilder setFactory(AbstractPrototypeFactory factory) {
		this.factory = factory;
		return this;
	}

	public Node build() { return new Node(this); }

	IGeologyNode getPrototype() { return prototype;}
	AbstractPrototypeFactory getFactory()     { return factory;  }
	int getSubObjectNumber()		{ return number;   }

	public PointModifier getPointModifierMap() { return modifier; }

}
