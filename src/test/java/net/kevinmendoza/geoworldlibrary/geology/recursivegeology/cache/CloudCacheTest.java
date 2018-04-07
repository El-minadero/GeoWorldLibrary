package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.IPointCloud;
public class CloudCacheTest {
	
	private static final Vector3i  center3 = new Vector3i(0,0,0);
	private static final Vector2i  center2 = new Vector2i(0,0);
	private static final IPointCloud nullCloudTest = new NullCloudTest();
	private static CloudCacheBuilder builder;
	private static NullPrototypeFactory factory;
	private static Set<Vector2i> cloud = new HashSet<>();
	
	@BeforeAll
	static void doThings() {
		cloud.add(new Vector2i(0,0));
		cloud.add(new Vector2i(10,0));
		cloud.add(new Vector2i(0,10));
		cloud.add(new Vector2i(10,10));
		cloud.add(new Vector2i(100,100));
		factory = new NullPrototypeFactory();
		builder= new CloudCacheBuilder();
		builder.setFactory(factory);
		builder.setPointCloudGenerator(nullCloudTest);
		builder.setCenter(center3);
		builder.setSearchRadius(50);
	}

	@Test
	public void type() throws Exception {
		assertNotNull(CloudCache.class);
	}

	@Test
	public void instantiation() throws Exception {
		ICache target = new CloudCache(builder);
		assertNotNull(target);
	}
	
	@Test
	public void test_local_keys() throws Exception {
		ICache target = new CloudCache(builder);
		Set<Vector2i> keySet = target.getLocalKeys(center3);
		
		assertTrue(keySet.size()==4,"Sets Not Equal each other! Should be 4 but size is:" + keySet.size());
	}
	
	@Test
	public void test_Internal_keys() throws Exception {
		ICache target = new CloudCache(builder);
		target.getLocalKeys(center3);
		Set<Vector2i> keySet = target.getKeys();
		Set<INodeRegion> internals = target.getInternalRegionsFromKeySet(keySet, center2);
		assertTrue(internals.size()==1, "Set is a little bigger");
	}
	
	@Test
	public void test_External_keys() throws Exception {
		ICache 		target = new CloudCache(builder);
		target.getLocalKeys(center3);
		Vector2i 	center = new Vector2i(0,0);
		Set<Vector2i> keySet = target.getKeys();
		Set<INodeRegion> externals = target.getExternalRegionsFromKeySet(keySet, center);
		assertTrue(externals.size()==keySet.size()-1, "Set Contains A little more than expected");
	}

	private static class NullCloudTest implements IPointCloud {

		@Override
		public Set<Vector2i> generatePointCloud() {
			Set<Vector2i> cloud = new HashSet<>();
			cloud.add(new Vector2i(0,0));
			cloud.add(new Vector2i(10,0));
			cloud.add(new Vector2i(0,10));
			cloud.add(new Vector2i(10,10));
			cloud.add(new Vector2i(100,100));
			return cloud;
		}

		public void setSeed(long seed) { }
		
	}
	

}
