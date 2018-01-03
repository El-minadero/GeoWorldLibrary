package net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration;

import java.util.HashMap;
import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.probability.DistributionFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.probability.IProbability;

public class MultiPointLatticeGeneratorTest {

	
	private static final long seed = 10;
	private static final int spacing = 10;
	private static final double ratio1 = 0;
	private static final double ratio2 = 0.5;
	private static final double ratio3 = 1;
	private static IProbability one;
	private static IProbability zero;
	private static IProbability gaussianSkew;
	private static final Vector2i center = new Vector2i(0,0);
	private static HashMap<Integer, Vector2i> centers;
	
	@BeforeAll
	static void setup() {
		one 			= DistributionFactory.BuildUniformDistribution(seed, 1, 1);
		zero			= DistributionFactory.BuildUniformDistribution(seed, 0, 0);
		gaussianSkew	= DistributionFactory.BuildNormalDistribution(seed, 0, 10, 1, 1);
		centers = new HashMap<Integer,Vector2i>();
		int count = 0;
		for(int x = -1;x<=1;x++) {
			for(int y = -1;y<=1;y++) {
				centers.put(count,new Vector2i(x*spacing,y*spacing));
				count++;
			}
		}
	}
	
	private boolean isWithinBounds(Set<Vector2i> target,Vector2i center) {
		boolean isInside=false;
		for(Vector2i tar : target) {
			if(isInside(center,tar)) { isInside = true; }
		}
		return isInside;
	}
	
	private boolean isInside(Vector2i origin,Vector2i target) {
		int x = target.getX();
		int y = target.getY();
		int x0 = origin.getX();
		int y0 = origin.getY();
		boolean b = (Math.abs(x - x0) <= spacing && Math.abs(y - y0) <= spacing);
		return b;
	}
	
	
	@Test
	public void type() throws Exception {
		assertNotNull(MultiPointLatticeGenerator.class);
	}

	@Test
	public void instantiation() throws Exception {
		IPointGenerator target = PointGeneratorFactory.makeMultiPointLattice(seed, spacing, ratio1, zero);
		assertNotNull(target);
	}

	@Test
	public void testCloudIsZero() throws Exception {
		Vector2i source        = centers.get(4);
		IPointGenerator target = PointGeneratorFactory.makeMultiPointLattice(seed, spacing, ratio2, zero);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside  = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio3;
		Assertions.assertFalse(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio3_Vector2i_0() throws Exception {
		Vector2i source        = centers.get(0);
		IPointGenerator target = PointGeneratorFactory.makeMultiPointLattice(seed, spacing, ratio3, one);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio3;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio3_Vector2i_1() throws Exception {
		Vector2i source        = centers.get(1);
		IPointGenerator target = PointGeneratorFactory.makeMultiPointLattice(seed, spacing, ratio1, one);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio3;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_multipleNeighborsRatio1_Vector2i_1() throws Exception {
		Vector2i source        = centers.get(3);
		IPointGenerator target = PointGeneratorFactory.makeMultiPointLattice(seed, spacing, ratio1, gaussianSkew);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside  = multipleInBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio1;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_multipleNeighborsRatio2_Vector2i_1() throws Exception {
		Vector2i source        = centers.get(3);
		IPointGenerator target = PointGeneratorFactory.makeMultiPointLattice(seed, spacing, ratio2, gaussianSkew);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside  = multipleInBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio2;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_multipleNeighborsRatio3_Vector2i_1() throws Exception {
		Vector2i source        = centers.get(3);
		IPointGenerator target = PointGeneratorFactory.makeMultiPointLattice(seed, spacing, ratio3, gaussianSkew);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside  = multipleInBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio3;
		Assertions.assertTrue(targetInside,msg);
	}

	private boolean multipleInBounds(Set<Vector2i> targets, Vector2i source) {
		int count=0;
		for(Vector2i tar : targets) {
			if(isInside(center,tar)) { count++; }
		}
		if(count > 1) {
			return true;
		}
		return false;
	}
	
	
}
