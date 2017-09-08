package net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata;

public interface IAlterationBuilder extends IGeologyDataBuilder {
	double getHeat();
	double getPressure();
	double getWeathering();
	double getHydrothermal();
}
