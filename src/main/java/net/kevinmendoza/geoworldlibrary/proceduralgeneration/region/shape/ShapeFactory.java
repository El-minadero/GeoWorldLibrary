package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.shape;

import java.util.HashMap;
import java.util.Random;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.IPointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.IRegion;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingbox.BoundingBoxFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingmodel.IBoundingModel;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics.ConicFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.relativelocation.IRelativeSpace;

public class ShapeFactory {
	  
	public IRegion makeRegion(IBoundingModel model, IRelativeSpace modifier, Random rand) {
		return new Shape(model,modifier,rand);
	}
}
