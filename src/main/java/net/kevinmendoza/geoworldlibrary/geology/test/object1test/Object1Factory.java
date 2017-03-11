package net.kevinmendoza.geoworldlibrary.geology.test.object1test;

import com.flowpowered.math.vector.Vector2i;
import com.google.inject.Inject;

import net.kevinmendoza.geoworldlibrary.geology.Geology;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractMapFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractPrototypeFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologyComposite;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologyMapBuilder;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologyNodeBuilder;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologyPrototype;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologyPrototypeBuilder;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.geoworldlibrary.geology.test.ObjectParameters;
import net.kevinmendoza.geoworldlibrary.geology.test.TestFactoryHub;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;

public class Object1Factory extends AbstractPrototypeFactory {
	
	@Inject
	private ObjectParameters parameters;
	
	@Override
	public GeologyComposite makePrototype(Region superRegion) {
		return new PrototypeBuilder()
		.setExternalDecayConstant(parameters.getExternalDecayConstant())
		.setInternalDecayConstant(parameters.getInternalDecayConstant())
		.setOrder(parameters.getOrder())
		.setNoiseMap(parameters.getNoiseMap(superRegion.getRandomInternalPoint()))
		.setRegion(parameters.getRegion(superRegion))
		.setOffsetMap(parameters.getOffsetMap(superRegion)).build();
	}

	@Override
	public GeologyComposite makePrototype(Vector2i vec) {
		Region region = makeRegion(vec);
		return new PrototypeBuilder()
				.setExternalDecayConstant(parameters.getExternalDecayConstant())
				.setInternalDecayConstant(parameters.getInternalDecayConstant())
				.setOrder(parameters.getOrder())
				.setRegion(region)
				.setNoiseMap(parameters.getNoiseMap(vec))
				.setOffsetMap(parameters.getOffsetMap(region)).build();
	}

	@Override
	public GeologyComposite makeObject(GeologyPrototype prototype) {
		return new GeologyNodeBuilder()
		.setFactory(TestFactoryHub.GetObject2Factory())
		.setPrototype(prototype)
		.setSubObjectNumber(parameters.getSubObjectNumber(prototype.getSuperRegion().getRandomInternalPoint())).build();
	}

	@Override
	public Region makeRegion(Vector2i vec) {
		return parameters.getRegion(vec);
	}
	
	static class PrototypeBuilder implements GeologyPrototypeBuilder {

		
		private Region region;
		private Order order;
		private double internalDecayConstant;
		private double externalDecayConstant;
		private PointModifier offsetMap;
		private NoiseMap map;
		
		public PrototypeBuilder setNoiseMap(NoiseMap map) { this.map = map; return this; }
		public PrototypeBuilder setRegion(Region region) { this.region = region; return this; }
		public PrototypeBuilder setOffsetMap(PointModifier modifier) { this.offsetMap = modifier; return this; }
		public PrototypeBuilder setOrder(Order order) { this.order = order; return this; }
		public PrototypeBuilder setInternalDecayConstant(double d) { this.internalDecayConstant = d; return this; }
		public PrototypeBuilder setExternalDecayConstant(double d) { this.externalDecayConstant = d; return this; }
		
		public Region getRegion() { return region; }
		public Order getOrder() { return order; }
		public double getExternalDecayConstant() { return externalDecayConstant; }
		public double getInternalDecayConstant() { return internalDecayConstant; }
		public PointModifier getOffsetMap() { return offsetMap; }
		public NoiseMap getMap() { return map; }
		
		public GeologyComposite build() { return new Object1(this); }
	
	}
	
	
}
