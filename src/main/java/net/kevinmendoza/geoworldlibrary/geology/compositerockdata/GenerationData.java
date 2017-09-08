package net.kevinmendoza.geoworldlibrary.geology.compositerockdata;

import java.util.HashMap;

import com.flowpowered.math.vector.Vector2i;

public abstract class GenerationData {

	protected HashMap<String, Object> map = new HashMap<>();

	public final void put(String key, Object value) { map.put(key, value); }
	public final <T> T get(String key, Class<? extends T> type) { return type.cast(map.get(key)); }
	public int getBaseHeight() {
		int baseHeight = Integer.class.cast(map.get("BaseHeight"));
		return baseHeight;
	}
	public void setHeight(int h) { put("BaseHeight",h); }
	public Vector2i get2DCoordinate() { return Vector2i.class.cast(map.get("Coordinate")); }
	public void set2DCoordinate(Vector2i vec) { map.put("Coordinate",vec); }
	public GenerationData getCopy() {
		final HashMap<String, Object> map2 = new HashMap<>();
		for(String key : map.keySet()) {
			map2.put(key, map.get(key));
		}
		return new PrimeData(map2);
	}
	@Override
	public String toString() {
		String s = "";
		for(String key : map.keySet()) {
			s = s + " " + key + ":" + map.get(key).toString();
		}
		return s;
	}

}