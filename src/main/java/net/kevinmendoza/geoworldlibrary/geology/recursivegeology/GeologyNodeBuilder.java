package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologicalFactory.GeologyFactory;

public class GeologyNodeBuilder {
	
	private CompositeGeologyInterface prototype;
	private int number;
	private GeologyFactory factory;

	public GeologyNodeBuilder setPrototype(CompositeGeologyInterface prototype) {
		this.prototype = prototype;
		return this;
	}

	public GeologyNodeBuilder setSubObjectNumber(int number) {
		this.number = number;
		return this;
	}

	public GeologyNodeBuilder setFactory(GeologyFactory factory) {
		this.factory = factory;
		return this;
	}

	public GeologyNode build() { return new GeologyNode(this); }

	CompositeGeologyInterface getPrototype() { return prototype;}
	GeologyFactory getFactory()     { return factory;  }
	int getSubObjectNumber()		{ return number;   }

}
