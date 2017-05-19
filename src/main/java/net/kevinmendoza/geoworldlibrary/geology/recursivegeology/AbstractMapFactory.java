package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

public abstract class AbstractMapFactory extends AbstractFactory {
	public abstract IGeology createGeologyMap();
	public abstract IGeology createDebuggedGeologyMap();
	
	protected final IGeology makeGeologyMap(IGeologyMapBuilder builder) {
		return new Map(builder);
	}
	@Override
	public final boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof AbstractMapFactory)) {
			return false;
		}
		AbstractMapFactory user = (AbstractMapFactory) o;
		return user.hashCode()==user.hashCode();
	}
}
