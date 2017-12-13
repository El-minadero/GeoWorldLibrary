package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import com.google.common.reflect.TypeToken;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.configuration.PrimitiveWrapperConfig.IntMinMax;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingbox.BoundingBoxFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingbox.IBoundingBox;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingmodel.BoundingModelFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingmodel.IBoundingModel;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics.ConicFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics.ConicType;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics.IConic;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.relativelocation.IRelativeSpace;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.relativelocation.RelativeSpaceFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.shape.ShapeFactory;
import net.kevinmendoza.geoworldlibrary.utilities.HashCodeOperations;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;


public abstract class AbstractRegionConfiguration {
	private final ShapeFactory shapeFactory;
	private final BoundingModelFactory modelFactory;
	
	protected final RelativeSpaceFactory rotationFactory;
	protected final ConicFactory conicFactory;
	protected final BoundingBoxFactory boxFactory;

	public AbstractRegionConfiguration(){
		shapeFactory 	= new ShapeFactory();
		rotationFactory 	= new RelativeSpaceFactory();
		conicFactory 	= new ConicFactory();
		modelFactory 	= new BoundingModelFactory();
		boxFactory 		= new BoundingBoxFactory();
	}
	
	protected IBoundingModel makeBoundingModel(IConic conic, IBoundingBox box) {
		return modelFactory.makeBoundingModel(conic, box);
	}
	protected IRegion makeRegion(IRelativeSpace modifier, IBoundingModel model, Random rand) {
		return shapeFactory.makeRegion(model,modifier,rand);
	}
	
	protected IBoundingModel makeDefaultBoundingModel(ConicType t,long seed,List<IntMinMax> list) {
		int[] dims 		= getDimensions(seed,list);
		IBoundingBox box	= boxFactory.createBoundingBox(t.getBoundingBoxType(), dims, new Random(seed));
		IConic conic 	= conicFactory.createConic(t, dims);
		return makeBoundingModel(conic,box);
	}
	
	protected int[] getDimensions(long seed,List<IntMinMax> list) {
		int[] dim = new int[3];
		for(int i = 0;i<list.size();i++) {
			dim[i] = list.get(i).getValue(seed);
		}
		return dim;
	}
}
