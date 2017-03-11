package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import net.kevinmendoza.geoworldlibrary.geology.Geology;

public abstract class AbstractMapFactory extends AbstractFactory {
	public abstract Geology createGeologyMap();
	
	protected final Geology makeGeologyMap(GeologyMapBuilder builder) {
		return new GeologyMap(builder);
	}
}
