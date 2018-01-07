
package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.xml.transform.Source;

import org.junit.jupiter.api.Test;
import org.spongepowered.api.statistic.StatisticFormat;

import com.flowpowered.math.vector.Vector2i;

import org.apache.commons.math3.exception.InsufficientDataException;
import org.junit.jupiter.api.BeforeAll;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.ICache;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.ICacheFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.INodeCache;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.LatticeCacheBuilder;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.NodeCacheBuilder;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.IData;
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
	public void getBaseData1_A$Vector2i() throws Exception {
		IGeology target = new MapBuilder().setCache(nodeCache).build();
		double data = target.getData(inside).get();
		assertTrue(data-1< 0.001);
	}
	
	@Test
	public void getBaseData2_A$Vector2i() throws Exception {
		IGeology target = new MapBuilder().setCache(nodeCache).build();
		double data = target.getData(outside).get();
		assertTrue(data < 0.9 && data> 0.2);
	}

	

}
