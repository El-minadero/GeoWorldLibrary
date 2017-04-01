package net.kevinmendoza.geoworldlibrary.geology.rockparameters;

public abstract class Alteration extends AbstractDataLeaf {

	private double pressure;
	private double heat;
	
	protected Alteration(IAlterationBuilder builder) {
		super(builder.getOrder(), builder.getID());
		this.heat = builder.getHeat();
		this.pressure = builder.getPressure();
	}
	
	protected double getProtectedPressure() {return pressure;}
	protected void setProtectedPressure(double p) {pressure = p;}
	
	protected double getProtectedHeat() { return heat; }
	protected void setProtectedHeat(double h) { heat = h; }
	
	public abstract double getPressure();
	public abstract double getHeat();
	
}
