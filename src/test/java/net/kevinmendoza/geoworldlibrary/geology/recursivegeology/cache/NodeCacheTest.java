package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.IPointCloud;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.IPointGenerator;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.PointGeneratorFactory;
public class NodeCacheTest {

	private static NodeCacheBuilder nodeCacheBuilder;
	private static LatticeCacheBuilder latticeCacheBuilder;
	private static CloudCacheBuilder cloudCacheBuilder;
	private static int spacing;
	private static double searchRadius;
	private static Vector3i center;

	@BeforeAll
	static void doThings() {
		spacing 			= 10;
		searchRadius 	= 50;
		center 			= new Vector3i(0,0,0);
		ICacheFactory cloudFactory 	= new NullPrototypeFactory();
		ICacheFactory latticeFactory	= new NullLatticePrototypeFactory(spacing);
		IPointGenerator pointGenerator = PointGeneratorFactory.makeSinglePointLattice(10, spacing, 0);
		IPointCloud		pointCloud 	= new NullCloudTest();
		
		latticeCacheBuilder 	= new LatticeCacheBuilder();
		cloudCacheBuilder	= new CloudCacheBuilder();
		
		latticeCacheBuilder.setFactory(latticeFactory);
		latticeCacheBuilder.setPointGenerator(pointGenerator);
		
		cloudCacheBuilder.setFactory(cloudFactory);
		cloudCacheBuilder.setPointCloudGenerator(pointCloud);
		cloudCacheBuilder.setSearchRadius(searchRadius);
		cloudCacheBuilder.setCenter(center);
		
		nodeCacheBuilder 	= new NodeCacheBuilder();
		nodeCacheBuilder.addCache(latticeCacheBuilder.build());
		nodeCacheBuilder.addCache(cloudCacheBuilder.build());
		
	}
	
	@Test
	public void type() throws Exception {
		assertThat(NodeCache.class, notNullValue());
	}

	@Test
	public void instantiation() throws Exception {
		INodeCache cache = nodeCacheBuilder.build();
		assertThat(cache, notNullValue());
	}

	@Test
	public void loadNodes_A$Vector2i() throws Exception {
		INodeCache cache = nodeCacheBuilder.build();
		cache.loadNodes(center);
		Set<INodeRegion> nodeset = new HashSet<>();
		nodeset.addAll(cache.getNodes());
		nodeset.addAll(cache.getNodes());
		assertTrue(!nodeset.isEmpty(),"NO NODES!?");
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
