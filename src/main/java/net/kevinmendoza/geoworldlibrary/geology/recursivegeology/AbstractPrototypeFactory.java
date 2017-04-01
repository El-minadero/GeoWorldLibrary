package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.Random;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.RegionPointGenerator;

public abstract class AbstractPrototypeFactory extends AbstractFactory {

	public abstract IGeologyNode makePrototype(Vector2i vec);
	public abstract IGeologyNode makeObject(AbstractPrototype prototype);
	public abstract Region makeRegion(Vector2i vec);

	protected long createVectorSeed(Vector2i vec) {
		long s = (vec.getX()*661) + vec.getY();
		return hash(s);
	}

	protected long createVectorSeed(Vector3i vec) {
		long s = ((vec.getX()*661) + vec.getY()) + vec.getZ()*331;
		return hash(s);
	}
	
	private long hash(long s) {
		return s %(1024 + getSeed());
	}
	
}
