package net.kevinmendoza.geoworldlibrary.geology.rockdata;

public class DataFactory {

	public static IData getDefaultData() {
		return new NullData();
	}
	
	public static IData getRockData(Texture texture,double[] composition, double[] activities, double[] metals) {
		return new RockData(texture,composition,activities,metals);
	}
}
