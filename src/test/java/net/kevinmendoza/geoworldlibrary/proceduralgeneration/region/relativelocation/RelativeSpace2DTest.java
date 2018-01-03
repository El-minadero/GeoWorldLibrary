package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.relativelocation;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.relativelocation.IRelativeSpace;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.relativelocation.RelativeSpaceFactory;

public class RelativeSpace2DTest {
	private static final double ZERO = 0.001;
	private Vector2i center2i = new Vector2i(5, 5);
	private RelativeSpaceFactory factory;
	private IRelativeSpace defaultModifier;
	private IRelativeSpace modifier;
	private Vector3i center3i;
	private double cos;
	private double sin;
	private double cos_;
	private double sin_;

	@Before
	public void initialize() {
		Random rand = new Random();
		;
		double angle = Math.toRadians(35);
		cos = Math.cos(angle);
		sin = Math.sin(angle);
		cos_ = Math.cos(-angle);
		sin_ = Math.sin(-angle);
		factory = new RelativeSpaceFactory();
		center2i = new Vector2i(5, 5);
		center3i = new Vector3i(5, 0, 5);
		defaultModifier = factory.createRelative2DFrame(center2i, rand, false);
		modifier = factory.createRelative2DFrame(center2i, angle);
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
		assertThat(defaultModifier, notNullValue());
		assertThat(modifier, notNullValue());
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

		assertEquals("expected:" + center2i.toString() + ". was" + targetCenter.toString(), center2i, targetCenter);
		assertEquals("expected:" + center2i.toString() + ". was" + defaultTargetCenter.toString(), center2i,
				defaultTargetCenter);
		assertEquals(defaultTargetCenter, targetCenter);

		assertEquals("expected:" + offrot.toString() + ". was" + targetRot.toString(), offrot, targetRot);
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

		assertEquals("expected:" + center3i.toString() + ". was" + targetCenter.toString(), center3i, targetCenter);
		assertEquals("expected:" + center3i.toString() + ". was" + defaultTargetCenter.toString(), center3i,
				defaultTargetCenter);
		assertEquals(defaultTargetCenter, targetCenter);

		assertEquals("expected:" + offrot.toString() + ". was" + targetRot.toString(), offrot, targetRot);
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

		assertEquals("expected:" + center.toString() + ". was" + localCenter1.toString(), center, localCenter1);
		assertEquals("expected:" + center.toString() + ". was" + localCenter2.toString(), center, localCenter2);
		assertEquals("expected:" + localoffset.toString() + ". was" + localPoint1.toString(), localPoint1, localoffset);
		assertEquals("expected:" + localoffset.toString() + ". was" + localPoint2.toString(), localPoint2, localoffset);

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

		assertEquals("expected:" + center.toString() + ". was" + localCenter1.toString(), center, localCenter1);
		assertEquals("expected:" + center.toString() + ". was" + localCenter2.toString(), center, localCenter2);
		assertEquals("expected:" + localoffset.toString() + ". was" + localPoint1.toString(), localPoint1, localoffset);
		assertEquals("expected:" + localoffset.toString() + ". was" + localPoint2.toString(), localPoint2, localoffset);
	}

	@Test
	public void getCenter2i_A$() throws Exception {
		Vector2i centerTarget = modifier.getCenter2i();
		Vector2i defaultCenterTarget = defaultModifier.getCenter2i();
		assertEquals("expected:" + center2i.toString() + ". was" + centerTarget.toString(), center2i, centerTarget);
		assertEquals("expected:" + center2i.toString() + ". was" + defaultCenterTarget.toString(), center2i,
				defaultCenterTarget);
		assertEquals("expected:" + centerTarget.toString() + ". was" + defaultCenterTarget.toString(), centerTarget,
				defaultCenterTarget);
	}

	@Test
	public void getCenter3i_A$() throws Exception {
		Vector3i centerTarget = modifier.getCenter3i();
		Vector3i defaultCenterTarget = defaultModifier.getCenter3i();
		assertEquals("expected:" + center3i.toString() + ". was" + centerTarget.toString(), center3i, centerTarget);
		assertEquals("expected:" + center3i.toString() + ". was" + defaultCenterTarget.toString(), center3i,
				defaultCenterTarget);
		assertEquals("expected:" + centerTarget.toString() + ". was" + defaultCenterTarget.toString(), centerTarget,
				defaultCenterTarget);
	}

	@Test
	public void getDistanceToCenter_A$Vector3i() throws Exception {
		Vector3i testVec = new Vector3i(30, 30, 30);
		double expected = Math
				.sqrt(Math.pow(center3i.getX() - testVec.getX(), 2) + Math.pow(center3i.getZ() - testVec.getZ(), 2));
		double test1 = modifier.getDistanceToCenter(testVec);
		double test2 = defaultModifier.getDistanceToCenter(testVec);
		assertTrue("expected:" + expected + ". was" + test1, expected - test1 < ZERO);
		assertTrue("expected:" + expected + ". was" + test2, expected - test2 < ZERO);
		assertTrue("expected:" + test1 + ". was" + test2, test1 - test2 < ZERO);
	}

	@Test
	public void getDistanceToCenter_A$Vector2i() throws Exception {
		Vector2i testVec = new Vector2i(30, 30);
		double expected = center2i.distance(testVec);
		double test1 = modifier.getDistanceToCenter(testVec);
		double test2 = defaultModifier.getDistanceToCenter(testVec);
		assertTrue("expected:" + expected + ". was" + test1, expected - test1 < ZERO);
		assertTrue("expected:" + expected + ". was" + test2, expected - test2 < ZERO);
		assertTrue("expected:" + test1 + ". was" + test2, test1 - test2 < ZERO);
	}
}
