package net.kevinmendoza.geoworldlibrary.geology.rockparameters;

public class DataFactory {

	private static IGeologyData emptyData;
	
	public static IGeologyData GetEmptyData() {
		if(emptyData==null)
			emptyData = new EmptyData();
		return emptyData;
	}
	
	public static IGeologyData GetGeologyDataNode(int ID) {
		return new DataNode(ID);
	}

	private static class EmptyData implements IGeologyData {

		public void merge(IGeologyData data,double d) { }
		public void merge(IGeologyData data) { }
		public void applyMultiplier(double multiplier) {}
		public boolean isEmpty() { return true; }
		public boolean isLeaf() { return true;  }
		public int getID() { return 0;}
		public IGeologyData copy() {
			return GetEmptyData();
		}
		public IGeologyData getOrderData(Order order) { return GetEmptyData(); }
		
	}
}
