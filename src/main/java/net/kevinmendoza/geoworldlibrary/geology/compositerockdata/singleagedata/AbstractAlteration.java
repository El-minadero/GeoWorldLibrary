package net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata;

import java.awt.Color;

import com.google.common.primitives.Bytes;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.DefaultDataFactory;

public abstract class AbstractAlteration extends AbstractGeologyData {

	private double[] values;
	
	protected AbstractAlteration(IAlterationBuilder builder) {
		super(builder);
		values = new double[4];
		this.values[0] = builder.getHeat();
		this.values[1] = builder.getPressure();
		this.values[2] = builder.getHydrothermal();
		this.values[3] = builder.getWeathering();
	}
	public double getIndexValue(int i) { return values[i]; }
	protected void setProtectedHeat(double h) 			{ values[0]	= h; }
	protected void setProtectedPressure(double p) 		{ values[1] = p; }
	protected void setProtectedHydrothermal(double h) 	{ values[2] = h; }
	protected void setProtectedWeathering(double w) 	{ values[3] = w; }
	
	public final double getHeat() 			{ return values[0]; }
	public final double getPressure() 		{ return values[1]; }
	public final double getHydrothermal() 	{ return values[2]; }
	public final double getWeathering()		{ return values[3]; }
	
	
	public void applyMultiplier(double multiplier) {
		for(int i=0;i<values.length;i++) {
			values[i]*=multiplier;
		}
	}

	@Override
	public void merge(ISingularGeologyData data, double mergeWeight) {
		if(isMergable(data)) {
			AbstractAlteration other = (AbstractAlteration)data;
			for(int i=0;i<values.length;i++) {
				values[i] = weightedDataMerge(values[i], other.getIndexValue(i), mergeWeight);
			}
		}
	}
	@Override
	public void merge(ISingularGeologyData data) {
		if(isMergable(data)) {
			AbstractAlteration other = (AbstractAlteration)data;
			for(int i=0;i<values.length;i++) {
				if(values[i]< other.getIndexValue(i)) {
					values[i]=other.getIndexValue(i);
				}
			}
		}
	}
	
	public final String toString() {
		String v = "";
		for(AlterationType type : AlterationType.values()) {
			v = v +" " + type + ":" + values[type.ordinal()];
		}
		return v;
	}


	public AbstractAlteration copy() {
		return DefaultDataFactory.getAlteration(values[0],values[1],values[2],values[3]);
	}

	public void setAlterationValue(AlterationType type, double value) {
		values[type.ordinal()] = value;
	}
	public double getAlterationValue(AlterationType type) {
		return values[type.ordinal()];
	}
	public final int toRGBCode() {
		Color color = new Color((float)values[0]/20,5*(float)values[1],10*(float)values[2]);
		return color.getRGB();
	}
	
}
