package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import java.util.Random;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

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
		else if(type.equals(RegionTypes.ELLIPSOID))
			return MakeEllipsoid(vec,a,b,t);
		else
			return MakeRectangle(vec,a,b,t);
	}
	
	public static Region MakeEllipsoid(Vector3i vec, double a, double b,
			double c) {
		return new Ellipsoid(vec,a,b,c);
	}
	
	private static Region MakeEllipsoid(Vector2i vec, double a, double b,
			double c) {
		return new Ellipsoid(new Vector3i(vec.getX(),0,vec.getY()),a,b,c);
	}

	public static Region MakeRegionOffsetByMap(Region region, PointModifier modifier) {
		return new OffsetRegion(region,modifier);
	}
	
}
