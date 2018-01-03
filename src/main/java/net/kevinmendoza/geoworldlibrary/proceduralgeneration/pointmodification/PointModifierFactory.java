package net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;

public class PointModifierFactory {

	
	public static IPointModifier CreateNoiseMapPointOffset(NoiseMap mapx, NoiseMap mapy, NoiseMap mapz) {
		return new NoiseMapModifier(mapx,mapy,mapz);
	}
	
	public static IPointModifier CreateNoiseMapPointOffset(NoiseMap map) {
		return new NoiseMapModifier(map);
	}
	
	public static IPointModifier CreateNullPointOffset() {
		return new NullModifier();
	}
	
	public static Vector2i Extract2iVector(Vector3i vec) {
		return new Vector2i(vec.getX(),vec.getZ());
	}
	
	public static Vector3i MakeVector3i(Vector2i vec,int y) {
		return new Vector3i(vec.getX(),y,vec.getY());
	}

	public static IPointModifier CreateStaticShiftOffset(int x, int y,
			int z) {
		return new StaticShiftModifier(x, y,z);
	}
}
