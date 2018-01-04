package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingmodel;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingbox.IBoundingBox;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics.IConic;

public class BoundingModelFactory {

	public static IBoundingModel makeBoundingModel(IConic conic ,IBoundingBox box) {
		return new BoundingModel(conic,box);
	}
	
}
