package net.kevinmendoza.geoworldlibrary.geology.geologicobject;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.GeoConditions;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;

public interface GeologicObjectInterface {

	public boolean isInSuperRegion(Vector2i center);
	
	public Region getRegion();
	
	public GeoConditions getConditions(Vector2i query);
}
