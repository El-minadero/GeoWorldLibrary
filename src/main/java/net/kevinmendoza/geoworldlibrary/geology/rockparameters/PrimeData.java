package net.kevinmendoza.geoworldlibrary.geology.rockparameters;

import java.util.HashMap;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GenerationData;

public class PrimeData extends GenerationData {

	public PrimeData(Vector2i center){
		put("Coordinate", center);
	}
	
	public PrimeData(HashMap<String, Object> map2) {
		map = map2;
	}

	public void setHeight(int h) {
		put("BaseHeight",h);
	}
}
