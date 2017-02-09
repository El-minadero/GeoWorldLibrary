package net.kevinmendoza.geoworldlibrary.geology.regionmap;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.geology.LithogenicOrder;
import net.kevinmendoza.geoworldlibrary.geology.StratigraphicColumn;

public interface GeologicRegionMapInterface {

	public void primeGenerationAt(Vector2i min);

	public void setSeed(long seed);
	
	public LithogenicOrder getOrder();

	public StratigraphicColumn getColumnAt(Vector2i center);
}
