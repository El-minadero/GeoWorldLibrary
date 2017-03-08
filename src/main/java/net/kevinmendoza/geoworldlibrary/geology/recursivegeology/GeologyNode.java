package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GenerationData;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModificationFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;

public class GeologyNode extends AbstractGeologyNode implements CompositeGeologyInterface {

	private final CompositeGeologyInterface prototype;
	private final GeologyNodeCache map;

	GeologyNode(GeologyNodeBuilder builder) {
		super(builder.getPrototype());
		prototype = builder.getPrototype();
		map = new GeologyNodeCache(builder.getFactory(),builder.getSubObjectNumber(),prototype.getSuperRegion());
	}

	public final double getDecay(Vector2i vec) { return prototype.getDecay(vec); }
	public final double getDecay(Vector3i vec) { return prototype.getDecay(PointModificationFactory.extract2iVector(vec)); }
	public final boolean isLeaf() { return false; }
	public final boolean isVectorInRegion(Vector3i query) { return prototype.isVectorInRegion(query); }
	public final boolean isVectorInRegion(Vector2i center) { return prototype.isVectorInRegion(center); }
	public final Region getSuperRegion() { return prototype.getSuperRegion(); }

	public final void primeGeneration(GenerationData metaData) {
		clearSubObjectList();
		Vector2i query = metaData.get2DCoordinate();
		if(isVectorInRegion(query)) {
			this.addToInternalList(map.getOverlappingObjects(query));
			this.addToExternalList(map.getDistantObjects(query));
		}
		primeGenerationList(metaData);
	}
	public boolean shouldBuildRegion(Vector2i vec) {
		return false;
	}

}



