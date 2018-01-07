package net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration;

import java.util.List;
import java.util.Set;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public interface IPointGenerator {
	
	/**
	 *  Returns a list of spaced centers surrounding the query point in a moore neighborhood.
	 * @param vec, a Vector2i object representing the query point.
	 * @return a list of centers with 2i global coordinates.
	 */
	public Set<Vector2i> getNeighborhood(Vector2i vec);
	
	/**
	 *  Returns a list of centers floored by the spacing in a moore neighborhood.
	 * @param vec, a Vector2i object representing the query point.
	 * @return a list of centers with 2i global coordinates.
	 */
	public Set<Vector2i> getNeighborhoodKeys(Vector2i vec);
	
	public Vector2i turnKeyToPoint(Vector2i vec);

	public int getRGBDebugVal(Vector3i query);

	public void setSeed(long seed);
	
}
