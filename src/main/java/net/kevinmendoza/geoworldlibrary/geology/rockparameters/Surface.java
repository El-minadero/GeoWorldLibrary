package net.kevinmendoza.geoworldlibrary.geology.rockparameters;

public abstract class Surface extends AbstractDataLeaf {

	private int surf;
	
	public Surface(ISurfaceBuilder builder) {
		super(builder.getOrder(),builder.getID());
		this.surf = builder.getSurface();
	}

	protected int getProtectedHeight() { return surf; }
	protected void setProtectedHeight(int h) { surf = h; }
	public abstract int getHeight();
	
	
}
