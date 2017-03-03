package net.kevinmendoza.geoworldlibrary.geology.rockparameters.other;

import java.util.HashMap;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;

public class GeologyDataFactory {

	public static <T> GeologyData<T> GetEmptyGeologyDataContainer() {
		return new Data<T>();
	}
	
	public static <T> GeologyData<T> GetGeologyDataWithData(Order order, GeologyData<T> data) {
		return new Data<T>(order,data);
	}

	private static class Data<T> implements GeologyData<T> {

		private HashMap<Order,GeologyData<T>> objects;

		Data(Order order, GeologyData<T> data) { objects = new HashMap<>(); objects.put(order, data);}
		Data() { objects = new HashMap<>(); }

		public void merge(GeologyData<T> object) {
			for(Order order : Order.values()) {
				GeologyData<T> other = object.getValue(order);
				if(other!=null)
					place(order,other);
			}
		}

		public GeologyData<T> getValue(Order order) { return objects.get(order); }

		private void place(Order order,GeologyData<T> object) {
			if(objects.containsKey(order)) 
				objects.get(order).merge(object);
			else
				objects.put(order, object);
		}
		public T getObject(Order order) { return (T) objects.get(order);}
	}
}
