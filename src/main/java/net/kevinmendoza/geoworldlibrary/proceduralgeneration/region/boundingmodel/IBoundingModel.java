package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingmodel;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public interface IBoundingModel {

	boolean isOverXDim(Vector3i local);
	boolean isOverYDim(Vector3i local);
	boolean isOverZDim(Vector3i local);
	
	boolean isOverXDim(Vector2i local);
	boolean isOverYDim(Vector2i local);
	boolean isOverZDim(Vector2i local);

	double getArea();

	Vector2i getRandom2iPoint();

	Vector3i getRandom3iPoint();

	boolean isInside(Vector2i localPoint);

	boolean isInside(Vector3i localPoint);

	double getNormalizedDistanceToEdge(Vector2i localPoint);

	double getNormalizedDistanceToEdge(Vector3i localPoint);

	boolean isOnEdge(Vector3i localPoint);

	boolean isOnEdge(Vector2i localPoint);

}
