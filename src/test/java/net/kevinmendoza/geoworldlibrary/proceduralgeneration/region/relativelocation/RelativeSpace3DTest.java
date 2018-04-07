package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.relativelocation;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public class RelativeSpace3DTest {
	private static final double ZERO = 0.001;
	private static Vector2i center2i = new Vector2i(5, 5);
	private static IRelativeSpace defaultModifier;
	private static IRelativeSpace modifier;
	private static Vector3i center3i;
	private static double cos;
	private static double sin;
	private static double cos_;
	private static double sin_;

	@BeforeAll
	public static void initialize() throws Exception {
		Random rand = new Random();
		;
		double angle = Math.toRadians(35);
		cos = Math.cos(angle);
		sin = Math.sin(angle);
		cos_ = Math.cos(-angle);
		sin_ = Math.sin(-angle);
		center2i = new Vector2i(5, 5);
		center3i = new Vector3i(5, 5, 5);
		defaultModifier = RelativeSpaceFactory.createRelative3DFrame(center3i, rand, false);
		modifier = RelativeSpaceFactory.createRelative3DFrame(center3i, 0, angle, 0);
	}

	private Vector2i rotateByAngle(Vector2i vec) {
		int x = (int) Math.round((vec.getX() * cos - vec.getY() * sin));
		int z = (int) Math.round((vec.getX() * sin + vec.getY() * cos));
		return new Vector2i(x, z);
	}

	private Vector3i rotateByAngle(Vector3i vec) {
		int x = (int) Math.round((vec.getX() * cos - vec.getZ() * sin));
		int z = (int) Math.round((vec.getX() * sin + vec.getZ() * cos));
		return new Vector3i(x, vec.getY(), z);
	}

	private Vector2i rotateInvAngle(Vector2i vec) {
		int x = (int) (vec.getX() * cos_ - vec.getY() * sin_);
		int z = (int) (vec.getX() * sin_ + vec.getY() * cos_);
		return new Vector2i(x, z);
	}

	private Vector3i rotateInvAngle(Vector3i vec) {
		int x = (int) (vec.getX() * cos_ - vec.getZ() * sin_);
		int z = (int) (vec.getX() * sin_ + vec.getZ() * cos_);
		return new Vector3i(x, vec.getY(), z);
	}

	@Test
	public void instantiation() throws Exception {
		assertNotNull(defaultModifier);
		assertNotNull(modifier);
	}

	@Test
	public void getGlobalPoint_A$Vector2i() throws Exception {
		Vector2i center = new Vector2i(0, 0);
		Vector2i off11 = new Vector2i(3, 3);
		Vector2i offrot = rotateByAngle(off11).add(center2i);
		Vector2i targetCenter = modifier.getGlobalPoint(center);
		Vector2i defaultTargetCenter = defaultModifier.getGlobalPoint(center);
		Vector2i targetRot = modifier.getGlobalPoint(off11);
		Vector2i defaultTargetRot = defaultModifier.getGlobalPoint(off11);

		assertEquals(center2i, targetCenter,"expected:" + center2i.toString() + ". was" + targetCenter.toString());
		assertEquals(center2i,defaultTargetCenter,"expected:" + center2i.toString() + ". was" + defaultTargetCenter.toString());
		assertEquals(defaultTargetCenter, targetCenter);

		assertEquals(offrot, targetRot,"expected:" + offrot.toString() + ". was" + targetRot.toString());
		assertEquals(off11.add(center2i), defaultTargetRot);

	}

	@Test
	public void getGlobalPoint_A$Vector3i() throws Exception {
		Vector3i center = new Vector3i(0, 0, 0);
		Vector3i off11 = new Vector3i(2, 2, 2);
		Vector3i offrot = rotateByAngle(off11).add(center3i);
		Vector3i targetCenter = modifier.getGlobalPoint(center);
		Vector3i defaultTargetCenter = defaultModifier.getGlobalPoint(center);
		Vector3i targetRot = modifier.getGlobalPoint(off11);
		Vector3i defaultTargetRot = defaultModifier.getGlobalPoint(off11);

		assertEquals(center3i, targetCenter,		 "expected:" + center3i.toString() + ". was" + 
		targetCenter.toString());
		assertEquals(center3i,defaultTargetCenter,"expected:" + center3i.toString() + ". was" + 
		defaultTargetCenter.toString());
		assertEquals(defaultTargetCenter, targetCenter);

		assertEquals(offrot, targetRot,"expected:" + offrot.toString() + ". was" + targetRot.toString());
		assertEquals(off11.add(center3i), defaultTargetRot);
	}

	@Test
	public void getLocalPoint_A$Vector2i() throws Exception {
		Vector2i center = new Vector2i(0, 0);
		Vector2i localoffset = new Vector2i(10, 10);

		Vector2i localCenter1 = modifier.getLocalPoint(center2i);
		Vector2i localCenter2 = defaultModifier.getLocalPoint(center2i);

		Vector2i globalPoint1 = modifier.getGlobalPoint(localoffset);
		Vector2i globalPoint2 = defaultModifier.getGlobalPoint(localoffset);

		Vector2i localPoint1 = modifier.getLocalPoint(globalPoint1);
		Vector2i localPoint2 = defaultModifier.getLocalPoint(globalPoint2);

		assertEquals(center, localCenter1,"expected:" + center.toString() + ". was" + localCenter1.toString());
		assertEquals(center, localCenter2,"expected:" + center.toString() + ". was" + localCenter2.toString());
		assertEquals(localPoint1, localoffset,"expected:" + localoffset.toString() + ". was" + localPoint1.toString());
		assertEquals(localPoint2, localoffset,"expected:" + localoffset.toString() + ". was" + localPoint2.toString());
	}

	@Test
	public void getLocalPoint_A$Vector3i() throws Exception {
		Vector3i center = new Vector3i(0, 0, 0);
		Vector3i localoffset = new Vector3i(10, 10, 10);

		Vector3i localCenter1 = modifier.getLocalPoint(center3i);
		Vector3i localCenter2 = defaultModifier.getLocalPoint(center3i);

		Vector3i globalPoint1 = modifier.getGlobalPoint(localoffset);
		Vector3i globalPoint2 = defaultModifier.getGlobalPoint(localoffset);

		Vector3i localPoint1 = modifier.getLocalPoint(globalPoint1);
		Vector3i localPoint2 = defaultModifier.getLocalPoint(globalPoint2);

		assertEquals(center, localCenter1,"expected:" + center.toString() + ". was" + localCenter1.toString());
		assertEquals(center, localCenter2,"expected:" + center.toString() + ". was" + localCenter2.toString());
		assertEquals(localPoint1, localoffset,"expected:" + localoffset.toString() + ". was" + localPoint1.toString());
		assertEquals(localPoint2, localoffset,"expected:" + localoffset.toString() + ". was" + localPoint2.toString());
	}

	@Test
	public void getCenter2i_A$() throws Exception {
		Vector2i centerTarget = modifier.getCenter2i();
		Vector2i defaultCenterTarget = defaultModifier.getCenter2i();
		assertEquals(center2i, centerTarget,"expected:" + center2i.toString() + ". was" + centerTarget.toString());
		assertEquals(center2i, defaultCenterTarget,"expected:" + center2i.toString() + ". was" + defaultCenterTarget.toString());
		assertEquals(centerTarget,defaultCenterTarget,"expected:" + centerTarget.toString() + ". was" + defaultCenterTarget.toString());
	}

	@Test
	public void getCenter3i_A$() throws Exception {
		Vector3i centerTarget = modifier.getCenter3i();
		Vector3i defaultCenterTarget = defaultModifier.getCenter3i();
		assertEquals(center3i, centerTarget,"expected:" + center3i.toString() + ". was" + centerTarget.toString());
		assertEquals(center3i,defaultCenterTarget,"expected:" + center3i.toString() + ". was" + defaultCenterTarget.toString());
		assertEquals(centerTarget,defaultCenterTarget,"expected:" + centerTarget.toString() + ". was" + defaultCenterTarget.toString());
	}

	@Test
	public void getDistanceToCenter_A$Vector3i() throws Exception {
		Vector3i testVec = new Vector3i(30, 30, 30);
		double expected = Math
				.sqrt(Math.pow(center3i.getX() - testVec.getX(), 2) + Math.pow(center3i.getZ() - testVec.getZ(), 2));
		double test1 = modifier.getDistanceToCenter(testVec);
		double test2 = defaultModifier.getDistanceToCenter(testVec);
		assertTrue(expected - test1 < ZERO,"expected:" + expected + ". was" + test1);
		assertTrue(expected - test2 < ZERO,"expected:" + expected + ". was" + test2);
		assertTrue(test1 - test2 < ZERO,"expected:" + test1 + ". was" + test2);
	}

	@Test
	public void getDistanceToCenter_A$Vector2i() throws Exception {
		Vector2i testVec = new Vector2i(30, 30);
		double expected = center2i.distance(testVec);
		double test1 = modifier.getDistanceToCenter(testVec);
		double test2 = defaultModifier.getDistanceToCenter(testVec);
		assertTrue(expected - test1 < ZERO,"expected:" + expected + ". was" + test1);
		assertTrue(expected - test2 < ZERO,"expected:" + expected + ". was" + test2);
		assertTrue(test1 - test2 < ZERO,"expected:" + test1 + ". was" + test2);
	}
}
