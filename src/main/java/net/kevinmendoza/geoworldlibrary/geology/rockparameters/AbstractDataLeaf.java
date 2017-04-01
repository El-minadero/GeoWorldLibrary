package net.kevinmendoza.geoworldlibrary.geology.rockparameters;

abstract class AbstractDataLeaf extends Comparison implements IGeologyData {

	private Order order;
	private int ID;
	
	AbstractDataLeaf(Order order,int i){ this.order = order; ID = i;}
	
	public final boolean isEmpty() { return false; }

	public final boolean isLeaf() { return true; }
	
	public final Order getOrder() { return order; }
	
	public final int getID() { return ID; }	
	
	public IGeologyData getOrderData(Order order) {
		if(order.equals(order))
			return this;
		else
			return DataFactory.GetEmptyData();
	}

}
