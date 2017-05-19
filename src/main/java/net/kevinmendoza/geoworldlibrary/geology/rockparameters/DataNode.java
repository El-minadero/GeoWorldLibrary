package net.kevinmendoza.geoworldlibrary.geology.rockparameters;

import java.util.HashMap;

class DataNode implements IGeologyData {

	HashMap<Order,IGeologyData> dataMap;
	private final int id;
	
	DataNode(int ID){
		id = ID;
		dataMap = new HashMap<>();
		for(Order order: Order.values()) {
			dataMap.put(order, DataFactory.GetEmptyData());
		}
	}

	public DataNode(int id2, HashMap<Order, IGeologyData> dataMap2) {
		id=id2;
		dataMap = new HashMap<>();
		for(Order order : Order.values()) {
			dataMap.put(order, dataMap2.get(order).copy());
		}
	}

	public IGeologyData copy() {
		return new DataNode(id,dataMap);
	}
	@Override
	public void merge(IGeologyData data,double mergeWeight) {
		if(!data.isEmpty()) {
			if(data.isLeaf()) {
				AbstractDataLeaf leaf = (AbstractDataLeaf)data;
				mergeSingleLeaf(leaf,mergeWeight);
			}
			else {
				mergeNode(data,mergeWeight);
			}
		}
	}
	
	public void merge(IGeologyData data) {
		if(!data.isEmpty()) {
			if(data.isLeaf()) {
				AbstractDataLeaf leaf = (AbstractDataLeaf)data;
				mergeSingleLeaf(leaf);
			}
			else {
				mergeNode(data);
			}
		}
	}
	private void mergeNode(IGeologyData data,double mergeWeight) {
		for(Order order: Order.values()) {
			IGeologyData source = data.getOrderData(order);
			if(!source.isEmpty() && source.isLeaf())
				mergeSingleLeaf((AbstractDataLeaf)source,mergeWeight);
		}
	}
	private void mergeNode(IGeologyData data) {
		for(Order order: Order.values()) {
			IGeologyData source = data.getOrderData(order);
			if(!source.isEmpty() && source.isLeaf())
				mergeSingleLeaf((AbstractDataLeaf)source);
		}
	}
	
	private void mergeSingleLeaf(AbstractDataLeaf leaf, double mergeWeight) {
		IGeologyData target = dataMap.get(leaf.getOrder());
		if(target.isEmpty()) {
			dataMap.put(leaf.getOrder(), leaf.copy());
		}
		else
			target.merge(leaf,mergeWeight);
	}
	private void mergeSingleLeaf(AbstractDataLeaf leaf) {
		IGeologyData target = dataMap.get(leaf.getOrder());
		if(target.isEmpty()) {
			dataMap.put(leaf.getOrder(), leaf.copy());
		}
		else
			target.merge(leaf);
	}
	
	public IGeologyData getOrderData(Order order) { return dataMap.get(order); }
	
	public int getID() { return id; }
	public boolean isLeaf() { return false; }
	public boolean isEmpty() {
		boolean b = true;
		for(Order order : Order.values()) {
			if(!dataMap.get(order).isEmpty())
				b = false;
		}
		return b;
	}

	@Override
	public void applyMultiplier(double multiplier) {
		for(Order order: Order.values()) {
			IGeologyData source = dataMap.get(order);
			if(!source.isEmpty())
				source.applyMultiplier(multiplier);
		}
	}
	

}
