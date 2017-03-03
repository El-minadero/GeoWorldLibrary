package net.kevinmendoza.geoworldlibrary.geology.test;

import com.google.inject.Inject;

import net.kevinmendoza.geoworldlibrary.geology.Geology;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologicalFactory.GeologyFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologicalFactory.GeologyMapFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologyMapBuilder.MapBuilder;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologyPrototype;
import net.kevinmendoza.geoworldlibrary.geology.test.TestParameters.MapParameters;
import net.kevinmendoza.geoworldlibrary.geology.test.TestParameters.TestMapParameters;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;

class TestMapFactory extends GeologyMapFactory {

	@Inject
	private MapParameters parameters;
	
	@Override
	public Geology createGeologyMap() {
		return makeGeologyMap(new Builder().setSpacing(parameters.getSpacing())
				.setFrequency(parameters.getFrequency())
				.setSeed(getSeed())
				.setFactory(TestFactoryHub.getTestFactory1())
				.setOrder(parameters.getOrder())
				.setPrototype(parameters.getPrototype()));
	}
	
	private static class Builder implements MapBuilder {
		
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
