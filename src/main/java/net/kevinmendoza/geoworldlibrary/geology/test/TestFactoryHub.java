package net.kevinmendoza.geoworldlibrary.geology.test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologicalFactory.GeologyFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologicalFactory.GeologyMapFactory;
import net.kevinmendoza.geoworldlibrary.geology.test.maptest.TestMapFactory;

class TestFactoryHub {

	
	private static TestMapFactory testMapFactory;
	private static Injector injector;
	
	static Injector getInjector() {
		if(injector==null) {
			injector = Guice.createInjector(new ParameterBind());
		}
		return injector;
	}
	 static void setSeed(long seed) {
		getMapFactory().setSeed(seed);
	}
	
	
	static GeologyMapFactory getMapFactory() {
		if(testMapFactory==null) {
			testMapFactory = getInjector().getInstance(TestMapFactory.class);
		}
		return testMapFactory;
	}

}
