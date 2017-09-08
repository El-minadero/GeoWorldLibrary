package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import java.util.Random;

public class EllipsoidFactory extends RegionFactory implements IRegionFactory {

	@Override
	protected IConic createConic(double[] axis) {
		return new Ellipsoid.Builder()
				.setXAxis(axis[0])
				.setYAxis(axis[1])
				.setZAxis(axis[2])
				.build();
	}

	@Override
	protected IBoundingBox createBoundingBox(double[] axis,Random rand) {
		return new RectangularPrismBox.Builder()
				.setXDim((int) axis[0])
				.setYDim((int) axis[1])
				.setZDim((int) axis[2])
				.setRandom(rand)
				.build();
	}
}
