package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.relativelocation;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;


class RelativeSpace2D extends RelativeSpace {

	private static final int y = 0;
	RelativeSpace2D(RelativeSpaceBuilder builder) {
		super(builder);
	}


	public Vector2i getGlobalPoint(Vector2i localPoint) { 
		Vector3i vec = new Vector3i(localPoint.getX(),y,localPoint.getY());
		vec = applyRotation(vec);
		vec = addToCenter(vec);
		return new Vector2i(vec.getX(),vec.getZ());
	}


	public Vector3i getGlobalPoint(Vector3i localPoint) {
		Vector3i vec = applyRotation(localPoint);
		vec = addToCenter(vec);
		return vec;
	}

	public Vector2i getLocalPoint(Vector2i globalPoint) {
		Vector3i vec = new Vector3i(globalPoint.getX(),y,globalPoint.getY());
		vec = subtractCenter(vec);
		vec = applyInverseRotation(vec);
		return new Vector2i(vec.getX(),vec.getZ());
	}

	public Vector3i getLocalPoint(Vector3i globalPoint) {
		Vector3i vec = subtractCenter(globalPoint);
		vec = applyInverseRotation(vec);
		return vec;
	}

	@Override
	public double getDistanceToCenter(Vector3i vec) {
		return getFast2iCenter().distance(new Vector2i(vec.getX(),vec.getZ()));
	}

	
}
