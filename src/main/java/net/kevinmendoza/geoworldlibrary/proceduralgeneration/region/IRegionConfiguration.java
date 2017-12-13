package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public interface IRegionConfiguration {

	/**
	 * returns an IRegion object based on input seed. Note that the region will center at the origin
	 * @param seed a long seed to create the dimensions
	 * @return an IRegion object
	 */
	IRegion getRegionFromSeed(long seed);
	/**
	 * returns an IRegion object with it's dimensions created from the vector hash operation. 
	 * center of the object will be at the passed vector;
	 * @param vec the region center and seed object
	 * @return an IRegion object
	 */
	IRegion getRegionFromVector2i(Vector2i vec);
	/**
	 * returns an IRegion object with it's dimensions created from the vector hash operation. 
	 * center of the object will be at the passed vector;
	 * @param vec the region center and seed object
	 * @return an IRegion object
	 */
	IRegion getRegionFromVector3i(Vector3i vec);
	/**
	 * returns an IRegion object with it's dimensions created from the passed seed convolved
	 * with the passed coordinate. 
	 *
	 * @param vec the region center 
	 * @param seed a seed number to convolve with vector hash code;
	 * @return an IRegion object
	 */
	IRegion getRegionFromVector2i(Vector2i vec,long seed);
	/**
	 * returns an IRegion object with it's dimensions created from the passed seed convolved
	 * with the passed coordinate. 
	 *
	 * @param vec the region center 
	 * @param seed a seed number to convolve with vector hash code;
	 * @return an IRegion object
	 */
	IRegion getRegionFromVector3i(Vector3i vec,long seed);
	
}
