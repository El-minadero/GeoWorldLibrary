package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.shape;

import java.util.HashMap;
import java.util.Random;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;
import com.flowpowered.noise.model.Model;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.IPointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.IRegion;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingbox.BoundingBoxFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingbox.BoxType;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingbox.IBoundingBox;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingmodel.BoundingModelFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingmodel.IBoundingModel;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics.ConicFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics.ConicType;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics.IConic;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.relativelocation.IRelativeSpace;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.relativelocation.RelativeSpaceFactory;
import net.kevinmendoza.geoworldlibrary.utilities.HashCodeOperations;

public class ShapeFactory {
	  
	public static IRegion makeRegion(IBoundingModel model, IRelativeSpace modifier, Random rand) {
		return new Shape(model,modifier,rand);
	}
	
	public static IRegion makeEllipse(int a, int b) {
		Random rand = new Random(HashCodeOperations.createHash(a,b));
		int[] dims = {a,b};
		Vector2i vector2i = new Vector2i(0,0);
		IConic	conic = ConicFactory.createConic(ConicType.ELLIPSE,dims);
		IBoundingBox box = BoundingBoxFactory.createBoundingBox(BoxType.D2, dims, rand);
		IBoundingModel model 	= BoundingModelFactory.makeBoundingModel(conic, box);
		IRelativeSpace modifier 	= RelativeSpaceFactory.createRelative2DFrame(vector2i, rand, false);
		return makeRegion(model, modifier, rand);
	}
	
}
