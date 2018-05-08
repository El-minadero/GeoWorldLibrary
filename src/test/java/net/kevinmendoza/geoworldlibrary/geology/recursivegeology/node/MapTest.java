
package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.ICache;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.ICacheFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.INodeCache;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.LatticeCacheBuilder;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.NodeCacheBuilder;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.IPointGenerator;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.PointGeneratorFactory;

public class MapTest {

	private static INodeCache nodeCache;
	private static final Vector2i inside = new Vector2i(0,0);
	private static final Vector2i outside = new Vector2i(6,6);
	

	@BeforeAll
	public static void setUp() {
		ICacheFactory factory 		= new NodeCacheFactory();
		IPointGenerator generator 	= PointGeneratorFactory
				.makeSinglePointLattice(0, 10, 0);
		ICache cache = new LatticeCacheBuilder()
				.setFactory(factory)
				.setPointGenerator(generator)
				.build();
		nodeCache = new NodeCacheBuilder().addCache(cache).build();
	}
	@Test
	public void type() throws Exception {
		assertNotNull(Map.class);
	}

	@Test
	public void instantiation() throws Exception {
		IGeology target = new MapBuilder().setCache(nodeCache).build();
		assertNotNull(target);
	}

	@Test
	public void getBaseData1_AVector2i() throws Exception {
		IGeology target = new MapBuilder().setCache(nodeCache).build();
		double data = target.getData(inside).getWeight();
		assertTrue(data-1< 0.001,"data should be less than 0.9, but greater than 0.2. is:" + data);
	}
	
	@Test
	public void getBaseData2_AVector2i() throws Exception {
		IGeology target = new MapBuilder().setCache(nodeCache).build();
		double data = target.getData(outside).getWeight();
		assertTrue(data < 0.9 && data> 0.2,"data should be less than 0.9, but greater than 0.2. is:" + data);
	}

	

}
