package net.kevinmendoza.geoworldlibrary.geology.rockmechanics;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public interface IStressField {

	public double[][] getStressField(Vector2i vector2i,boolean combined);
	public double[][] getStressField(Vector3i vector3i,boolean combined);
}
