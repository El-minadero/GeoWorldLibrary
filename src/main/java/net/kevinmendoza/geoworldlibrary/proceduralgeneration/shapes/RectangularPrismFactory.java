package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import java.util.Random;

class RectangularPrismFactory extends RegionFactory implements IRegionFactory {

	@Override
	protected IConic createConic(double[] axis) {
		return new RectangularPrismConic.Builder()
				.setXDim((int) axis[0])
				.setYDim((int) axis[1])
				.setZDim((int) axis[2])
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
