package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import java.util.Random;

import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.RotationConvention;
import org.apache.commons.math3.geometry.euclidean.threed.RotationOrder;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.utilities.HashCodeOperations;

abstract class RegionFactory {

	protected abstract IConic createConic(double[] axis);
	protected abstract IBoundingBox createBoundingBox(double[] axis, Random rand);
	
	public Region getRegion(Vector2i vec, double[] axis, boolean b) {
		Random rand = getRandomObject(vec);
		IRelativePointModifier pointModifier = getPointModifier(vec,b,rand);
		IConic conic = createConic(axis);
		IBoundingBox box = createBoundingBox(axis,rand);
		BoundingModel model = new BoundingModel(box,conic);
		return new Shape(model, pointModifier, rand);
	}

	public Region getRegion(Vector3i vec, double[] axis, boolean b) {
		Random rand = getRandomObject(vec);
		IRelativePointModifier pointModifier = getPointModifier(vec,b,rand);
		IConic conic = createConic(axis);
		IBoundingBox box = createBoundingBox(axis,rand);
		BoundingModel model = new BoundingModel(box,conic);
		return new Shape(model, pointModifier, rand);
	}
	private Rotation create3DRotation(Random rand, boolean isRandom) {
		double yAxisRot=0;
		double xAxisRot=0;
		double zAxisRot=0;
		if(isRandom) {
			xAxisRot = rand.nextDouble()*Math.PI;
			yAxisRot = rand.nextDouble()*Math.PI;
			zAxisRot = rand.nextDouble()*Math.PI;
		}
		return new Rotation(RotationOrder.XYZ, RotationConvention.FRAME_TRANSFORM, 
				xAxisRot, yAxisRot, zAxisRot);
	}
	private Rotation create2DRotation(Random rand, boolean isRandom) {
		double yAxisRot=0;
		double xAxisRot=0;
		double zAxisRot=0;
		if(isRandom) {
			yAxisRot = rand.nextDouble()*Math.PI;
		}
		return new Rotation(RotationOrder.XYZ, RotationConvention.FRAME_TRANSFORM, 
				xAxisRot, yAxisRot, zAxisRot);
	}
	private Random getRandomObject(Vector3i vec) {
		long seed = HashCodeOperations.createVectorSeed(vec);
		Random rand = new Random(seed);
		rand.nextDouble();
		return rand;
	}
	private Random getRandomObject(Vector2i vec) {
		long seed = HashCodeOperations.createVectorSeed(vec);
		Random rand = new Random(seed);
		rand.nextDouble();
		return rand;
	}
	
	private IRelativePointModifier getPointModifier(Vector2i center,boolean randomRotation,Random rand) {
		Rotation rotation = create2DRotation(rand, randomRotation);
		return new RelativeLocation.Builder()
				.setCenter(center)
				.setRotation(rotation)
				.build2DLocation();
	}
	private IRelativePointModifier getPointModifier(Vector3i center,boolean randomRotation,Random rand) {
		Rotation rotation = create3DRotation(rand, randomRotation);
		return new RelativeLocation.Builder()
				.setCenter(center)
				.setRotation(rotation)
				.build3DLocation();
	}
}
