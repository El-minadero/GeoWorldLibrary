package net.kevinmendoza.geoworldlibrary.geology.test;

import com.flowpowered.math.vector.Vector2i;
import com.google.inject.Inject;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologicalFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologyObject;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologyPrototype;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologicalFactory.GeologyFactory;
import net.kevinmendoza.geoworldlibrary.geology.test.TestParameters.TestObject1Parameters;
import net.kevinmendoza.geoworldlibrary.geology.test.TestParameters.TestObject3Parameters;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;

public class TestFactory3 extends GeologyFactory  {

	@Inject
	private TestObject3Parameters parameters;

	@Override
	public GeologyObject makePrototype(Region superRegion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeologyObject makePrototype(Vector2i vec) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeologyObject makeObject(GeologyPrototype prototype) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Region makeRegion(Vector2i vec) {
		// TODO Auto-generated method stub
		return null;
	}



}
