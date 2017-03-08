package net.kevinmendoza.geoworldlibrary.geology.rockparameters;

import java.util.HashMap;

import com.flowpowered.math.vector.Vector2i;

public abstract class GenerationData {

	private final HashMap<String, Object> map = new HashMap<>();
	
	public final void put(String key, Object value) { map.put(key, value); }
	public final <T> T get(String key, Class<? extends T> type) { return type.cast(map.get(key)); }
	public int getBaseHeight() { return Integer.class.cast(map.get("BaseHeight")); }
	public Vector2i get2DCoordinate() { return Vector2i.class.cast(map.get("Coordinate")); }

}
