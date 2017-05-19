package net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;

class ImplementedMap extends AbstractMap {

	private final NoiseMap map;
	private final double multiplier;
	
	ImplementedMap(NoiseMap map, double multiplier) {
		this.map = map;
		this.multiplier = multiplier;
	}
	
	@Override
	public double getNoise(double x, double y, double z) {
		return map.getNoise(x, y, z)*multiplier;
	}
	
	@Override
	public double getNoise(double x, double y) {
		return getNoise(x,0,y);
	}
	
	@Override
	public double getNoise(Vector3i vec) {
		return getNoise(vec.getX(),vec.getY(),vec.getZ());
	}

	@Override
	public double getNoise(Vector2i vec) {
		return getNoise(vec.getX(),0,vec.getY());
	}

	@Override
	public double getNoise(Vector3d vec) {
		return getNoise(vec.getX(),vec.getY(),vec.getZ());
	}

	@Override
	public double getNoise(Vector2d vec) {
		return getNoise(vec.getX(),0,vec.getY());
	}
	
}
