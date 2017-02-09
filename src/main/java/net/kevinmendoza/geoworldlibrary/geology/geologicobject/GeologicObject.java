package net.kevinmendoza.geoworldlibrary.geology.geologicobject;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;

public abstract class GeologicObject implements GeologicObjectInterface {

	private final Region region;

	public GeologicObject(GeologicObjectBuilderGetInterface builder) {
		region = builder.getRegion();
	}
	
	@Override
	public boolean isInSuperRegion(Vector2i center) {
		return region.isInside(center);
	}
	
	public Region getRegion() {
		return region;
	}

}
