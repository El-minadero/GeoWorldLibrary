package net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;

final class ValueOffsetMap extends AbstractMap {

	private final NoiseMap map;
	private final double offset;
	public ValueOffsetMap(NoiseMap map, double offset) {
		this.map = map;
		this.offset = offset;
	}

	public double getNoise(double x, double y) { return map.getNoise(x,y) + offset; }
	public double getNoise(double x, double y, double z) {return map.getNoise(x,y,z) + offset;}
	public double getNoise(Vector3i vec) { return map.getNoise(vec) + offset; }
	public double getNoise(Vector2i vec) { return map.getNoise(vec) + offset; }
	public double getNoise(Vector3d vec) { return map.getNoise(vec) + offset; }
	public double getNoise(Vector2d vec) { return map.getNoise(vec) + offset; }

}
