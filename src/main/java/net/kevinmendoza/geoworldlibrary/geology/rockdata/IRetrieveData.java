package net.kevinmendoza.geoworldlibrary.geology.rockdata;

public interface IRetrieveData {

	Texture getTexture();
	BulkComposition getComposition();
	double getModifierValue(ActivityModifiers modifier);
	double getMetalValue(Metals metal);
	double getWeight();
	boolean isNull();
	
}
