package net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;

class PrototypeMap  extends AbstractMap {

	@Override
	public double getNoise(double x, double y) {
		return 0;
	}

	@Override
	public double getNoise(double x, double y, double z) {
		return 0;
	}

	@Override
	public double getNoise(Vector3i vec) {
		return 0;
	}

	@Override
	public double getNoise(Vector2i vec) {
		return 0;
	}

	@Override
	public double getNoise(Vector3d vec) {
		return 0;
	}

	@Override
	public double getNoise(Vector2d vec) {
		return 0;
	}

}
