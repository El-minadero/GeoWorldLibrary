package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import java.util.Random;

import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.RotationConvention;
import org.apache.commons.math3.geometry.euclidean.threed.RotationOrder;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.RandomInterface;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;
 
class Shape implements Region,RandomInterface {
	
	private final BoundingModel model;
	private final IRelativePointModifier pointModifier;
	private final Random random;
	
	Shape(BoundingModel model, IRelativePointModifier pointModifier, Random random){
		this.model = model;
		this.random = random;
		this.pointModifier = pointModifier;
	}

	public Vector2i getRandomInternalPoint2i() {
		Vector2i modelPoint = model.getRandom2iPoint();
		return pointModifier.getGlobalPoint(modelPoint);
	}
	public Vector3i getRandomInternalPoint3i() {
		Vector3i modelPoint = model.getRandom3iPoint();
		return pointModifier.getGlobalPoint(modelPoint);
	}

	public boolean isInside(Vector2i vec) {
		Vector2i localPoint = pointModifier.getLocalPoint(vec);
		return model.isInside(localPoint);
	}
	public boolean isInside(Vector3i vec) {
		Vector3i localPoint = pointModifier.getLocalPoint(vec);
		return model.isInside(localPoint);
	}

	public int getInt(int i) { return random.nextInt(i); }
	public double getDouble() { return random.nextDouble(); }

	@Override
	public double getNormalizedDistanceToEdge(Vector2i vec) {
		Vector2i localPoint = pointModifier.getLocalPoint(vec);
		return model.getNormalizedDistanceToEdge(localPoint);
	}

	@Override
	public double getNormalizedDistanceToEdge(Vector3i vec) {
		Vector3i localPoint = pointModifier.getLocalPoint(vec);
		return model.getNormalizedDistanceToEdge(localPoint);
	}

	@Override
	public boolean isOnEdge(Vector3i vec) {
		Vector3i localPoint = pointModifier.getLocalPoint(vec);
		return model.isOnEdge(localPoint);
	}

	@Override
	public boolean isOnEdge(Vector2i vec) {
		Vector2i localPoint = pointModifier.getLocalPoint(vec);
		return model.isOnEdge(localPoint);
	}

	public Vector2i getCenter2i() { return pointModifier.getGlobalCenter2i(); }
	public Vector3i getCenter3i() { return pointModifier.getGlobalCenter3i(); }

	public boolean equals(Object o) {
		boolean b = false;
		if(o.getClass().equals(this.getClass())) {
			Shape otherShape = (Shape)o;
			if(otherShape.model.equals(this.model) &&
					otherShape.pointModifier.equals(this.pointModifier)) {
				b = true;
			}
		}
		return b;
	}
	@Override
	public String toString() {
		return "model:" + model.toString() + " offset:" +pointModifier.toString() + " rand" + random.hashCode();
	}
	
}
