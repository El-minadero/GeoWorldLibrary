package net.kevinmendoza.geoworldlibrary.geology.rockdata;

public class NullData implements IData {

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

	@Override
	public double[] getArrayValue(Object o) {
		return new double[3];
	}

	@Override
	public Texture getTexture() {
		return Texture.amorphous;
	}

	

}
