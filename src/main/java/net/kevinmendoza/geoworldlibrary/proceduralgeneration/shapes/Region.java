package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;

public interface Region {

	public int getInt(int i);
	public double getDouble();
	
	public boolean isInside(Vector2i vec);
	public Vector2i getRandomInternalPoint();
	
	public double getNormalizedDistanceToEdge(Vector2i vec);

	public int hashCode();
	public String toString();

}
