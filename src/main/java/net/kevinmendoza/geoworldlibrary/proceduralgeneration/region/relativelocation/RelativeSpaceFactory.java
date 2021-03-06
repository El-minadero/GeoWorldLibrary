package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.relativelocation;

import java.util.Random;

import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.RotationConvention;
import org.apache.commons.math3.geometry.euclidean.threed.RotationOrder;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingbox.BoxType;

public class RelativeSpaceFactory {
	
	public static IRelativeSpace createRelative2DFrame(Vector2i center, 
			Random rand, boolean isRandom) {
		RelativeSpaceBuilder builder = new RelativeSpaceBuilder();
		Rotation rotation = create2DRotation(rand,isRandom);
		return builder.setCenter(center).setRotation(rotation).build2DLocation();
	}
	
	public static IRelativeSpace createRelative3DFrame(Vector3i center, 
			Random rand, boolean isRandom) {
		RelativeSpaceBuilder builder = new RelativeSpaceBuilder();
		Rotation rotation = create3DRotation(rand,isRandom);
		return builder.setCenter(center).setRotation(rotation).build3DLocation();
	}
	public static IRelativeSpace createRelative2DFrame(Vector2i center, double y) {
		RelativeSpaceBuilder builder = new RelativeSpaceBuilder();
		Rotation rotation = create2DRotation(y);
		return builder.setCenter(center).setRotation(rotation).build2DLocation();
	}
	
	public static IRelativeSpace createRelative3DFrame(Vector3i center, 
			double x, double y, double z) {
		RelativeSpaceBuilder builder = new RelativeSpaceBuilder();
		Rotation rotation = create3DRotation(x,y,z);
		return builder.setCenter(center).setRotation(rotation).build3DLocation();
	}
	
	
	private static Rotation create3DRotation(double x, double y, double z) {
		return new Rotation(RotationOrder.XYZ, RotationConvention.FRAME_TRANSFORM, 
				x,y,z);
	}
	private static Rotation create2DRotation(double y) {
		return new Rotation(RotationOrder.XYZ, RotationConvention.FRAME_TRANSFORM, 
				0,y,0);
	}
	private static Rotation create3DRotation(Random rand, boolean isRandom) {
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
	private static Rotation create2DRotation(Random rand, boolean isRandom) {
		double yAxisRot=0;
		if(isRandom) {
			yAxisRot = rand.nextDouble()*Math.PI;
		}
		return new Rotation(RotationOrder.XYZ, RotationConvention.FRAME_TRANSFORM, 
				0,yAxisRot,0);
	}
	
}
