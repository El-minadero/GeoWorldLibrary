package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public interface Region  {

	public int getInt(int i);
	public double getDouble();
	
	public double getNormalizedDistanceToEdge(Vector2i vec);
	public double getNormalizedDistanceToEdge(Vector3i vec);

	public boolean isOnEdge(Vector3i vec);
	public boolean isOnEdge(Vector2i vec);
	
	public int hashCode();
	public String toString();
	public Vector2i getCenter2i();
	public Vector3i getCenter3i();
	
	public Vector2i getRandomInternalPoint2i();
	public Vector3i getRandomInternalPoint3i();
	
	public boolean isInside(Vector2i vec);
	public boolean isInside(Vector3i vec);
	
	public boolean equals(Object o);
	
	

}
