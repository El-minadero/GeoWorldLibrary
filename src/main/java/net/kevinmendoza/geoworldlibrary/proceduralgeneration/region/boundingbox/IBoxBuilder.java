package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingbox;

import java.util.Random;

interface IBoxBuilder {

	IBoxBuilder setAxis(int[] axis);
	IBoxBuilder setRandom(Random rand);
	int[] getAxis();
	Random getRandom();
	
	IBoundingBox build();

}
