package net.kevinmendoza.geoworldlibrary.geology.rockparameters;

import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.Surface;

public class GeologyInterface {
	
	static interface GeoDataInterface<T> {
		void merge(GeoDataInterface<T> other);
		boolean canReplace(GeoDataInterface<T> other);
		boolean canMerge(GeoDataInterface<T> other);
	}
	
	public static interface Alteration extends GeoDataInterface<Alteration>{
		Alteration getCopy();
	}
	public static interface Replacement extends GeoDataInterface<Replacement>{
		Replacement getCopy();
	}
	public static interface Surface extends GeoDataInterface<Replacement>{
		int getMaxHeight();
		Surface getCopy();
	}

	public interface RockCache {
		void placeEntry(Vector3i query, Replacement replace, Alteration alterate);
		
	}
}
