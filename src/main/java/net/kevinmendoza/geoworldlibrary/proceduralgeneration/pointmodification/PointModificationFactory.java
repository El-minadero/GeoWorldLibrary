package net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;

public class PointModificationFactory {

	
	public static PointModifier CreatePointOffsetMultiplier(NoiseMap map) {
		return new NoiseMapModifier(map);
	}
	
	public static PointModifier CreateNullPointOffset() {
		return new NullModifier();
	}
	
	public static Vector2i extract2iVector(Vector3i vec) {
		return new Vector2i(vec.getX(),vec.getZ());
	}
	
	public static Vector3i MakeVector3i(Vector2i vec,int y) {
		return new Vector3i(vec.getX(),y,vec.getY());
	}
	
	private static class NullModifier implements PointModifier {
		
		public Vector2i getOffsetPoint(Vector2i vec) {
			return vec;
		}
	}
	
	private static class NoiseMapModifier implements PointModifier {

		private NoiseMap map;
		private NoiseMapModifier(NoiseMap map) {
			this.map = map;
		}
		
		@Override
		public Vector2i getOffsetPoint(Vector2i vec) {
			int x = vec.getX();
			int z = vec.getY();
			int xx = (int) map.getNoise(x,z) + x;
			int zz = (int) map.getNoise(z,x) + z;
			return new Vector2i(xx,zz);
		}
		
		
	}
}
