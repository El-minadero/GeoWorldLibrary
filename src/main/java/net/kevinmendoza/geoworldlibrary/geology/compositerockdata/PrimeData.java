package net.kevinmendoza.geoworldlibrary.geology.compositerockdata;

import java.util.HashMap;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.GenerationData;

public class PrimeData extends GenerationData {

	public PrimeData(Vector2i center){
		put("Coordinate", center);
	}
	
	public PrimeData(HashMap<String, Object> map2) {
		map = map2;
	}

}
