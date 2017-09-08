package net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata;

import com.google.common.primitives.Bytes;

public abstract class AbstractRock extends AbstractGeologyData {

	private String rock;
	private final String nullRock = "";
	private double cutoff;
	private double currentValue;
	
	protected AbstractRock(IRockBuilder builder) {
		super(builder);
		rock 			= builder.getRock();
		cutoff			= builder.getMultiplierCutoff();
		currentValue 	= 1;
	}
	
	public final double  getCutoff() { return cutoff; }
	protected final void setCutoff(double c) { this.cutoff = c; }
	
	protected final void multiplyValue(double val) { currentValue*=val;  }
	protected final void setValue(double val) { currentValue = val; }
	public final double  getValue() 		  { return currentValue; }
	
	protected final void setProtectedRock(String rock) {this.rock = rock;}
	public final String getPreEvaluatedRock() { return rock; }
	
	public final boolean isNullRock() { 
		String rk = getPreEvaluatedRock();
		boolean y = (rk.equalsIgnoreCase(nullRock));
		return y; }
	
	public final double getDifference() {
		double val = currentValue - cutoff;
		if(val< 0)
			return 0.0;
		else
			return val;
	}
	public final String getEvaluatedRock() { 
		if(cutoff < currentValue)
			return rock; 
		else
			return nullRock; 
	}
	
	public final void applyMultiplier(double multiplier) {
		multiplyValue(multiplier);
	}

	@Override
	public final void merge(ISingularGeologyData data, double mergeWeight) {
		if(isMergable(data)) {
			AbstractRock other = (AbstractRock)data;
			if(!other.isNullRock()) {
				if(isNullRock()) {
					setCutoff(other.getCutoff());
					setValue(other.getValue()*mergeWeight);
					setProtectedRock(other.getPreEvaluatedRock());
				}
				else {
					double thisDiff = getDifference()*(1-mergeWeight);
					double otherDiff = other.getDifference()*mergeWeight;
					if(thisDiff < otherDiff) {
						setCutoff(other.getCutoff());
						setValue(other.getValue());
						setProtectedRock(other.getPreEvaluatedRock());
					}
				}
			}
		}
	}

	@Override
	public final void merge(ISingularGeologyData data) {
		if(isMergable(data)) {
			AbstractRock other = (AbstractRock)data;
			if(!other.isNullRock()) {
				if(isNullRock()) {
					setCutoff(other.getCutoff());
					setValue(other.getValue());
					setProtectedRock(other.getPreEvaluatedRock());
				}
				else {
					double thisDiff = getDifference();
					double otherDiff = other.getDifference();
					if(thisDiff < otherDiff) {
						setCutoff(other.getCutoff());
						setValue(other.getValue());
						setProtectedRock(other.getPreEvaluatedRock());
					}
				}
			}
		}
	}
	
	public final String toString() { return "Rock: "+ rock + " cutoff:" + cutoff + " value:" + currentValue; }
	@Override
	public final int toRGBCode() {
		int val = 1;
		if(rock!=null) {
			for(Byte b : rock.getBytes()) {
				val*= Bytes.hashCode(b);
			}
		}
		else {
			val = 0;
		}
		return  val;
	}

}
