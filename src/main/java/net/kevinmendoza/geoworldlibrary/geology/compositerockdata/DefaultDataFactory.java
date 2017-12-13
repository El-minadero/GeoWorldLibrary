package net.kevinmendoza.geoworldlibrary.geology.compositerockdata;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractAlteration;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractDataTest;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractRock;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.GeoWorldAlteration;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.GeoWorldRock;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.GeoWorldTestData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.IAlterationBuilder;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.IRockBuilder;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISurfaceBuilder;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.Surface;

public class DefaultDataFactory extends EmptyDataFactory {

	private static GeoWorldRock.Builder RBuilder;
	private static SurfaceBuilder SBuilder;
	private static GeoWorldAlteration.Builder ABuilder;
	private static GeoWorldTestData.Builder TBuilder;
	
	public static AbstractRock getRock(String rock, double cutoff) {
		if(RBuilder==null) {
			RBuilder = new GeoWorldRock.Builder();
		}
		RBuilder.setCutoff(cutoff).setName(rock);
		return RBuilder.build();
	}
	
	public static AbstractDataTest getTestData(double val) {
		if(TBuilder==null) {
			TBuilder = new GeoWorldTestData.Builder();
		}
		TBuilder.setValue(val);
		return TBuilder.build();
	}
	
	public static Surface getSurface(int surf) {
		if(SBuilder==null) {
			SBuilder = new SurfaceBuilder();
		}
		SBuilder.setSurface(surf);
		return SBuilder.build();
	}
	
	public static AbstractAlteration getAlteration(double heat, double pressure, double hydrothermal, double weathering) {
		if(ABuilder==null) {
			ABuilder = new GeoWorldAlteration.Builder();
		}
		ABuilder.setHeat(heat).setPressure(pressure).setHydrothermal(hydrothermal).setWeathering(weathering);
		return ABuilder.Build();
	}

	
	private static class SurfaceBuilder implements ISurfaceBuilder {

		private int id=1;
		private int surface;
		
		public SurfaceBuilder setSurface(int surface) { this.surface = surface; return this; }
		
		public int getID() 		{ return id; }
		public int getSurface() { return surface; }
		
		public Surface build() {
			return new Surf(this);
		}
	}

}
