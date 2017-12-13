package net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata;

abstract class AbstractGeologyData implements ISingularGeologyData {
	private final int id;

	AbstractGeologyData(IGeologyDataBuilder builder){
		this.id = builder.getID();
	}
	
	public int getID() { return id; }
	
	protected boolean isMergable(ISingularGeologyData data) {
		return ((data!=null) &&  data.getID()==id);
	}
	
	private double invWeight(double w) {
		return 1 - w;
	}
	protected double weightedDataMerge(double val1, double val2, double weight2) {
		return val1*invWeight(weight2) + val2*weight2;
	}

	public abstract int toRGBCode();
	
}
