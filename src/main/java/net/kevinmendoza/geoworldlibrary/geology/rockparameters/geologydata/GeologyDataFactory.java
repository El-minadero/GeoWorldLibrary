package net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydata;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;

public class GeologyDataFactory {
	
	public static <T extends GeologyData<T>> GeologyDataContainer<T> CreateGeologyDataContainer(Order o, T t) {
		return new ImplementedGeologyDataContainer<T>(o,t);
	}
}
