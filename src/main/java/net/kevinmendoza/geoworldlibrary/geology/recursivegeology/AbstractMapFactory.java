package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

public abstract class AbstractMapFactory extends AbstractFactory {
	public abstract IGeology createGeologyMap();
	public abstract IGeology createDebuggedGeologyMap();
	
	protected final IGeology makeGeologyMap(IGeologyMapBuilder builder) {
		return new Map(builder);
	}
	
}
