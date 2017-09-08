package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public interface IBoundingBox {

	public Vector2i getRandomInternalPoint2i();

	public Vector3i getRandomInternalPoint3i();

	public boolean isInside(Vector2i localPoint);
	
	public boolean isInside(Vector3i localPoint);

}
