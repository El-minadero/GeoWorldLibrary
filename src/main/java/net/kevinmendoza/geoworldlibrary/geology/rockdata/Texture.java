package net.kevinmendoza.geoworldlibrary.geology.rockdata;

public enum Texture {
	// Igneous in origin
	Grained_Amorphous(true),Grained_Fine(true),Grained_Coarse(true),Grained_Pegmatitic(true),
	// Sedimenary in origin
	Clastic_Precipitate(false),Clastic_Fine(false),Clastic_Medium(false),Clastic_Coarse(false);
	
	public boolean igneous;
	
	Texture(boolean isIgneous) {
		this.igneous = isIgneous;
	}
}
