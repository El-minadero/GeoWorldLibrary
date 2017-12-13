package net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration;

import java.util.List;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public interface IPointGenerator {

	/**
	 *  Returns a list of centers offset by a seed and surrounding the query point in roughly a moore neighborhood.
	 * @param vec, a Vector2i object representing the query point.
	 * @return a list of offset centers.
	 */
	public List<Vector2i> getFlooredCenterNeighborhood(Vector2i vec);
	
	/**
	 *  Returns a list of spaced centers surrounding the query point in a moore neighborhood.
	 * @param vec, a Vector2i object representing the query point.
	 * @return a list of centers with coordinates corresponding to spacing multiples.
	 */
	public List<Vector2i> getFullCenterNeighborhood(Vector2i vec);
	
	/**
	 *  Returns a center fully realized by both seed and spacing based on a spaced point.
	 * @param vec, a Vector2i object representing a spaced query point.
	 * @return a single realized center.
	 */
	public Vector2i getFullCenter(Vector2i vec);

	public int getRGBDebugVal(Vector3i query);

	public void setSeed(long seed);

	public boolean shouldBuild(Vector2i vec);
	
}
