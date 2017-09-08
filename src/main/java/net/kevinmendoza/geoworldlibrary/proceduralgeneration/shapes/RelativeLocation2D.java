package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

class RelativeLocation2D extends RelativeLocation {

	private static final int y = 0;
	RelativeLocation2D(Builder builder) {
		super(builder);
	}


	public Vector2i getGlobalPoint(Vector2i localPoint) { 
		Vector3i vec = new Vector3i(localPoint.getX(),y,localPoint.getY());
		vec = applyInverseRotation(vec);
		vec = addToCenter(vec);
		return new Vector2i(vec.getX(),vec.getZ());
	}


	public Vector3i getGlobalPoint(Vector3i localPoint) {
		Vector3i vec = applyInverseRotation(localPoint);
		vec = addToCenter(vec);
		return vec;
	}

	public Vector2i getLocalPoint(Vector2i globalPoint) {
		Vector3i vec = new Vector3i(globalPoint.getX(),y,globalPoint.getY());
		vec = subtractCenter(vec);
		vec = applyRotation(vec);
		return new Vector2i(vec.getX(),vec.getZ());
	}

	public Vector3i getLocalPoint(Vector3i globalPoint) {
		Vector3i vec = subtractCenter(globalPoint);
		vec = applyRotation(vec);
		return vec;
	}

	
}
