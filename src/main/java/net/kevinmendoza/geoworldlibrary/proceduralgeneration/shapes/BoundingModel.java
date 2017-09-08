package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public class BoundingModel {

	private static final double EDGE_CUTOFF = 0.1;
	private final IBoundingBox box;
	private final IConic conic;
	
	BoundingModel(IBoundingBox box, IConic conic){
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
		double residual = conic.getResidual(localPoint);
		double rms = conic.getInvRootMeanAxis();
		return residual*rms;
	}

	public double getNormalizedDistanceToEdge(Vector3i localPoint) {
		double residual = conic.getResidual(localPoint);
		double rms = conic.getInvRootMeanAxis();
		return residual*rms;
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
		return " box:" +box.toString()+ " conic:"+ conic.toString();
	}

}
