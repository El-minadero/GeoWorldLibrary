package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics.ConicFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics.ConicType;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics.Ellipse;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics.IConic;

public class RectangleConicTest {
	private static final ConicType type = ConicType.RECTANGLE;
	private static final int[] dims = { 4, 6 };
	private static final double ZERO = 0.0001;
	private ConicFactory factory;
	private List<Vector2i> in2;
	private List<Vector2i> edge2;
	private List<Vector2i> out2;
	private List<Vector3i> in3;
	private List<Vector3i> edge3;
	private List<Vector3i> out3;

	@Test
	@Before
	public void type() throws Exception {
		assertThat(Ellipse.class, notNullValue());
		factory = new ConicFactory();

		in2 = new ArrayList<>();
		out2 = new ArrayList<>();
		edge2 = new ArrayList<>();
		in3 = new ArrayList<>();
		out3 = new ArrayList<>();
		edge3 = new ArrayList<>();

		in2.add(new Vector2i(2, 0));
		in2.add(new Vector2i(0, 2));
		out2.add(new Vector2i(6, 0));
		out2.add(new Vector2i(0, 7));
		edge2.add(new Vector2i(4, 6));
		edge2.add(new Vector2i(-4, -6));

		in3.add(new Vector3i(2, 0, 0));
		in3.add(new Vector3i(0, 7, 0));
		in3.add(new Vector3i(0, 0, 2));
		out3.add(new Vector3i(5, 0, 0));
		out3.add(new Vector3i(0, 0, 7));
		edge3.add(new Vector3i(4, 0, 6));
		edge3.add(new Vector3i(-4, 0, -6));
	}

	@Test
	public void isInside_A$Vector2i() throws Exception {
		boolean expected = true;
		boolean actual;
		IConic target = factory.createConic(type, dims);
		for (Vector2i vec : in2) {
			actual = target.isInside(vec);
			assertThat("Vector " + vec.toString() + " should be inside Conic", actual, is(equalTo(expected)));
		}
		for (Vector2i vec : out2) {
			actual = target.isInside(vec);
			assertNotSame("Vector " + vec.toString() + " should be outside Conic", actual, is(equalTo(expected)));
		}
		for (Vector2i vec : edge2) {
			actual = target.isInside(vec);
			assertThat("Vector " + vec.toString() + " should be inside Conic", actual, is(equalTo(expected)));
		}
	}

	@Test
	public void isInside_A$Vector3i() throws Exception {
		boolean expected = true;
		boolean actual;
		IConic target = factory.createConic(type, dims);
		for (Vector3i vec : in3) {
			actual = target.isInside(vec);
			assertThat("Vector " + vec.toString() + " should be inside Conic", actual, is(equalTo(expected)));
		}
		for (Vector3i vec : out3) {
			actual = target.isInside(vec);
			assertNotSame("Vector " + vec.toString() + " should be outside Conic", actual, is(equalTo(expected)));
		}
		for (Vector3i vec : edge3) {
			actual = target.isInside(vec);
			assertThat("Vector " + vec.toString() + " should be inside Conic", actual, is(equalTo(expected)));
		}
	}

	@Test
	public void getResidual_A$Vector2i() {
		boolean expression;
		double residual;
		IConic target = factory.createConic(type, dims);
		for (Vector2i vec : in2) {
			residual = target.getResidual(vec);
			expression = (residual < 1);
			assertTrue("residual from inside vector " + vec.toString() + " should be less than 1", expression);
		}
		for (Vector2i vec : out2) {
			residual = target.getResidual(vec);
			expression = (residual > 0);
			assertTrue("residual from outside vector " + vec.toString() + " should be greater than zero", expression);
		}
		for (Vector2i vec : edge2) {
			residual = target.getResidual(vec);
			expression = (residual < ZERO);
			assertTrue("residual from edge vector " + vec.toString() + " should be approximately zero", expression);
		}
	}

	@Test
	public void getResidual_A$Vector3i() {
		boolean expression;
		double residual;
		IConic target = factory.createConic(type, dims);
		for (Vector3i vec : in3) {
			residual = target.getResidual(vec);
			expression = (residual < 1);
			assertTrue("residual from inside vector " + vec.toString() + " should be less than 1", expression);
		}
		for (Vector3i vec : out3) {
			residual = target.getResidual(vec);
			expression = (residual > 0);
			assertTrue("residual from outside vector " + vec.toString() + " should be greater than zero", expression);
		}
		for (Vector3i vec : edge3) {
			residual = target.getResidual(vec);
			expression = (residual < ZERO);
			assertTrue("residual from edge vector " + vec.toString() + " should be approximately zero", expression);
		}
	}
}
