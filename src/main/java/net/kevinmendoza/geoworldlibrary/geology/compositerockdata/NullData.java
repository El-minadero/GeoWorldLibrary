package net.kevinmendoza.geoworldlibrary.geology.compositerockdata;

class NullData implements IData {

	private double start = 1;
	@Override
	public void modifyData(double modifier) {
		start*=modifier;
	}

	@Override
	public IData merge(IData data) {
		if(data.get()>start) {
			start = data.get();
		}
		return this;
	}

	@Override
	public double get() {
		return start;
	}

	

}
