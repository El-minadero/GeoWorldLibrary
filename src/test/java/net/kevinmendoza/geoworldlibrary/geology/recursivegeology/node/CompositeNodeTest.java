package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.ICache;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.INodeCache;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.NodeCacheBuilder;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.IData;

public class CompositeNodeTest {
	
	private static Vector2i center = new Vector2i(0,0);
	private static Vector2i inside = new Vector2i(5,5);
	private static Vector2i outside= new Vector2i(11,11);
	private static Vector2i veryMuchOutside= new Vector2i(100,100);
	private static INode node;

	@BeforeAll
	public static void setup() {
		INode prototype = new PrototypeBuilder().build();
		ICache cache = new LatticeCacheBuilder()
		INodeCache nodeCache = new NodeCacheBuilder().addCache(cache)
		node = new NodeBuilder()
				.setPrototype(prototype)
				.set
	}
	@Test
	public void type() throws Exception {
		assertNotNull(Node.class);
	}

	@Test
	public void instantiation() throws Exception {
		NodeBuilder builder = new NodeBuilder();
		INode node = builder.build();
		assertNotNull(node);
	}

	@Test
	public void isLeaf_A$() throws Exception {
		NodeBuilder builder = new NodeBuilder();
		INode node = builder.build();
		assertTrue(!node.isLeaf());
	}

	@Test
	public void isInside_A$Vector2i() throws Exception {
		NodeBuilder builder = new NodeBuilder();
		INode node = builder.build();
		assertTrue(node.isInside(inside));
		assertTrue(node.isInside(center));
		assertTrue(!node.isInside(outside));
		assertTrue(!node.isInside(veryMuchOutside));
	}

	@Test
	public void getCenterDistance_A$Vector2i() throws Exception {
		NodeBuilder builder = new NodeBuilder();
		INode node = builder.build();
		double dist1 = node.getCenterDistance(veryMuchOutside);
		double dist2 = center.distance(veryMuchOutside);
		assertTrue(dist1-dist2<0.0001);
	}

	@Test
	public void getData_A$Vector2i() throws Exception {
		NodeBuilder builder = new NodeBuilder();
		INode node 	= builder.build();
		IData insideData = node.getData(inside);
		assertTrue(insideData.get()-1<0.001);
	}
	
	@Test
	public void getData2_A$Vector2i() throws Exception {
		NodeBuilder builder = new NodeBuilder();
		INode node 	= builder.build();
		IData insideData = node.getData(outside);
		assertTrue(insideData.get() < 0.9 && insideData.get() > 0.2);
	}
	
	@Test
	public void getData3_A$Vector2i() throws Exception {
		NodeBuilder builder = new NodeBuilder();
		INode node 	= builder.build();
		IData insideData = node.getData(veryMuchOutside);
		assertTrue(insideData.get() < 0.001);
	}
}
