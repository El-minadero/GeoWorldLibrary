package net.kevinmendoza.geoworldlibrary.geology.test.data;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydata.GeologyData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydatainterface.Surface;
class TestSurface implements Surface {

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
		Surface surface = t.get();
		height = (int)((1-mergeWeight)*(double)(height) + mergeWeight*(double)(surface.getHeight()));
	}

	@Override
	public int getHeight() {
		return height;
	}

}
