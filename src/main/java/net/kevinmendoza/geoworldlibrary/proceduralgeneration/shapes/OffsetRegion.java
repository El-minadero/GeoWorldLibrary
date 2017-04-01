package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;

class OffsetRegion implements Region{

	private final Region innerRegion;
	private final PointModifier modifier;
	
	OffsetRegion(Region innerRegion,PointModifier modifier){
		this.innerRegion = innerRegion;
		this.modifier = modifier;
	}
	public final Vector2i getCenter() {
		return innerRegion.getCenter();
	}
	
	@Override
	public final int getInt(int i) {
		return innerRegion.getInt(i);
	}

	@Override
	public final double getDouble() {
		return innerRegion.getDouble();
	}

	@Override
	public final boolean isInside(Vector2i vec) {
		Vector2i vec2 = modifier.getOffsetPoint(vec);
		return innerRegion.isInside(vec2);
	}

	@Override
	public final Vector2i getRandomInternalPoint() {
		Vector2i internal = innerRegion.getRandomInternalPoint();
		return modifier.getOffsetPoint(internal);
	}

	@Override
	public final double getNormalizedDistanceToEdge(Vector2i vec) {
		Vector2i vec2 = modifier.getOffsetPoint(vec);
		return innerRegion.getNormalizedDistanceToEdge(vec2);
	}

	@Override
	public boolean isInside(Vector3i vec) {
		Vector3i vec3 = modifier.getOffsetPoint(vec);
		return innerRegion.isInside(vec3);
	}

	@Override
	public double getNormalizedDistanceToEdge(Vector3i vec) {
		Vector3i vec3 = modifier.getOffsetPoint(vec);
		return innerRegion.getNormalizedDistanceToEdge(vec3);
	}

	@Override
	public Vector2i getModifiedPoint(Vector2i vec) {
		return modifier.getOffsetPoint(vec);
	}

	@Override
	public Vector3i getModifiedPoint(Vector3i vec) {
		return modifier.getOffsetPoint(vec);
	}

}
