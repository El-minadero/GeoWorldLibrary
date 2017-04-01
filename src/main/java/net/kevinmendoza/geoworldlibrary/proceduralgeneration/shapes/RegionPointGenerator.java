package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public interface RegionPointGenerator {

	public Vector2i getRandomInternalPoint();
	
	public boolean isInside(Vector2i vec);
	public boolean isInside(Vector3i vec);
	
	public int hashCode();
	public boolean equals(Object o);
}
