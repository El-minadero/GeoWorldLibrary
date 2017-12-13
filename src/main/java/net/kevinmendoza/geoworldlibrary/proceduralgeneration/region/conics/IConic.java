package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public interface IConic {

	public boolean isInside(Vector2i point);

	public boolean isInside(Vector3i point);

	public double getResidual(Vector2i localPoint);

	public double getResidual(Vector3i localPoint);

}
