package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node.INode;

public interface INodeCache {
	/**
	 * Given a global coordinate, return a list of closest sub object keys
	 * @param globalVector
	 * @return List of Object Keys
	 */
	public List<Vector2i> getLocalKeys(Vector3i globalVec);
	/**
	 * returns a list of keys that should be built according to the generator, but have not been.
	 * 
	 * @param list of keys to check
	 * @return keys which are not a part of the local cache
	 */
	List<Vector2i> getMissingNeighborKeys(List<Vector2i> neighborhoodKeyList);
	
	/**
	 * given a global coordinate, returns a list of neighboring keys
	 * @param globalCoordinateVector
	 * @return list of Vector2i keys corresponding to FLOORED coordinates
	 */
	public List<Vector2i> getLocalKeys(Vector2i globalVec);
	
	/**
	 * get List of all Nodes in the cache
	 * @return list of IGeologyNode members of cache
	 */
	public List<INode> getMemberList();
	
	/**
	 * get List of all Vector2i keys in the cache
	 * @return list of Vector2i keys corresponding to the cache's KeySet()
	 */
	public List<Vector2i> getKeyList();

	/**
	 *  add a geology node to a specific vector key
	 * @param FlooredKeyVector a vector corresponding to FLOORED key coordinates
	 * @param geologyNode the IGeologyNode to place at the key
	 */
	void addKeyValuePair(Vector2i FlooredKey, INode geologyNode);
	/**
	 *  add an empty key binding to the cache
	 * @param the key to place the binding at
	 */
	void addNullValueToKey(Vector2i flooredKey);
	/**
	 * Given a key, make a prototype based on the inner factory method
	 * Stores and returns the same IGeologyNode object
	 * @param flooredKey
	 * @return IGeologyNode object
	 */
	INode populateKeyValue(Vector2i flooredKey);

	/**
	 * returns a list of regions in which the globalVector is located inside. Will only search through specified
	 * key list
	 * @param surroundingFlooredKeys a list of Vector2i corresponding to Floored Vector coordinates from which to check
	 * @param globalVec the vector to check inside regions
	 * @return a list of IGeologyNodes who's regions overlap with the globalVector
	 */
	public List<INode> getInternalRegionsFromKeyList(List<Vector2i> surroundingFlooredKeys, Vector2i globalVec);
	/**
	 * returns a list of regions in which the globalVector is not inside of. Will only search through specified
	 * key list
	 * @param surroundingFlooredKeys a list of Vector2i corresponding to Floored Vector coordinates from which to check
	 * @param globalVec the vector to check inside regions
	 * @return a list of IGeologyNodes who's regions do not overlap with the globalVector
	 */
	public List<INode> getExternalRegionsFromKeyList(List<Vector2i> surroundingFlooredKeys, Vector2i globalVec);
	/**
	 * returns a list of regions in which the globalVector is located inside. Will search entire Key list
	 * @param surroundingFlooredKeys a list of Vector2i corresponding to Floored Vector coordinates from which to check
	 * @param globalVec the vector to check inside regions
	 * @return a list of IGeologyNodes who's regions overlap with the globalVector
	 */
	public List<INode> getInternalRegions(Vector2i check);
	/**
	 * returns a list of regions in which the globalVector is not inside of. Will search entire key list
	 * @param surroundingFlooredKeys a list of Vector2i corresponding to Floored Vector coordinates from which to check
	 * @param globalVec the vector to check inside regions
	 * @return a list of IGeologyNodes who's regions do not overlap with the globalVector
	 */
	public List<INode> getExternalRegions(Vector2i check);
	/**
	 * Given a global coordinate, produce a human readable readout of member regions
	 * @param globalVector
	 * @return human readable string of member objects
	 */
	public String getLocationString(Vector3i globalVector);
	/**
	 * Given a global coordinate, return a list of 3 (or less) closest sub objects
	 * @param globalVector
	 * @return List of IGeologyNodes
	 */
	public List<INode> getClosestNodesToLocation(Vector3i globalVec);
	/**
	 * if cache needs to be pre-populated with objects, do it here.
	 * 
	 * 
	 */
	public void populateIfNecessary();
	public void setSeed(long seed);

}
