package net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata;

public class AbstractDataTest extends AbstractGeologyData {

	private double value;
	AbstractDataTest(IGeologyDataBuilder builder) {
		super(builder);
		value = 0;
	}

	@Override
	public void applyMultiplier(double multiplier) {
		value*=multiplier;
	}

	@Override
	public void merge(ISingularGeologyData data, double mergeWeight) {
		if(isMergable(data)) {
			AbstractDataTest test = (AbstractDataTest)(data);
			value = weightedDataMerge(value,test.getValue(),mergeWeight);
		}
	}

	double getValue() { return value; }

	@Override
	public void merge(ISingularGeologyData data) {
		if(isMergable(data)) {
			AbstractDataTest test = (AbstractDataTest)(data);
			double val2 = test.getValue();
			if(val2>value)
				value = val2;
		}
	}

	@Override
	public int toRGBCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
