package net.kevinmendoza.geoworldlibrary.geology.rockdata;

public enum Texture {
	// Igneous in origin
	amorphous(true),Fine_Grained(true),Coarse_Grained(true),Pegmatitic(true),
	// Sedimenary in origin
	precipitate(false),Fine_Clastic(false),Medium_Clastic(false),Coarse_Clasic(false);
	
	public boolean igneous;
	
	Texture(boolean isIgneous) {
		this.igneous = isIgneous;
	}
}
