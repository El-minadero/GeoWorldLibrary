package net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.probability.DistributionFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.probability.IProbability;

public class PointCloudTest {
	
	private static final long seed = 10;
	private static final int spacing = 10;
	private static final double ratio1 = 0;
	private static final double ratio2 = 0.5;
	private static final double ratio3 = 1;
	private static IProbability one;
	private static IProbability zero;
	private static IProbability gaussianSkew;
	
	@BeforeAll
	protected static void setup() {
		one 			= DistributionFactory.BuildUniformDistribution(seed, 1, 1);
		zero			= DistributionFactory.BuildUniformDistribution(seed, 0, 0);
		gaussianSkew	= DistributionFactory.BuildNormalDistribution(seed, 0, 10, 1, 1);
	}
	
	@Test
	public void test_type() throws Exception {
		assertNotNull(SinglePointLatticeGenerator.class);
	}

	@Test
	public void test_instantiation() throws Exception {
		IPointCloud target = PointGeneratorFactory.makePointCloud(seed, spacing, ratio1, zero);
		assertNotNull(target);
	}

	@Test
	public void testZeroPointCloudRatio1() throws Exception {
		IPointCloud cloud = PointGeneratorFactory.makePointCloud(seed, spacing, ratio1, zero);
		Set<Vector2i> vecSet = cloud.generatePointCloud();
		assertEquals(vecSet.size(), 0);
	}
	
	@Test
	public void testZeroPointCloudRatio2() throws Exception {
		IPointCloud cloud = PointGeneratorFactory.makePointCloud(seed, spacing, ratio2, zero);
		Set<Vector2i> vecSet = cloud.generatePointCloud();
		assertEquals(vecSet.size(), 0);
	}
	
	@Test
	public void testZeroPointCloudRatio3() throws Exception {
		IPointCloud cloud = PointGeneratorFactory.makePointCloud(seed, spacing, ratio3, zero);
		Set<Vector2i> vecSet = cloud.generatePointCloud();
		assertEquals(vecSet.size(), 0);
	}
	
	@Test
	public void testOnePointCloudRatio1() throws Exception {
		IPointCloud cloud = PointGeneratorFactory.makePointCloud(seed, spacing, ratio1, one);
		Set<Vector2i> vecSet = cloud.generatePointCloud();
		assertEquals(vecSet.size(), 1);
	}
	
	@Test
	public void testOnePointCloudRatio2() throws Exception {
		IPointCloud cloud = PointGeneratorFactory.makePointCloud(seed, spacing, ratio2, one);
		Set<Vector2i> vecSet = cloud.generatePointCloud();
		assertEquals(vecSet.size(), 1);
	}
	
	@Test
	public void testOnePointCloudRatio3() throws Exception {
		IPointCloud cloud = PointGeneratorFactory.makePointCloud(seed, spacing, ratio3, one);
		Set<Vector2i> vecSet = cloud.generatePointCloud();
		assertEquals(vecSet.size(), 1);
	}
	
	@Test
	public void testWithinBoundsRatio1() throws Exception {
		IPointCloud cloud = PointGeneratorFactory.makePointCloud(seed, spacing, ratio1, gaussianSkew);
		for(int i =0;i<10;i++) {
			Set<Vector2i> vector2is = cloud.generatePointCloud();
			boolean inBounds = isInBounds(vector2is);
			Assertions.assertTrue(inBounds,"Vectors not in bounds for ratio:" + ratio1);
		}
	}
	private boolean isInBounds(Set<Vector2i> vector2is) {
		boolean inside = true;
		for(Vector2i vec : vector2is) {
			int x = vec.getX();
			int y = vec.getY();
			if( x <0 || x > spacing || y<0 || y > spacing) {
				inside = false;
			}
		}
		return inside;
	}

	@Test
	public void testWithinBoundsRatio2() throws Exception {
		IPointCloud cloud = PointGeneratorFactory.makePointCloud(seed, spacing, ratio2, gaussianSkew);
		for(int i =0;i<10;i++) {
			Set<Vector2i> vector2is = cloud.generatePointCloud();
			boolean inBounds = isInBounds(vector2is);
			Assertions.assertTrue(inBounds,"Vectors not in bounds for ratio:" + ratio1);
		}
	}
	@Test
	public void testWithinBoundsRatio3() throws Exception {
		IPointCloud cloud = PointGeneratorFactory.makePointCloud(seed, spacing, ratio3, gaussianSkew);
		for(int i =0;i<10;i++) {
			Set<Vector2i> vector2is = cloud.generatePointCloud();
			boolean inBounds = isInBounds(vector2is);
			Assertions.assertTrue(inBounds,"Vectors not in bounds for ratio:" + ratio1);
		}
	}

}
