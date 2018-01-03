package net.kevinmendoza.geoworldlibrary.geology.compositerockdata;

public class DataFactory {

	public static IData getDefaultData() {
		return new NullData();
	}
}
