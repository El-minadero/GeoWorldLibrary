package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.factory;

import java.util.Random;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node.INode;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.prototype.IPrototype;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.IRegion;
import net.kevinmendoza.geoworldlibrary.utilities.HashCodeOperations;

public abstract class AbstractPrototypeFactory extends AbstractFactory {

	public abstract INode makePrototype(Vector2i vec);
	public abstract INode makeNode(IPrototype abstractPrototype);
	public abstract IRegion makeRegion(Vector2i vec);

	protected long createVectorSeed(Vector2i vec) {
		long s = HashCodeOperations.createVectorSeed(vec);
		return hash(s);
	}

	protected long createVectorSeed(Vector3i vec) {
		long s = HashCodeOperations.createVectorSeed(vec);
		return hash(s);
	}
	
	private long hash(long s) {
		return s %(1024 + getSeed());
	}
	@Override
	public final boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof AbstractPrototypeFactory)) {
			return false;
		}
		AbstractPrototypeFactory user = (AbstractPrototypeFactory) o;
		return user.hashCode()==user.hashCode();
	}
	
	
}
