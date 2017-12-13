package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.relativelocation;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public interface IPoint {

	public Vector2i getCenter2i();
	
	public Vector3i getCenter3i();
	
	public double getDistanceToCenter(Vector3i vec);
	
	public double getDistanceToCenter(Vector2i vec);
}
