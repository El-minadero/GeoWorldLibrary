package net.kevinmendoza.geoworldlibrary.geology.rockparameters;

import java.util.HashMap;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.GeoDataInterface;

class GeologyHistory<T extends GeoDataInterface<T>>{

	private HashMap<Order,T> sequence;

	GeologyHistory(){ sequence = new HashMap<>();}
	GeologyHistory(Order order, T replacement){
		sequence = new HashMap<>();
		sequence.put(order, replacement);
	}
	private  GeologyHistory(HashMap<Order,T> sequence){
		this.sequence = sequence;
	}
	public GeologyHistory<T> getCopy() { 
		return new GeologyHistory<T>(sequence);
	}  

	public void merge(GeologyHistory<T> t) {
		for(Order order : Order.values()) {
			T other = t.get(order);
			if(other!=null)
				place(order,other);
		}
	}

	private T get(Order order) {
		return sequence.get(order);
	}

	public void place(Order order,T t) {
		if(sequence.containsKey(order)) {
			if(sequence.get(order).canReplace(t))
				sequence.put(order, t);
			else if(sequence.get(order).canMerge(t))
				sequence.get(order).merge(t);
		}
		sequence.put(order, t);
	}



}
