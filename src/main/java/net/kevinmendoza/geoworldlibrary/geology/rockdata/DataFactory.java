package net.kevinmendoza.geoworldlibrary.geology.rockdata;

public class DataFactory {

	public static IData getDefaultData() {
		return new NullData();
	}
}
