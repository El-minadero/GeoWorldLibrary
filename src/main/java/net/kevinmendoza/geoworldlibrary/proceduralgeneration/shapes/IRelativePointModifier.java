package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public interface IRelativePointModifier {

	Vector2i getGlobalPoint(Vector2i localPoint);

	Vector3i getGlobalPoint(Vector3i localPoint);

	Vector2i getLocalPoint(Vector2i globalPoint);

	Vector3i getLocalPoint(Vector3i globalPoint);

	Vector2i getGlobalCenter2i();
	
	Vector3i getGlobalCenter3i();

}
