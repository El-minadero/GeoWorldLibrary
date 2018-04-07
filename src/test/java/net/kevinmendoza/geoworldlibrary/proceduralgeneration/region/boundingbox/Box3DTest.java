package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingbox;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingbox.BoundingBoxFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingbox.Box2D;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingbox.BoxType;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingbox.IBoundingBox;

public class Box3DTest {
	private static final BoxType type = BoxType.D3;
	private static final int[] dims = { 5, 6, 4 };
	private static final Random rand = new Random();
	private static final double X = 5;
	private static final double Y = 6;
	private static final double Z = 4;
	private static final double ZERO = 0.0001;
	private static BoundingBoxFactory factory;
	private static List<Vector2i> in2;
	private static List<Vector2i> edge2;
	private static List<Vector2i> out2;
	private static List<Vector3i> in3;
	private static List<Vector3i> edge3;
	private static List<Vector3i> out3;


	@BeforeAll
	public static void type() throws Exception {
		factory = new BoundingBoxFactory();

		in2 = new ArrayList<>();
		out2 = new ArrayList<>();
		edge2 = new ArrayList<>();
		in3 = new ArrayList<>();
		out3 = new ArrayList<>();
		edge3 = new ArrayList<>();

		in2.add(new Vector2i(2, 0));
		in2.add(new Vector2i(0, 2));
		out2.add(new Vector2i(X + 1, 0));
		out2.add(new Vector2i(0, Z + 1));
		edge2.add(new Vector2i(X, Z));
		edge2.add(new Vector2i(-X, -Z));

		in3.add(new Vector3i(2, 0, 0));
		in3.add(new Vector3i(0, 2, 0));
		in3.add(new Vector3i(0, 0, 2));
		out3.add(new Vector3i(X + 1, 0, 0));
		out3.add(new Vector3i(0, Y + 1, 0));
		out3.add(new Vector3i(0, 0, Z + 1));
		edge3.add(new Vector3i(X, Y, Z));
		edge3.add(new Vector3i(-X, -Y, -Z));
	}

	@Test
	public void isOverXDim_A$Vector3i() throws Exception {
		boolean expected = true;
		boolean actual;
		IBoundingBox target = factory.createBoundingBox(type, dims, rand);
		for (Vector3i vec : in3) {
			actual = target.isOverXDim(vec);
			assertNotEquals("Vector X dim " + vec.toString() + " should be inside Box", actual, expected);
		}

		actual = target.isOverXDim(out3.get(0));
		assertTrue("Vector X dim" + out3.get(0).toString() + " should be outside Conic", actual);

		for (Vector3i vec : edge3) {
			actual = target.isOverXDim(vec);
			assertNotEquals("Vector Xdim " + vec.toString() + " should be inside Conic", actual, expected);
		}
	}

	@Test
	public void isOverYDim_A$Vector3i() throws Exception {

		boolean expected = true;
		boolean actual;
		IBoundingBox target = factory.createBoundingBox(type, dims, rand);
		for (Vector3i vec : in3) {
			actual = target.isOverYDim(vec);
			assertNotEquals("Vector Y dim " + vec.toString() + " should be inside Box :" + target.toString(), actual,
					expected);
		}

		actual = target.isOverYDim(out3.get(1));
		assertTrue("Vector Y dim" + out3.get(1).toString() + " should be outside Box " + target.toString(), actual);

		for (Vector3i vec : edge3) {
			actual = target.isOverYDim(vec);
			assertNotEquals("Vector Y dim " + vec.toString() + " should be inside Box", actual, expected);
		}
	}

	@Test
	public void isOverZDim_A$Vector3i() throws Exception {
		boolean expected = true;
		boolean actual;
		IBoundingBox target = factory.createBoundingBox(type, dims, rand);
		for (Vector3i vec : in3) {
			actual = target.isOverZDim(vec);
			Assertions.assertNotEquals(actual, expected,"Vector Z dim " + vec.toString() + " should be inside Box");
		}
		actual = target.isOverZDim(out3.get(2));
		assertTrue("Vector Z dim" + out3.get(2).toString() + " should be outside Box", actual);

		for (Vector3i vec : edge3) {
			actual = target.isOverZDim(vec);
			Assertions.assertNotEquals(actual, expected,"Vector Z " + vec.toString() + " should be inside Box");
		}
	}

	@Test
	public void getArea_A$() throws Exception {
		IBoundingBox target = factory.createBoundingBox(type, dims, rand);
		double areaResidual = target.getArea() - X * Y * Z * 8;
		boolean condition = (areaResidual < ZERO);
		assertTrue("Area Calculations are off", condition);
	}

	@Test
	public void getRandomInternalPoint2i_A$() throws Exception {
		IBoundingBox target = factory.createBoundingBox(type, dims, rand);
		List<Vector2i> points = new ArrayList<>();
		int identical = 0;
		for (int i = 0; i < 30; i++) {
			Vector2i vec = target.getRandomInternalPoint2i();
			assertTrue("Random Vector2i placing points outside bounding box", target.isInside(vec));
			if (points.contains(vec)) {
				identical++;
			}
			points.add(vec);
		}
		assertTrue("Random Vector2i is not really random", (identical < points.size()));

	}

	@Test
	public void getRandomInternalPoint3i_A$() throws Exception {
		IBoundingBox target = factory.createBoundingBox(type, dims, rand);
		List<Vector3i> points = new ArrayList<>();
		int identical = 0;
		for (int i = 0; i < 30; i++) {
			Vector3i vec = target.getRandomInternalPoint3i();
			assertTrue("Random Vector2i placing points outside bounding box", target.isInside(vec));
			if (points.contains(vec)) {
				identical++;
			}
			points.add(vec);
		}
		assertTrue("Random Vector2i is not really random", (identical < points.size()));
	}

	@Test
	public void isInside_A$Vector2i() throws Exception {
		boolean expected = true;
		boolean actual;
		IBoundingBox target = factory.createBoundingBox(type, dims, rand);
		for (Vector2i vec : in2) {
			actual = target.isInside(vec);
			assertTrue("Vector " + vec.toString() + " should be inside Box", actual);
		}
		for (Vector2i vec : out2) {
			actual = target.isInside(vec);
			Assertions.assertNotEquals(actual, expected,"Vector " + vec.toString() + " should be outside Box");
		}
		for (Vector2i vec : edge2) {
			actual = target.isInside(vec);
			assertTrue("Vector " + vec.toString() + " should be inside Box", actual);
		}
	}

	@Test
	public void isInside_A$Vector3i() throws Exception {
		boolean expected = true;
		boolean actual;
		IBoundingBox target = factory.createBoundingBox(type, dims, rand);
		for (Vector3i vec : in3) {
			actual = target.isInside(vec);
			assertTrue("Vector " + vec.toString() + " should be inside Box", actual);
		}
		for (Vector3i vec : out3) {
			actual = target.isInside(vec);
			Assertions.assertNotEquals(actual, expected,"Vector " + vec.toString() + " should be outside Box");
		}
		for (Vector3i vec : edge3) {
			actual = target.isInside(vec);
			assertTrue("Vector " + vec.toString() + " should be inside Box", actual);
		}
	}
	private void assertTrue(String string, boolean val) {
		Assertions.assertTrue(val,string);
	}
	private void assertEquals(String string, Object o1, Object o2) {
		Assertions.assertEquals(o1,o2,string);
	}
	private void assertNotEquals(String string, Object o1, Object o2) {
		Assertions.assertNotEquals(o1,o2,string);
	}
}
