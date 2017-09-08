package net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

class NullModifier  implements PointModifier {
	
	public Vector2i getOffsetPoint(Vector2i vec) {
		return vec;
	}
	
	public Vector3i getOffsetPoint(Vector3i vec) {
		return vec;
	}

}