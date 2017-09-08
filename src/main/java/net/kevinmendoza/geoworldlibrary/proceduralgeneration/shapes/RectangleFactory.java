package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import java.util.Random;

class RectangleFactory extends RegionFactory implements IRegionFactory {

	@Override
	protected IConic createConic(double[] axis) {
		return new RectangleConic.Builder()
				.setXDim((int) axis[0])
				.setYDim((int) axis[1])
				.build();
	}

	@Override
	protected IBoundingBox createBoundingBox(double[] axis,Random rand) {
		return new RectangleBox.Builder()
				.setXDim((int) axis[0])
				.setYDim((int) axis[1])
				.setRandom(rand)
				.build();
	}
}
