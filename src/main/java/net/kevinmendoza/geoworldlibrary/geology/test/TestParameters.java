package net.kevinmendoza.geoworldlibrary.geology.test;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.GeologyPrototype;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;

class TestParameters {

	
	static class TestMapParameters implements MapParameters {
		private int spacing = 10000;
		private double frequency =1 ;
		private Order order = Order.FIRST;
		
		public int getSpacing() {return spacing;}
		public double getFrequency() {return frequency;}
		public Order getOrder() {return order; }
		@Override
		public GeologyPrototype getPrototype() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	 static class TestObject1Parameters implements ObjectParameters {

	}

	 static class TestObject2Parameters implements ObjectParameters {

	}

	 static class TestObject3Parameters implements ObjectParameters {

	}
	
	 static interface MapParameters{

		int getSpacing();

		double getFrequency();

		Order getOrder();

		GeologyPrototype getPrototype();

	 }

	 static interface ObjectParameters {
	 }
 }
