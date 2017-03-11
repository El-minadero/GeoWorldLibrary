package net.kevinmendoza.geoworldlibrary.geology.test.maptest;

import net.kevinmendoza.geoworldlibrary.geology.Geology;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractMapFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractPrototypeFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologyMapBuilder;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologyPrototype;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.geoworldlibrary.geology.test.TestFactoryHub;

public class TestMapFactory extends AbstractMapFactory {

	private final int SPACING = 5000;
	private final int Frequency = 0;
	private final Order order = Order.FIRST;
	
	@Override
	public Geology createGeologyMap() {
		Builder b = new Builder()
				.setSpacing(SPACING)
				.setFrequency(Frequency)
				.setSeed(getSeed())
				.setFactory(TestFactoryHub.GetObject1Factory())
				.setOrder(order)
				.setPrototype(new MapObject());
		return makeGeologyMap(b);
	}
	
	private static class Builder implements GeologyMapBuilder {
		
		private int spacing;
		private double freq;
		private Order order;
		private long seed;
		private AbstractPrototypeFactory factory;
		private GeologyPrototype prototype;
		
		private Builder setPrototype(GeologyPrototype prototype) {this.prototype = prototype; return this;}
		private Builder setSpacing(int spacing) {this.spacing = spacing; return this;}
		private Builder setOrder(Order order)    {this.order = order; return this; }
		private Builder setFrequency(double freq) {this.freq = freq; return this; }
		private Builder setSeed(long seed) 		{this.seed = seed; return this; }
		private Builder setFactory(AbstractPrototypeFactory factory) { this.factory = factory; return this; }
	
		public int getSpacing() { return spacing; }
		public double getFrequency() { return freq; }
		public Order getOrder() { return order;  }
		public long getSeed() { return seed; }
		public AbstractPrototypeFactory getFactory() { return factory;}
		public GeologyPrototype getPrototype() { return prototype;  }
		
	}
}
