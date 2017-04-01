package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public interface Region extends RegionPointGenerator {

	public int getInt(int i);
	public double getDouble();
	
	public Vector2i getModifiedPoint(Vector2i vec);
	public Vector3i getModifiedPoint(Vector3i vec);
	
	public double getNormalizedDistanceToEdge(Vector2i vec);
	public double getNormalizedDistanceToEdge(Vector3i vec);

	public int hashCode();
	public String toString();
	public Vector2i getCenter();

}
