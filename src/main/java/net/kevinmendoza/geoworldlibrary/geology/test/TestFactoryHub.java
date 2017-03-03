package net.kevinmendoza.geoworldlibrary.geology.test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologicalFactory.GeologyFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologicalFactory.GeologyMapFactory;

class TestFactoryHub {

	private static GeologyFactory testFactory1;
	private static GeologyFactory testFactory2;
	private static GeologyFactory testFactory3;
	private static TestMapFactory testMapFactory;
	private static Injector injector;
	
	 static Injector getInjector() {
		if(injector==null) {
			injector = Guice.createInjector(new ParameterBind());
		}
		return injector;
	}
	 static void setSeed(long seed) {
		getTestFactory1().setSeed(seed);
		getTestFactory2().setSeed(seed);
		getTestFactory3().setSeed(seed);
		getMapFactory().setSeed(seed);
	}
	
	 static GeologyFactory getTestFactory1() {
		if(testFactory1==null) {
			testFactory1 = getInjector().getInstance(TestFactory1.class);
		}
		return testFactory1;
	}
	
	static GeologyFactory getTestFactory2() {
		if(testFactory2==null) {
			testFactory2 = getInjector().getInstance(TestFactory2.class);
		}
		return testFactory2;
	}
	
	static GeologyFactory getTestFactory3() {
		if(testFactory3==null) {
			testFactory3 = getInjector().getInstance(TestFactory3.class);
		}
		return testFactory3;
	}
	
	static GeologyMapFactory getMapFactory() {
		if(testMapFactory==null) {
			testMapFactory = getInjector().getInstance(TestMapFactory.class);
		}
		return testMapFactory;
	}

}
