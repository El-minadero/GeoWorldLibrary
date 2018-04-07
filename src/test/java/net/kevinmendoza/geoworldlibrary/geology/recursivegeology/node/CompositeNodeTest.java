package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.ICache;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.ICacheFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.INodeCache;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.LatticeCacheBuilder;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.NodeCacheBuilder;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.IData;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.IPointGenerator;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.PointGeneratorFactory;

public class CompositeNodeTest {
	
	private static Vector2i center = new Vector2i(0,0);
	private static Vector2i inside = new Vector2i(5,5);
	private static Vector2i outside= new Vector2i(11,11);
	private static Vector2i veryMuchOutside= new Vector2i(100,100);
	private static INodeCache nodeCache;
	private static INode	prototype;

	@BeforeAll
	public static void setup() {
		ICacheFactory factory 		= new NodeCacheFactory();
		IPointGenerator generator 	= PointGeneratorFactory
				.makeSinglePointLattice(0, 10, 0);
		ICache cache = new LatticeCacheBuilder()
				.setFactory(factory)
				.setPointGenerator(generator)
				.build();
		prototype 				= new PrototypeBuilder().build();
		nodeCache = new NodeCacheBuilder().addCache(cache).build();
	}
	@Test
	public void type() throws Exception {
		assertNotNull(Node.class);
	}

	@Test
	public void instantiation() throws Exception {
		INode node = new NodeBuilder()
				.setPrototype(prototype)
				.setCache(nodeCache)
				.build();
		assertNotNull(node);
	}

	@Test
	public void isLeaf_A$() throws Exception {
		INode node = new NodeBuilder()
				.setPrototype(prototype)
				.setCache(nodeCache)
				.build();
		assertTrue(!node.isLeaf());
	}

	@Test
	public void isInside_A$Vector2i() throws Exception {
		INode node = new NodeBuilder()
				.setPrototype(prototype)
				.setCache(nodeCache)
				.build();
		assertTrue(node.isInside(inside));
		assertTrue(node.isInside(center));
		assertTrue(!node.isInside(outside));
		assertTrue(!node.isInside(veryMuchOutside));
	}

	@Test
	public void getCenterDistance_A$Vector2i() throws Exception {
		INode node = new NodeBuilder()
				.setPrototype(prototype)
				.setCache(nodeCache)
				.build();
		double dist1 = node.getCenterDistance(veryMuchOutside);
		double dist2 = center.distance(veryMuchOutside);
		assertTrue(dist1-dist2<0.0001);
	}

	@Test
	public void getData_A$Vector2i() throws Exception {
		INode node = new NodeBuilder()
				.setPrototype(prototype)
				.setCache(nodeCache)
				.build();
		IData insideData = node.getData(inside);
		assertTrue(insideData.getWeight()-1<0.001);
	}
	
	@Test
	public void getData2_A$Vector2i() throws Exception {
		INode node = new NodeBuilder()
				.setPrototype(prototype)
				.setCache(nodeCache)
				.build();
		IData insideData = node.getData(outside);
		assertTrue(insideData.getWeight() < 0.9 && insideData.getWeight() > 0.2);
	}
	
	@Test
	public void getData3_A$Vector2i() throws Exception {
		INode node = new NodeBuilder()
				.setPrototype(prototype)
				.setCache(nodeCache)
				.build();
		IData insideData = node.getData(veryMuchOutside);
		assertTrue(insideData.getWeight() < 0.001);
	}
}
