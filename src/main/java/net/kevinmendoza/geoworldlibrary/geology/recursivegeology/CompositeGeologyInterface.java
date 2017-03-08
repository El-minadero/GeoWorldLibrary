package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.Geology;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;

public interface CompositeGeologyInterface extends Geology {

	public boolean shouldBuildRegion(Vector2i vec);
	public double getDecay(Vector2i vec);
	public double getDecay(Vector3i vec);
	public boolean isVectorInRegion(Vector2i center);
	public boolean isVectorInRegion(Vector3i query);
	public Region getSuperRegion();
	boolean isLeaf();
}
