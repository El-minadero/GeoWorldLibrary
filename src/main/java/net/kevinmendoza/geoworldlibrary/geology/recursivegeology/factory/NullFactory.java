package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.factory;

import java.util.HashMap;
import java.util.Random;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.INodeCache;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.CacheTestClass;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node.INode;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node.NodeBuilder;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.prototype.IPrototype;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.prototype.PrototypeBuilder;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.IRegion;
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

public class NullFactory implements INodeFactory {

	private static final String NAME = "NULL PROTOTYPE";
	private NodeBuilder nodeBuilder;
	private INodeCache cache;
	private IBoundingModel model;
	private Random rand;
	private RelativeSpaceFactory f4;
	private ShapeFactory f5;
	private PrototypeBuilder builder;

	public NullFactory(int[] dims) {
		nodeBuilder = new NodeBuilder();
		cache = new CacheTestClass();
		Random rand = new Random();
		ConicFactory f1 = new ConicFactory();
		BoundingBoxFactory f2 = new BoundingBoxFactory();
		BoundingModelFactory f3 = new BoundingModelFactory();
		f4 = new RelativeSpaceFactory();
		f5 = new ShapeFactory();
		IConic conic = f1.createConic(ConicType.BOX, dims);
		IBoundingBox box = f2.createBoundingBox(ConicType.BOX.getBoundingBoxType(), dims, rand);
		model = f3.makeBoundingModel(conic, box);
		builder = new PrototypeBuilder();
		builder.setSelfFactory(this);
	}
	@Override
	public INode makePrototype(Vector2i vec) {
		IRegion region = makeRegion(vec);
		return builder.setName(NAME).setRegion(region).buildNull();
	}

	
	public IRegion makeRegion(Vector2i vec) {
		IRelativeSpace space = f4.createRelative2DFrame(vec, rand, false);
		IRegion region = f5.makeRegion(model, space, rand);
		return region;
	}


	@Override
	public INode makeNode(IPrototype prototype) {
		return nodeBuilder.setPrototype(prototype).setCache(cache).build();
	}
	@Override
	public void setSeed(long seed) { }
	@Override
	public INode makePrototype(IRegion sourceRegion) {
		Vector2i vec = sourceRegion.getCenter2i();
		return makePrototype(vec);
	}
}
