package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;

public interface Region {

	public int getInt(int i);
	public double getDouble();
	
	public double getCenterDistance(Vector2i test);
	public boolean isInside(Vector2i vec);
	public Vector2i getRandomInternalPoint();
	public double distanceToEdge(Vector2i vec);

	public int hashCode();
	public String toString();

}
