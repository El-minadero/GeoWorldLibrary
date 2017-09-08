package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public interface IConic {

	public boolean isInside(Vector2i point);

	public boolean isInside(Vector3i point);

	public double getResidual(Vector2i localPoint);

	public double getResidual(Vector3i localPoint);

	public double getRootMeanAxis();

	public double getInvRootMeanAxis();
}
