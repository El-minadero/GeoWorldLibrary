package net.kevinmendoza.geoworldlibrary.geology.test;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GenerationData;

class PrimeData extends GenerationData {

	PrimeData(Vector2i center){
		put("Coordinate", center);
	}
}
