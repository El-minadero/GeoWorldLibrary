package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.IPointGenerator;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.PointGeneratorFactory;
public class LatticeCacheTest {
	
	private static final Vector3i  center3 = new Vector3i(0,0,0);
	private static final Vector2i  center2 = new Vector2i(0,0);
	private static Set<Vector2i>  neighborhood;
	private static final int spacing = 10;
	private static final IPointGenerator GENERATOR = PointGeneratorFactory
			.makeSinglePointLattice(10, spacing, 0);
	private static LatticeCacheBuilder builder;
	private static NullLatticePrototypeFactory factory;
	
	@BeforeAll
	static void doThings() {
		factory = new NullLatticePrototypeFactory(spacing);
		builder = new LatticeCacheBuilder();
		builder.setFactory(factory);
		builder.setPointGenerator(GENERATOR);
		neighborhood = GENERATOR.getNeighborhoodKeys(center2);
	}
	
	@Test
	public void test_local_keys() throws Exception {
		ICache target = new LatticeCache(builder);
		Set<Vector2i> keySet = target.getLocalKeys(center3);
		assertEquals(neighborhood,keySet,"Sets Not Equal each other! Should be 4 but size is:" + keySet.size());
	}
	
	@Test
	public void test_Internal_keys() throws Exception {
		ICache target = new LatticeCache(builder);
		target.getLocalKeys(center3);
		Set<Vector2i> keySet = target.getLocalKeys(center3);
		Set<INodeRegion> internals = target.getInternalRegionsFromKeySet(keySet, center2);
		assertTrue(internals.size()==1, "Set is a little bigger");
	}
	
	@Test
	public void test_External_keys() throws Exception {
		ICache 		target = new LatticeCache(builder);
		target.getLocalKeys(center3);
		Set<Vector2i> keySet = target.getLocalKeys(center3);
		Set<INodeRegion> externals = target.getExternalRegionsFromKeySet(keySet, center2);
		assertTrue(externals.size()==keySet.size()-1, "Set Contains A little more than expected");
	}

}
