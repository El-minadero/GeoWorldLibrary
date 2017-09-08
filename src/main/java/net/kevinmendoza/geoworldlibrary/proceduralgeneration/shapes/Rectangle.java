package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import java.util.Random;

import com.flowpowered.math.vector.Vector2i;

abstract class Rectangle {

	private final int[] dim;
	private final int[] dim2;
	private final Random rand;
	
	protected Rectangle(RecBuilder builder) {
		dim  = builder.getDimArray();
		dim2 = builder.getDim2Array();
		rand = builder.getRand();
	}
	
	
	protected int getRandomCoordinateInBounds(int i) {
		int c = rand.nextInt(dim2[i]) - dim[i];
		return c;
	}
	
	protected boolean isCoordinateInBounds(int c,int i) {
		return (c < dim[i] && c > -dim[i]);
	}
	
	@Override
	public String toString() {
		String s="";
		for(int i = 0;i<dim.length;i++) {
			s+= " " + i + ":" + dim[i] + ":" + dim2[i];
		}
		return s;
	}
	
	public static interface RecBuilder {

		int[] getDim2Array();

		int[] getDimArray();

		Random getRand();
		
	}
}
