package net.kevinmendoza.geoworldlibrary.geology.compositerockdata;

import java.awt.Color;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractAlteration;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractRock;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.GeoWorldAlteration;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.GeoWorldRock;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.IAlterationBuilder;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.IRockBuilder;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISingularGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISurfaceBuilder;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.Surface;

public class EmptyDataFactory {
	
	private static GeoWorldRock.Builder RBuilder;
	private static GeoWorldAlteration.Builder ABuilder;
	
	public static AbstractRock getRock() {
		if(RBuilder==null) {
			RBuilder = new GeoWorldRock.Builder().setName("");
		}
		return RBuilder.build();
	}

	
	public static AbstractAlteration getAlteration() {
		if(ABuilder==null) {
			ABuilder = new GeoWorldAlteration.Builder();
		}
		return ABuilder.Build();
	}

	public static ISingularGeologyData getEmptyDataObject(int id) {
		if(id==1) {
			return null;
		}
		else if(id==2) {
			return getAlteration();
		}
		else {
			return getRock();
		}
	}
	
	
	protected static class Surf extends Surface {

		public Surf(ISurfaceBuilder builder) {
			super(builder);
		}
		
		public void applyMultiplier(double multiplier) {
		}

		@Override
		public void merge(ISingularGeologyData data, double mergeWeight) {
			
		}

		@Override
		public void merge(ISingularGeologyData data) {
			
		}
		public final int toRGBCode() { return 0; }
		
	}
	
	
}
