package net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public interface IPointModifier {

	public Vector2i getPoint(Vector2i vec);

	public Vector3i getPoint(Vector3i vec);
}
