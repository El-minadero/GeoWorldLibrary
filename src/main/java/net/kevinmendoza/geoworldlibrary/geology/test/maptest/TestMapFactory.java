package net.kevinmendoza.geoworldlibrary.geology.test.maptest;

import net.kevinmendoza.geoworldlibrary.geology.Geology;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologicalFactory.GeologyFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologicalFactory.GeologyMapFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologyMapBuilder;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologyPrototype;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;

public class TestMapFactory extends GeologyMapFactory {

	private final int SPACING = 50;
	private final int Frequency = 0;
	private final Order order = Order.FIRST;
	
	@Override
	public Geology createGeologyMap() {
		Builder b = new Builder()
				.setSpacing(SPACING)
				.setFrequency(Frequency)
				.setSeed(getSeed())
				.setFactory(null)
				.setOrder(order)
				.setPrototype(new MapObject());
		return makeGeologyMap(b);
	}
	
	private static class Builder implements GeologyMapBuilder {
		
		private int spacing;
		private double freq;
		private Order order;
		private long seed;
		private GeologyFactory factory;
		private GeologyPrototype prototype;
		
		private Builder setPrototype(GeologyPrototype prototype) {this.prototype = prototype; return this;}
		private Builder setSpacing(int spacing) {this.spacing = spacing; return this;}
		private Builder setOrder(Order order)    {this.order = order; return this; }
		private Builder setFrequency(double freq) {this.freq = freq; return this; }
		private Builder setSeed(long seed) 		{this.seed = seed; return this; }
		private Builder setFactory(GeologyFactory factory) { this.factory = factory; return this; }
	
		public int getSpacing() { return spacing; }
		public double getFrequency() { return freq; }
		public Order getOrder() { return order;  }
		public long getSeed() { return seed; }
		public GeologyFactory getFactory() { return factory;}
		public GeologyPrototype getPrototype() { return prototype;  }
		
	}
}
