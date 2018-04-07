package net.kevinmendoza.geoworldlibrary.geology.rockdata;

public class RockData implements IData {

	private static final double DOUBLE_COMPARE_CUTOFF = 0.000001;
	private Texture texture;
	private BulkComposition bulkComposition;
	private double[] activityModifiers;
	private double[] metals;
	private double weight;
	private boolean isNull;
	
	private RockData(Builder builder) {
		isNull = builder.isNull();
		texture = builder.getTexture();
		bulkComposition=builder.getBulkComposition();
		activityModifiers=builder.getActivityModifiers();
		metals=builder.getMetals();
		weight=1;
	}
	
	private RockData(IData data) {
		RockData other = (RockData)data;
		this.isNull=other.isNull;
		this.texture=other.texture;
		this.bulkComposition=other.bulkComposition;
		this.activityModifiers=other.activityModifiers;
		this.metals=other.metals;
		weight=1;
	}
	
	@Override
	public void modifyData(double modifier) {
		weight*=modifier;
		modifyMetals(modifier);
		modifyActivities(modifier);
	}
	private void modifyMetals(double modifier) {
		for(int index = 0; index<metals.length;index++) {
			metals[index]*=modifier;
		}
	}
	private void modifyActivities(double modifier) {
		for(int index = 0; index<activityModifiers.length;index++) {
			activityModifiers[index]*=modifier;
		}
	}
	@Override
	public IData merge(IData data) {
		if(isNull) {
			return data;
		}
		else {
			return mergeData(data);
		}
	}

	private IData mergeData(IData data) {
		RockData rockData = (RockData)data;
		if(!rockData.isNull) {
			mergeDescriptors(rockData);
			mergeArrays(rockData);
		}
		return this;
	}

	private void mergeArrays(RockData data) {
		metals				=mergeValues(metals, data.metals);
		activityModifiers	=mergeValues(activityModifiers, data.activityModifiers);
	}

	private double[] mergeValues(double[] original, double[] other) {
		for(int index=0;index<original.length;index++) {
			if(original[index]<other[index]) {
				original[index] = other[index];
			}
		}
		return original;
	}
	private void mergeDescriptors(RockData data) {
		if(data.getWeight()>weight) {
			this.weight=data.getWeight();
			setEnums(data);
		}
	}

	private void setEnums(RockData data) {
		if(data.texture!=null) {
			this.texture=data.texture;
		}
		if(data.bulkComposition!=null) {
			this.bulkComposition=data.bulkComposition;
		}
	}
	
	@Override
	public final boolean equals(Object obj) {
		if(obj instanceof RockData) {
			RockData data = (RockData)obj;
			boolean zero		= data.isNull==this.isNull;
			boolean one 		= equalDoubleArrays(data.metals,this.metals);
			boolean two 		= equalDoubleArrays(data.activityModifiers,this.activityModifiers);
			boolean three 	= data.texture.equals(this.texture);
			boolean four		= data.bulkComposition.equals(this.bulkComposition);
			return (one && two && three && four && zero);
		}
		else {
			System.out.println("not instance! wtf?");
			return false;
		}
	}

	private boolean equalDoubleArrays(double[] arr1, double[] arr2) {
		boolean toReturn = true;
		for(	int i = 0; i<arr1.length; i++) {
			if(Math.abs(arr1[i]-arr2[i]) > DOUBLE_COMPARE_CUTOFF) {
				toReturn=false;
			}
		}
		return toReturn;
	}
	
	@Override
	public double getWeight() { return weight; }
	
	public IData copy() {
		return new RockData(this);
	}
	
	public static class Builder {

		private Texture texture;
		private BulkComposition bulkComposition;
		private double[] activityModifiers;
		private double[] metals;
		private boolean isNull;
		
		public Builder() {
			isNull				= false;
			texture          	= Texture.Clastic_Medium;
			bulkComposition   	= BulkComposition.Silicate;
			activityModifiers	= new double[ActivityModifiers.values().length];
			metals			 	= new double[Metals.values().length];
		}

		public boolean isNull() { return isNull; }
		public Builder setNull() { isNull=true; return this; }
		
		public BulkComposition getBulkComposition() { return bulkComposition; }
		public Texture getTexture() { return texture; }
		public double[] getMetals() { return metals; }
		public double[] getActivityModifiers() { return activityModifiers; }
		
		public Builder setMetals(double[] metalArray) {
			metals = metalArray; return this;
		}
		public Builder setMetals(Metals metal, double value) {
			metals[metal.ordinal()] =value; return this;
		}
		public Builder setActivity(double[] activityArray) {
			activityModifiers =activityArray; return this;
		}
		public Builder setActivity(ActivityModifiers activity, double value) {
			activityModifiers[activity.ordinal()] =value; return this;
		}
		public Builder setTexture(Texture tex) {
			texture = tex; return this;
		}
		public Builder setBulkComposition(BulkComposition composition) {
			bulkComposition=composition; return this;
		}

		public IData build() {
			return new RockData(this);
		}
		
	}
	
}
