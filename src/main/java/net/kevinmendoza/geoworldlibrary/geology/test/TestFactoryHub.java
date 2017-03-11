package net.kevinmendoza.geoworldlibrary.geology.test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractMapFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.AbstractPrototypeFactory;
import net.kevinmendoza.geoworldlibrary.geology.test.maptest.TestMapFactory;
import net.kevinmendoza.geoworldlibrary.geology.test.object1test.Object1Factory;
import net.kevinmendoza.geoworldlibrary.geology.test.object2test.Object2Factory;

public class TestFactoryHub {

	
	private static AbstractMapFactory testMapFactory;
	private static AbstractPrototypeFactory object1Factory;
	private static AbstractPrototypeFactory object2Factory;
	private static Injector injector;
	
	private static Injector GetInjector() {
		if(injector==null) {
			injector = Guice.createInjector(new ParameterBind());
		}
		return injector;
	}
	 static void setSeed(long seed) {
		GetMapFactory().setSeed(seed);
	}
	 
	public static AbstractPrototypeFactory GetObject1Factory() {
			if(object1Factory==null) {
				object1Factory = GetInjector().getInstance(Object1Factory.class);
			}
			return object1Factory;
	}
	
	public static AbstractPrototypeFactory GetObject2Factory() {
		if(object2Factory==null) {
			object2Factory = GetInjector().getInstance(Object2Factory.class);
		}
		return object2Factory;
	}
	
	public static AbstractMapFactory GetMapFactory() {
		if(testMapFactory==null) {
			testMapFactory = GetInjector().getInstance(TestMapFactory.class);
		}
		return testMapFactory;
	}

}
