package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingmodel;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingbox.IBoundingBox;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics.IConic;

class BoundingModel implements IBoundingModel {

	private static final double EDGE_CUTOFF = 0.1;
	private final IBoundingBox box;
	private final IConic conic;
	
	BoundingModel(IConic conic,IBoundingBox box){
		this.box = box;
		this.conic = conic;
	}

	public Vector2i getRandom2iPoint() {
		Vector2i point;
		while(true) {
			point = box.getRandomInternalPoint2i();
			if(conic.isInside(point)) {
				break;
			}
		}
		return point;
	}

	public Vector3i getRandom3iPoint() {
		Vector3i point;
		while(true) {
			point = box.getRandomInternalPoint3i();
			if(conic.isInside(point)) {
				break;
			}
		}
		return point;
	}

	public boolean isInside(Vector2i localPoint) {
		if(box.isInside(localPoint)) {
			if(conic.isInside(localPoint)) {
				return true;
			}
		}
		return false;
	}
	public boolean isInside(Vector3i localPoint) {
		if(box.isInside(localPoint)) {
			if(conic.isInside(localPoint)) {
				return true;
			}
		}
		return false;
	}

	public double getNormalizedDistanceToEdge(Vector2i localPoint) {
		return conic.getResidual(localPoint);
	}

	public double getNormalizedDistanceToEdge(Vector3i localPoint) {
		return conic.getResidual(localPoint);
	}

	public boolean isOnEdge(Vector3i localPoint) {
		double d = getNormalizedDistanceToEdge(localPoint);
		return (d<EDGE_CUTOFF);
	}

	public boolean isOnEdge(Vector2i localPoint) {
		double d = getNormalizedDistanceToEdge(localPoint);
		return (d<EDGE_CUTOFF);
	}
	@Override
	public String toString() {
		String s = "====Model Info====\n"
				+"Bounding box dimensions/2:" +box.toString() +"\n"
				+"Conic Model dimensions/2:"+ conic.toString();
		return s;
	}

	public boolean isOverXDim(Vector3i vec)   { 
		if(box.isOverXDim(vec)) {
			return true;
		}
		else {
			return !conic.isInside(vec);
		}
	}
	public boolean isOverYDim(Vector3i vec)   { 
		if(box.isOverYDim(vec)) {
			return true;
		}
		else {
			return !conic.isInside(vec);
		}
	}
	public boolean isOverZDim(Vector3i vec)   { 
		if(box.isOverZDim(vec)) {
			return true;
		}
		else {
			return !conic.isInside(vec);
		}
	}
	
	public boolean isOverXDim(Vector2i vec)   { 
		if(box.isOverXDim(vec)) {
			return true;
		}
		else {
			return !conic.isInside(vec);
		}
	}
	public boolean isOverYDim(Vector2i vec) 	 { 
		if(box.isOverYDim(vec)) {
			return true;
		}
		else {
			return !conic.isInside(vec);
		} 
	}
	public boolean isOverZDim(Vector2i vec)   { 
		if(box.isOverZDim(vec)) {
			return true;
		}
		else {
			return !conic.isInside(vec);
		} 
	}

	public double getArea() { return box.getArea(); }

}
