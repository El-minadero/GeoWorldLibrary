package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.shape;

import java.util.Random;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.IRegion;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingmodel.IBoundingModel;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.relativelocation.IRelativeSpace;
 
class Shape implements IRegion {
	
	private final IBoundingModel model;
	private final IRelativeSpace pointModifier;
	private final Random random;
	
	Shape(IBoundingModel model, IRelativeSpace pointModifier, Random random){
		this.model = model;
		this.random = random;
		this.pointModifier = pointModifier;
	}
	@Override
	public double getDouble() { return random.nextDouble(); }
	@Override
	public int getInt(int i) { return random.nextInt(i); }
	@Override
	public double getArea() { return model.getArea(); }
	@Override
	public boolean isOverXDim(Vector3i vec) { 
		Vector3i local = pointModifier.getLocalPoint(vec);
		return model.isOverXDim(local);
	}
	@Override
	public boolean isOverYDim(Vector3i vec) { 
		Vector3i local = pointModifier.getLocalPoint(vec);
		return model.isOverXDim(local);
	}
	@Override
	public boolean isOverZDim(Vector3i vec) { 
		Vector3i local = pointModifier.getLocalPoint(vec);
		return model.isOverXDim(local);
	}
	@Override
	public boolean isOverXDim(Vector2i vec) { 
		Vector2i local = pointModifier.getLocalPoint(vec);
		return model.isOverXDim(local);
	}
	@Override
	public boolean isOverYDim(Vector2i vec) { 
		Vector2i local = pointModifier.getLocalPoint(vec);
		return model.isOverXDim(local);
	}
	@Override
	public boolean isOverZDim(Vector2i vec) { 
		Vector2i local = pointModifier.getLocalPoint(vec);
		return model.isOverXDim(local);
	}
	@Override
	public Vector2i getRandom2iPoint() {
		Vector2i modelPoint = model.getRandom2iPoint();
		return pointModifier.getGlobalPoint(modelPoint);
	}
	@Override
	public Vector3i getRandom3iPoint() {
		Vector3i modelPoint = model.getRandom3iPoint();
		return pointModifier.getGlobalPoint(modelPoint);
	}
	@Override
	public boolean isInside(Vector2i vec) {
		Vector2i localPoint = pointModifier.getLocalPoint(vec);
		return model.isInside(localPoint);
	}
	@Override
	public boolean isInside(Vector3i vec) {
		Vector3i localPoint = pointModifier.getLocalPoint(vec);
		return model.isInside(localPoint);
	}

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

	@Override
	public Vector2i getCenter2i() { return pointModifier.getCenter2i(); }
	@Override
	public Vector3i getCenter3i() { return pointModifier.getCenter3i(); }

	@Override
	public double getDistanceToCenter(Vector3i vec) {
		return pointModifier.getDistanceToCenter(vec);
	}

	@Override
	public double getDistanceToCenter(Vector2i vec) {
		return pointModifier.getDistanceToCenter(vec);
	}

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
		String s = "==========Shape Info=======\n" +
					model.toString() + "\n" +
					pointModifier.toString() + " \n";
		return s;
	}

	
	
}
