package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.rockdata.IData;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.IDataFactory;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.IDecay;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.IPointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.IRegion;

public final class Prototype implements INode {

	private final IRegion region;
	private final String name;
	private final IDataFactory dataFactory;
	private final IPointModifier modifier;
	private final IDecay decay;
	
	Prototype(PrototypeBuilder builder) {
		dataFactory 	= builder.getDataFactory();
		region 		= builder.getRegion();
		name 		= builder.getName();
		modifier		= builder.getPointModifier();
		decay 		= builder.getDecay();
	}

	public final boolean isLeaf() 	{ return true; 	}
	
	public final String getName() 	{ return name;	}
	
	public final boolean isInside(Vector2i vector2i) 
									{ return region.isInside(modifier.getPoint(vector2i)); }
	public final boolean isInside(Vector3i vector3i) 	
									{ return region.isInside(modifier.getPoint(vector3i)); }
	public final double getCenterDistance(Vector3i vector3i) 
									{ return region.getDistanceToCenter(modifier.getPoint(vector3i)); }
	public final double getCenterDistance(Vector2i vector2i) 	
									{ return region.getDistanceToCenter(modifier.getPoint(vector2i)); }
	public final String getLocationData(Vector3i vec3) 	
									{ return region.toString(); }
	public final Vector2i getRandomInternalPoint2i()
									{ return region.getRandom2iPoint(); }
	public final Vector3i getRandomInternalPoint3i() 
									{ return region.getRandom3iPoint(); }

	public final IData getData(Vector2i vector2i) {
		Vector2i modifiedPoint = modifier.getPoint(vector2i);
		IData data = getDefaultData(modifiedPoint);
		double modifier = getExternalMultiplier(vector2i);
		data.modifyData(modifier);
		return data;
	}

	public final IData getData(Vector3i vector3i) {
		Vector3i modifiedPoint = modifier.getPoint(vector3i);
		IData data = getDefaultData(modifiedPoint);
		double modifier = getExternalMultiplier(vector3i);
		data.modifyData(modifier);
		return data;
	}
	
	public final IData getDefaultData(Vector2i vector2i) {
		Vector2i modifiedPoint = modifier.getPoint(vector2i);
		IData data = dataFactory.getData(modifiedPoint);
		return data;
	}

	public final IData getDefaultData(Vector3i vector3i) {
		Vector3i modifiedPoint = modifier.getPoint(vector3i);
		IData data = dataFactory.getData(modifiedPoint);
		return data;
	}

	public final double getExternalMultiplier(Vector2i vector2i) {
		Vector2i modifiedPoint = modifier.getPoint(vector2i);
		double distance = region.getNormalizedDistanceToEdge(modifiedPoint);
		return decay.getModifier(distance);
	}

	public final double getExternalMultiplier(Vector3i vector3i) {
		Vector3i modifiedPoint = modifier.getPoint(vector3i);
		double distance = region.getNormalizedDistanceToEdge(modifiedPoint);
		return decay.getModifier(distance);
	}

}
