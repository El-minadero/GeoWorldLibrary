
package net.kevinmendoza.geoworldlibrary.geology.compositerockdata;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.kevinmendoza.geoworldlibrary.geology.rockdata.DataFactory;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.IData;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.NullData;
public class NullDataTest {

	@Test
	public void type() throws Exception {
		assertNotNull(NullData.class);
	}

	@Test
	public void instantiation() throws Exception {
		IData target = DataFactory.getDefaultData();
		assertNotNull(target);
	}

	@Test
	public void modifyData_A$double() throws Exception {
		IData target = DataFactory.getDefaultData();
		target.modifyData(0.5);
		assertTrue(target.get() - 0.5 < 0.0001);
	}

	@Test
	public void merge_A$IData() throws Exception {
		IData target1 = DataFactory.getDefaultData();
		IData target2 = DataFactory.getDefaultData();
		target2.modifyData(0.5);
		IData finalTarget = target1.merge(target2);
		assertTrue(finalTarget.get() - 1.0 < 0.0001);
	}

	@Test
	public void get_A$() throws Exception {
		IData target = DataFactory.getDefaultData();
		assertTrue(target.get()-1<0.0001);
	}

}
