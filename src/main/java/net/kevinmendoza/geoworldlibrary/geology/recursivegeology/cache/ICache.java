package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache;

import java.util.Collection;
import java.util.Set;
import java.util.Set;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;
import com.flowpowered.math.vector.Vectori;

public interface ICache {
	/**
	 * Given a global coordinate, return a list of closest sub object keys
	 * @param globalVector
	 * @return List of Object Keys
	 */
	public Set<Vector2i> getLocalKeys(Vector3i globalVec);
	
	/**
	 * given a global coordinate, returns a list of neighboring keys
	 * @param globalCoordinateVector
	 * @return list of Vector2i keys corresponding to FLOORED coordinates
	 */
	public Set<Vector2i> getLocalKeys(Vector2i globalVec);
	/**
	 * Given a global coordinate, return a list of sub object keys
	 * @param globalVector
	 * @return List of Object Keys
	 */
	public Set<Vector2i> getKeys();
	
	/**
	 * returns a Set of regions in which the globalVector is located inside. Will only search through specified
	 * key Set
	 * @param surroundingFlooredKeys a Set of Vector2i corresponding to Floored Vector coordinates from which to check
	 * @param globalVec the vector to check inside regions
	 * @return a Set of IGeologyNodes who's regions overlap with the globalVector
	 */
	public Set<INodeRegion> getInternalRegionsFromKeySet(Set<Vector2i> surroundingFlooredKeys, Vector2i globalVec);
	/**
	 * returns a Set of regions in which the globalVector is not inside of. Will only search through specified
	 * key Set
	 * @param surroundingFlooredKeys a Set of Vector2i corresponding to Floored Vector coordinates from which to check
	 * @param globalVec the vector to check inside regions
	 * @return a Set of IGeologyNodes who's regions do not overlap with the globalVector
	 */
	public Set<INodeRegion> getExternalRegionsFromKeySet(Set<Vector2i> surroundingFlooredKeys, Vector2i globalVec);
	/**
	 * returns a Set of regions in which the globalVector is located inside. Will search entire Key Set
	 * @param surroundingFlooredKeys a Set of Vector2i corresponding to Floored Vector coordinates from which to check
	 * @param globalVec the vector to check inside regions
	 * @return a Set of IGeologyNodes who's regions overlap with the globalVector
	 */
	public Set<INodeRegion> getInternalRegions(Vector2i check);
	/**
	 * returns a Set of regions in which the globalVector is not inside of. Will search entire key Set
	 * @param surroundingFlooredKeys a Set of Vector2i corresponding to Floored Vector coordinates from which to check
	 * @param globalVec the vector to check inside regions
	 * @return a Set of IGeologyNodes who's regions do not overlap with the globalVector
	 */
	public Set<INodeRegion> getExternalRegions(Vector2i check);
	/**
	 * returns a Set of regions in which the globalVector is located inside. Will search entire Key Set
	 * @param surroundingFlooredKeys a Set of Vector3i corresponding to Floored Vector coordinates from which to check
	 * @param globalVec the vector to check inside regions
	 * @return a Set of IGeologyNodes who's regions overlap with the globalVector
	 */
	public Set<INodeRegion> getInternalRegions(Vector3i check);
	/**
	 * returns a Set of regions in which the globalVector is not inside of. Will search entire key Set
	 * @param surroundingFlooredKeys a Set of Vector3i corresponding to Floored Vector coordinates from which to check
	 * @param globalVec the vector to check inside regions
	 * @return a Set of IGeologyNodes who's regions do not overlap with the globalVector
	 */
	public Set<INodeRegion> getExternalRegions(Vector3i check);
	/**
	 * Given a global coordinate, produce a human readable readout of member regions
	 * @param globalVector
	 * @return human readable string of member objects
	 */
	public String getLocationString(Vector3i globalVector);
	/**
	 * Given a global coordinate, return a Set of 3 (or less) closest sub objects
	 * @param globalVector
	 * @return Set of IGeologyNodes
	 */
	public Set<INodeRegion> getClosestNodesToLocation(Vector3i globalVec);
	
	public void setSeed(long seed);
}
