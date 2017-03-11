package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

public final class GeologyNodeBuilder {
	
	private GeologyComposite prototype;
	private int number;
	private AbstractPrototypeFactory factory;

	public GeologyNodeBuilder setPrototype(GeologyComposite prototype) {
		this.prototype = prototype;
		return this;
	}

	public GeologyNodeBuilder setSubObjectNumber(int number) {
		this.number = number;
		return this;
	}

	public GeologyNodeBuilder setFactory(AbstractPrototypeFactory factory) {
		this.factory = factory;
		return this;
	}

	public GeologyNode build() { return new GeologyNode(this); }

	GeologyComposite getPrototype() { return prototype;}
	AbstractPrototypeFactory getFactory()     { return factory;  }
	int getSubObjectNumber()		{ return number;   }

}
