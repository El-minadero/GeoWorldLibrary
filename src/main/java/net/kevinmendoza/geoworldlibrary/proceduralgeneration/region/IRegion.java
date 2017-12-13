package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingbox.IBoundingBox;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingmodel.IBoundingModel;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics.IConic;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.relativelocation.IPoint;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.relativelocation.IRelativeSpace;

public interface IRegion extends IBoundingModel,IPoint {

	public int getInt(int i);
	public double getDouble();

}
