package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingbox;

import java.util.Random;

public class BoundingBoxFactory {

	public static IBoundingBox createBoundingBox(BoxType type, int[] axis,Random rand) {
		IBoxBuilder builder;
		switch(type) {
		case D2: 
			builder = new Box2D.Builder();
			break;
		case D3:
			builder = new Box3D.Builder();
			break;
		default:
			builder = new Box2D.Builder();
			break;
		}
		return builder.setAxis(axis).setRandom(rand).build();
	}
}
