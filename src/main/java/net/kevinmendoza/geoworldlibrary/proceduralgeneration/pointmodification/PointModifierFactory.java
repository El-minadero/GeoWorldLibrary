package net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;

public class PointModifierFactory {

	
	public static PointModifier CreateNoiseMapPointOffset(NoiseMap mapx, NoiseMap mapy, NoiseMap mapz) {
		return new NoiseMapModifier(mapx,mapy,mapz);
	}
	
	public static PointModifier CreateNullPointOffset() {
		return new NullModifier();
	}
	
	public static Vector2i Extract2iVector(Vector3i vec) {
		return new Vector2i(vec.getX(),vec.getZ());
	}
	
	public static Vector3i MakeVector3i(Vector2i vec,int y) {
		return new Vector3i(vec.getX(),y,vec.getY());
	}
	
	private static class NullModifier implements PointModifier {
		
		public Vector2i getOffsetPoint(Vector2i vec) {
			return vec;
		}
		
		public Vector3i getOffsetPoint(Vector3i vec) {
			return vec;
		}
	}
	
	private static class NoiseMapModifier implements PointModifier {

		private final List<NoiseMap> maps;
		
		private NoiseMapModifier(NoiseMap mapx,NoiseMap mapy, NoiseMap mapz) {
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
}
