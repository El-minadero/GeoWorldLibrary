package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.IData;

 
final class Map extends AbstractNode implements IGeology {

	Map(ICacheBuilder builder) {
		super(builder);
	}

	public 		final IData getDefaultData(Vector2i vector2i) { return null; }
	public 		final IData getDefaultData(Vector3i vector3i) { return null; }
	protected 	final IData applyModifier(IData data, Vector2i vector2i) { return data; }
	protected 	final IData applyModifier(IData data, Vector3i vector3i) { return data; }

	protected double getModifier(Vector2i vector2i) { return 1; }
	protected double getModifier(Vector3i vector3i) { return 1; }

}
