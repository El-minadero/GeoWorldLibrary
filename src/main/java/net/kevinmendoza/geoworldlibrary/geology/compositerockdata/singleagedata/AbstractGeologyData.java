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
	
	public abstract int toRGBCode();
	
}
