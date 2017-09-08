package net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata;

public abstract class Surface extends AbstractGeologyData {

	private int surf;
	
	public Surface(ISurfaceBuilder builder) {
		super(builder);
		this.surf = builder.getSurface();
	}
	protected int getProtectedHeight() { return surf; }
	
	protected void setProtectedHeight(int h) { surf = h; }
	
	public int getHeight() { return surf; }
	
	public final String toString() {
		return "Surface: " + surf;
	}
	
	
}
