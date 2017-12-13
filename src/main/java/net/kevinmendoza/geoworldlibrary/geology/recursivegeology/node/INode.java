package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;

import java.util.List;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.Order;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractAlteration;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.AbstractRock;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.singleagedata.ISingularGeologyData;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.factory.AbstractPrototypeFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.factory.INodeFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.IRegion;

public interface INode extends IGeology {
	
	public String getName();
	boolean isLeaf();
	public INode convertToNode();
	public boolean equals(Object o);
	public int hashCode();
	
	public boolean isInside(Vector2i center);
	public boolean isInside(Vector3i query);
	
	public double getCenterDistance(Vector3i vec);
	public double getCenterDistance(Vector2i vec);
	
	public Vector2i getRandomInternalPoint2i();
	public Vector3i getRandomInternalPoint3i();
	
	public double getExternalDecay(Vector2i vec);
	public double getExternalDecay(Vector3i vec);

	
}
