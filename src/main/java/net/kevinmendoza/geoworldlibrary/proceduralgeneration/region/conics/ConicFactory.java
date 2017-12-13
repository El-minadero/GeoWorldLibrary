package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics;

import java.util.Random;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingbox.IBoundingBox;

public class ConicFactory {

	public IConic createConic(ConicType type, int[] dims) {
		double[] d = new double[dims.length];
		for(int i = 0; i<dims.length;i++) { d[i] = dims[i]; }
		IConicBuilder builder;
		switch(type) {
		case ELLIPSE: 
			builder = new Ellipse.Builder();
			break;
		case ELLIPSOID:
			builder = new Ellipsoid.Builder();
			break;
		case RECTANGLE:
			builder = new RectangleConic.Builder();
			break;
		case BOX:
			builder = new BoxConic.Builder();
			break;
		default:
			builder = new RectangleConic.Builder();
			break;
		}
		return builder.setAxis(d).build();
	}

}
