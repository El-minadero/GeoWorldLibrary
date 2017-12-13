package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;
import com.google.common.reflect.TypeToken;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.configuration.PrimitiveWrapperConfig.IntMinMax;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingbox.IBoundingBox;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingmodel.IBoundingModel;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics.ConicType;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics.IConic;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.relativelocation.IRelativeSpace;
import net.kevinmendoza.geoworldlibrary.utilities.HashCodeOperations;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public final class SimpleRegionConfiguration extends AbstractRegionConfiguration implements IRegionConfiguration {
	public static final TypeToken<SimpleRegionConfiguration> type = TypeToken.of(SimpleRegionConfiguration.class);
	@Setting
	private ConicType conicType;
	@Setting
	private List<IntMinMax> dimensions;
	@Setting
	private boolean randomlyRotate;
	
	public SimpleRegionConfiguration(ConicType type,List<IntMinMax> dimensions) {
		super();
		this.conicType 	= type;
		this.dimensions 	= dimensions;
		randomlyRotate = true;
	}
	public SimpleRegionConfiguration(ConicType type,int dimensions) {
		super();
		this.conicType 	= type;
		this.dimensions = new ArrayList<>();
		for(int i =0;i<type.getConstraintNumber();i++) {
			this.dimensions.add(new IntMinMax(dimensions,dimensions+1));
		}
		randomlyRotate = false;
	}

	@Override
	public IRegion getRegionFromSeed(long seed) {
		return makeRegionFromVectorAndSeed(seed,new Vector2i(0,0),new Vector3i(0,0,0));
	}

	@Override
	public IRegion getRegionFromVector2i(Vector2i vec) {
		long seed = HashCodeOperations.createVectorSeed(vec);
		Vector3i vec3 = new Vector3i(vec.getX(),0,vec.getY());
		return makeRegionFromVectorAndSeed(seed,vec,vec3);
	}

	@Override
	public IRegion getRegionFromVector3i(Vector3i vec3i) {
		long seed = HashCodeOperations.createVectorSeed(vec3i);
		Vector2i vec2i = new Vector2i(vec3i.getX(),vec3i.getZ());
		return makeRegionFromVectorAndSeed(seed,vec2i,vec3i);
	}

	@Override
	public IRegion getRegionFromVector2i(Vector2i vec, long seed1) {
		long seed = HashCodeOperations.createVectorSeed(vec)^seed1;
		Vector3i vec3 = new Vector3i(vec.getX(),0,vec.getY());
		return makeRegionFromVectorAndSeed(seed,vec,vec3);
	}

	@Override
	public IRegion getRegionFromVector3i(Vector3i vec3i, long seed1) {
		long seed = HashCodeOperations.createVectorSeed(vec3i)^seed1;
		Vector2i vec2i = new Vector2i(vec3i.getX(),vec3i.getZ());
		return makeRegionFromVectorAndSeed(seed,vec2i,vec3i);
	}
	
	private IRegion makeRegionFromVectorAndSeed(long seed, Vector2i vec2i, Vector3i vec3i) {
		Random rand = new Random(seed);
		IBoundingModel model = makeDefaultBoundingModel(conicType,seed,dimensions);
		IRelativeSpace modifier;
		if(conicType.getConstraintNumber()==2) {
			modifier = rotationFactory.createRelative2DFrame(vec2i, rand, randomlyRotate);
		}
		else {
			modifier = rotationFactory.createRelative3DFrame(vec3i, rand, randomlyRotate);
		}
		return super.makeRegion(modifier, model, rand);
	}
	
	

}
