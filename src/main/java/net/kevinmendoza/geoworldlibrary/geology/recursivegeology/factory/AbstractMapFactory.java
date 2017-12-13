package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.factory;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node.IGeologyMapBuilder;

public abstract class AbstractMapFactory extends AbstractFactory {
	
	public abstract IGeology createGeologyMap();
	
	
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
