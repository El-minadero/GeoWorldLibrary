package net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification;

import java.util.ArrayList;
import java.util.List;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;

class NoiseMapModifier implements PointModifier {

	private final List<NoiseMap> maps;
	
	NoiseMapModifier(NoiseMap map) {
		maps = new ArrayList<>();
		maps.add(map); maps.add(map); maps.add(map);
	}
	
	NoiseMapModifier(NoiseMap mapx,NoiseMap mapy, NoiseMap mapz) {
		maps = new ArrayList<>();
		maps.add(mapx); maps.add(mapy); maps.add(mapz);
	}
	
	@Override
	public Vector2i getOffsetPoint(Vector2i vec) {
		int xx = (int) maps.get(0).getNoise(vec) + vec.getX();
		int zz = (int) maps.get(2).getNoise(vec.getY(),vec.getX()) + vec.getY();
		return new Vector2i(xx,zz);
	}
	
	@Override
	public Vector3i getOffsetPoint(Vector3i vec) {
		int xx = (int) maps.get(0).getNoise(vec) + vec.getX();
		int yy = (int) maps.get(1).getNoise(vec)*0 + vec.getY();
		int zz = (int) maps.get(2).getNoise(vec.getZ(),vec.getY(),vec.getX()) + vec.getZ();
		return new Vector3i(xx,yy,zz);
	}
}
