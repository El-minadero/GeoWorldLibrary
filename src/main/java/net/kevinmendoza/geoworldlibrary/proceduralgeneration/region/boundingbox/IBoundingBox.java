package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingbox;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public interface IBoundingBox {

	public Vector2i getRandomInternalPoint2i();

	public Vector3i getRandomInternalPoint3i();

	public boolean isInside(Vector2i localPoint);
	
	public boolean isInside(Vector3i localPoint);

	public boolean isOverXDim(Vector3i vec);
	public boolean isOverYDim(Vector3i vec);
	public boolean isOverZDim(Vector3i vec);
	public boolean isOverXDim(Vector2i vec);
	public boolean isOverYDim(Vector2i vec);
	public boolean isOverZDim(Vector2i vec);

	public double getArea();
}
