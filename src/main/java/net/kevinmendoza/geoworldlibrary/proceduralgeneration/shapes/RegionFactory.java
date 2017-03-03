package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import java.util.Random;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;

public class RegionFactory {
	
	private static Region MakeEllipse(Vector2i vec,double xAxis,
			double yAxis,double theta) {
		return new Ellipse(vec,xAxis,yAxis,theta);
	}
	
	private static Region MakeRectangle(Vector2i vec,double xAxis,
			double yAxis,double theta) {
		return new Rectangle(vec,xAxis,yAxis,theta);
	}
	
	/**
	 * 
	 * @param type the RegionType to make
	 * @param vec  the Region Center
	 * @param a	   the unrotated x axis length
	 * @param b	   the unrotated y axis length	
	 * @param t	   the xy plane rotation.
	 * @return
	 */
	public static Region MakeRegionType(RegionTypes type, Vector2i vec,
			double a, double b, double t) {
		if(type.equals(RegionTypes.ELLIPSE))
			return MakeEllipse(vec,a,b,t);
		else
			return MakeRectangle(vec,a,b,t);
	}
	
	public static Region MakeRegionOffsetByMap(Region region, PointModifier modifier) {
		return new OffsetRegion(region,modifier);
	}
}
