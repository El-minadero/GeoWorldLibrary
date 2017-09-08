package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.HashMap;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;

public class NullFactory extends AbstractPrototypeFactory {

	@Override
	public IGeologyNode makePrototype(Vector2i vec) {
		return null;
	}

	@Override
	public IGeologyNode makeObject(AbstractPrototype prototype) {
		return new NodeBuilder().setPrototype(prototype)
				.setFactories(getEmptyMap())
				.build();
	}

	@Override
	public Region makeRegion(Vector2i vec) {
		return null;
	}

	private HashMap<Integer,AbstractPrototypeFactory> getEmptyMap(){
		return new HashMap<Integer,AbstractPrototypeFactory>();
	}
}
