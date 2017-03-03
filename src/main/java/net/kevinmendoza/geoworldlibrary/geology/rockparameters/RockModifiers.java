package net.kevinmendoza.geoworldlibrary.geology.rockparameters;

import java.util.HashMap;

import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.Alteration;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.Replacement;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.RockCache;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.Surface;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.BlockModifier.BlockStateModifier;

public class RockModifiers {
	public static Surface GetEmptySurface() {
		return new EmptySurface();
	}

	public static Replacement GetEmptyReplacement() {
		return new EmptyReplacement();
	}
	public static Alteration GetEmptyAlteration() {
		return new EmptyAlteration();
	}
	public static RockCache GetRockCache() {
		return new EmptyRockCache();
	}
	
	private static class EmptyRockCache implements RockCache {

		private HashMap<Integer,BlockStateModifier> modifiers;
		
		public EmptyRockCache() { modifiers = new HashMap<>(); }
		
		@Override
		public void placeEntry(Vector3i query,Replacement replace,Alteration alterate) {
			modifiers.put(query.getY(), BlockModifier.GetNullBlockModifier(alterate, replace));
		}
	}
	
	private static class EmptyAlteration implements Alteration {
		
		public Alteration getCopy() { return GetEmptyAlteration(); }
		public void merge(Alteration surface) { }
		public void merge(double mergeMultiplier, Alteration mergeAlteration) {}
		public int getMaxHeight() { return 0; }
	}
	
	private static class EmptyReplacement implements Replacement {
		
		public Replacement getCopy() { return GetEmptyReplacement(); }
		public void merge(Replacement surface) { }
		public int getMaxHeight() { return 0; }
		public void merge(double mergeMultiplier, Replacement mergeReplacement) {}
	}
	
	private static class EmptySurface implements Surface {
		private int height;
		
		public Surface getCopy() { return GetEmptySurface(); }
		public void setHeight(int h) { height = h; }
		public void merge(double mergeMultiplier, Surface surface) 
		{ height = (int) ((height + surface.getMaxHeight()*mergeMultiplier)/2); }
		public void merge(Surface surface) { height = (height + surface.getMaxHeight())/2; }
		public int getMaxHeight() { return height; }
		
	}
}
