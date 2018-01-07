package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.IData;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.NullData;

 
final class Map extends AbstractNode implements IGeology {

	Map(MapBuilder builder) {
		super(builder);
	}

	public 		final IData getDefaultData(Vector2i vector2i) { IData 	data = new NullData();
																		data.modifyData(0);
																		return data; }
	public 		final IData getDefaultData(Vector3i vector3i) { IData 	data = new NullData();
																		data.modifyData(0);
																		return data; }
	protected 	final IData applyModifier(IData data, Vector2i vector2i) { return data; }
	protected 	final IData applyModifier(IData data, Vector3i vector3i) { return data; }

	protected 	final double getModifier(Vector2i vector2i) { return 1; }
	protected 	final double getModifier(Vector3i vector3i) { return 1; }
	protected 	final boolean shouldLoadCaches(Vector3i vector3i) { return true; }
	protected 	final boolean shouldLoadCaches(Vector2i vector2i) { return true; }

}
