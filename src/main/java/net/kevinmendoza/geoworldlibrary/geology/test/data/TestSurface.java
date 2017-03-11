package net.kevinmendoza.geoworldlibrary.geology.test.data;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydata.GeologyData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydatainterface.Surface;
class TestSurface extends Surface {

	private int height;

	public TestSurface(int height) {
		this.height = height;
	}

	
	@Override
	public Surface get() {
		return this;
	}

	@Override
	public void merge(GeologyData<Surface> t, double mergeWeight) {
		height = (int) weightedCompare(height, t.get().getHeight(), mergeWeight);
	}

	@Override
	public int getHeight() {
		return height;
	}

}
