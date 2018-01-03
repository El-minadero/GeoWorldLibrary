package net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration;


import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.SinglePointLatticeGenerator.*;

import java.awt.Color;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import junit.framework.TestCase;
import net.kevinmendoza.geoworldlibrary.utilities.HashCodeOperations;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class SinglePointLatticeGeneratorTest extends TestCase {

	private static final int spacing = 10;
	private static final int ratio1 = 0;
	private static final int ratio2 = 1;
	private static final double ratio3 = 0.5;
	private static final long seed1 = 10;
	private static final long seed2 = 20;
	private static final Vector2i center = new Vector2i(0,0);
	private static HashMap<Integer,Vector2i> centers;

	@BeforeAll
	protected static void set() {
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
	public void test_type() throws Exception {
		assertNotNull(SinglePointLatticeGenerator.class);
	}

	@Test
	public void test_instantiation() throws Exception {
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(10, 10, 1);
		assertNotNull(target);
	}

	@Test
	public void test_inNeighborhood_Ratio1_Vector2i_0() throws Exception {
		Vector2i source        = centers.get(0);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio1);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio1;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio1_Vector2i_1() throws Exception {
		Vector2i source        = centers.get(1);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio1);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio1;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio1_Vector2i_2() throws Exception {
		Vector2i source        = centers.get(2);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio1);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio1;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio1_Vector2i_3() throws Exception {
		Vector2i source        = centers.get(3);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio1);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio1;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio1_Vector2i_4() throws Exception {
		Vector2i source        = centers.get(4);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio1);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio1;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio1_Vector2i_5() throws Exception {
		Vector2i source        = centers.get(5);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio1);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio1;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio1_Vector2i_6() throws Exception {
		Vector2i source        = centers.get(6);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio1);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio1;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio1_Vector2i_7() throws Exception {
		Vector2i source        = centers.get(7);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio1);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio1;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio1_Vector2i_8() throws Exception {
		Vector2i source        = centers.get(8);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio1);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio1;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio2_Vector2i_0() throws Exception {
		Vector2i source        = centers.get(0);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio2);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio2;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio2_Vector2i_1() throws Exception {
		Vector2i source        = centers.get(1);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio2);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio2;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio2_Vector2i_2() throws Exception {
		Vector2i source        = centers.get(2);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio2);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio2;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio2_Vector2i_3() throws Exception {
		Vector2i source        = centers.get(3);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio2);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio2;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio2_Vector2i_4() throws Exception {
		Vector2i source        = centers.get(4);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio2);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio2;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio2_Vector2i_5() throws Exception {
		Vector2i source        = centers.get(5);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio2);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio2;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio2_Vector2i_6() throws Exception {
		Vector2i source        = centers.get(6);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio2);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio2;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio2_Vector2i_7() throws Exception {
		Vector2i source        = centers.get(7);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio2);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio2;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio2_Vector2i_8() throws Exception {
		Vector2i source        = centers.get(8);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio2);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio2;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio3_Vector2i_0() throws Exception {
		Vector2i source        = centers.get(0);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio3);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio3;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio3_Vector2i_1() throws Exception {
		Vector2i source        = centers.get(1);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio3);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio3;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio3_Vector2i_2() throws Exception {
		Vector2i source        = centers.get(2);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio3);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio3;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio3_Vector2i_3() throws Exception {
		Vector2i source        = centers.get(3);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio3);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio3;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio3_Vector2i_4() throws Exception {
		Vector2i source        = centers.get(4);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio3);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio3;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio3_Vector2i_5() throws Exception {
		Vector2i source        = centers.get(5);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio3);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio3;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio3_Vector2i_6() throws Exception {
		Vector2i source        = centers.get(6);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio3);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio3;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio3_Vector2i_7() throws Exception {
		Vector2i source        = centers.get(7);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio2);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio3;
		Assertions.assertTrue(targetInside,msg);
	}
	
	@Test
	public void test_inNeighborhood_Ratio3_Vector2i_8() throws Exception {
		Vector2i source        = centers.get(8);
		IPointGenerator target = PointGeneratorFactory.makeSinglePointLattice(seed1, spacing, ratio3);
		Set<Vector2i> targets = target.getNeighborhood(center);
		boolean  targetInside = isWithinBounds(targets,source);
		String msg = "Could not find point inside cell with origin: " + source.toString() + " and ratio: " + ratio3;
		Assertions.assertTrue(targetInside,msg);
	}
	
	
	
	

}
