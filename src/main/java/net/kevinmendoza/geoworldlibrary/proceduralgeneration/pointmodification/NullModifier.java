package net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

class NullModifier  implements IPointModifier {
	
	public Vector2i getPoint(Vector2i vec) {
		return vec;
	}
	
	public Vector3i getPoint(Vector3i vec) {
		return vec;
	}

}
