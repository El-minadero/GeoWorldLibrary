package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import java.util.Random;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

class EllipseFactory extends RegionFactory implements IRegionFactory {

	@Override
	protected IConic createConic(double[] axis) {
		return new Ellipse.Builder()
				.setXAxis(axis[0])
				.setYAxis(axis[1])
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
