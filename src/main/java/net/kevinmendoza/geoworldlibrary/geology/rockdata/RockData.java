package net.kevinmendoza.geoworldlibrary.geology.rockdata;

class RockData implements IData {

	private final Texture originTexture;
	private double[] bulkComposition;
	private double[] activityModifiers;
	private double[] metals;
	private double weight;
	
	
	public RockData(Texture originTexture) {
		weight = 1;
		this.originTexture 	= originTexture;
		bulkComposition 		= new double[BulkComposition.values().length];
		activityModifiers 	= new double[ActivityModifiers.values().length];
		metals 			  	= new double[Metals.values().length];
	}
	
	public RockData(Texture originTexture,double[] bulkComposition, double[] activityModifiers, double[] metals) {
		weight = 1;
		this.originTexture 		= originTexture;
		this.bulkComposition 	= bulkComposition;
		this.activityModifiers 	= activityModifiers;
		this.metals 			  	= metals;
	}

	public void modifyData(double modifier) {
		weight*=modifier;
		modifyActivity(modifier);
	}

	private void modifyActivity(double modifier) {
		for(ActivityModifiers activity : ActivityModifiers.values()) {
			activityModifiers[activity.ordinal()]*=modifier;
		}
	}

	@Override
	public IData merge(IData data) {
		double otherWeight = data.get();
		if(otherWeight > this.weight) {
			return data.merge(this);
		}
		modifyActivities(data);
		modifyMetals(data);
		return this;
	}

	private void modifyMetals(IData data) {
		double[] otherMetals = data.getArrayValue(Metals.class);
		for(Metals metal : Metals.values()) {
			double m1 = this.metals[metal.ordinal()];
			double m2 = otherMetals[metal.ordinal()];
			if(m2 > m1) {
				this.activityModifiers[metal.ordinal()] = m2;
			}
		}
	}

	private void modifyActivities(IData data) {
		double[] otherActivity = data.getArrayValue(ActivityModifiers.class);
		for(ActivityModifiers activity : ActivityModifiers.values()) {
			double m1 = this.activityModifiers[activity.ordinal()];
			double m2 =	otherActivity[activity.ordinal()];
			if(m2 > m1) {
				this.activityModifiers[activity.ordinal()] = m2;
			}
		}
	}

	public double get() {
		return weight;
	}

	public double[] getArrayValue(Object o) {
		GeochemicalProperties property = (GeochemicalProperties)o;
		if(property.equals(GeochemicalProperties.Activities))
			return activityModifiers;
		else if(property.equals(GeochemicalProperties.Bulk_Composition))
			return bulkComposition;
		else {
			return metals;
		}
	}

	public Texture getTexture() {
		return originTexture;
	}
	
}
