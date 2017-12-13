package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingbox;

import java.lang.reflect.Array;
import java.util.Random;

import com.flowpowered.math.vector.Vector2i;

abstract class Rectangle {

	private static final String coord = "XYZ";
	private final double[] dim;
	private final Random rand;
	
	protected Rectangle(IBoxBuilder builder) {
		int[] d = builder.getAxis();
		dim = new double[d.length];
		for(int i = 0;i<d.length;i++) {
			dim[i] = d[i];
		}
		rand = builder.getRandom();
	}
	
	protected double getDim(int i) { return dim[i]; }
	
	protected double getRandomCoordinateInBounds(int i) {
		double c = rand.nextDouble()*(dim[i]*2) - dim[i];
		return c;
	}
	
	protected boolean isCoordinateInBounds(int c,int i) {
		double upper = dim[i];
		int lower = Math.abs(c);
		return (lower <= upper);
	}
	
	@Override
	public String toString() {
		String s="";
		for(int i = 0;i<dim.length;i++) {
			s+= " " + coord.charAt(i) + ":" + dim[i];
		}
		return s;
	}
	
	public static interface RecBuilder {

		int[] getDim2Array();

		int[] getDimArray();

		Random getRand();
		
	}
}
