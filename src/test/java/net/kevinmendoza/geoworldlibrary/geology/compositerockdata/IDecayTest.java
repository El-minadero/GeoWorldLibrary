package net.kevinmendoza.geoworldlibrary.geology.compositerockdata;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.rockdata.Decay;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.DecayFactory;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.IDecay;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.IDecay.*;
public class IDecayTest {

	@Test
	public void type() throws Exception {
		assertNotNull(Decay.class);
	}

	@Test
	public void instantiation() throws Exception {
		IDecay target = DecayFactory.getDefaultDecay();
		assertNotNull(target);
	}
	
	@Test
	public void modifierIsOneAtDistanceZero() throws Exception {
		IDecay target = DecayFactory.getDefaultDecay();
		double modifier = target.getModifier(0);
		assertTrue(modifier - 1 < 0.0001);
	}
	
	@Test
	public void modifierIsZeroAtDistanceFive() throws Exception {
		IDecay target = DecayFactory.getDefaultDecay();
		double modifier = target.getModifier(5);
		assertTrue(modifier < 0.0001);
	}

}
