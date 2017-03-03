package net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification;

import com.flowpowered.math.vector.Vector2i;

public interface PointModifier {

	public Vector2i getOffsetPoint(Vector2i vec);
}
