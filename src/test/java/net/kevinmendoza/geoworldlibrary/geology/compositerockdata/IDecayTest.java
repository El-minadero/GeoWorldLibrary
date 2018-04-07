package net.kevinmendoza.geoworldlibrary.geology.compositerockdata;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.kevinmendoza.geoworldlibrary.geology.rockdata.DecayFactory;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.IDecay;
public class IDecayTest {

	

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
