package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.RegionPointGenerator;

public interface IGeologyNode extends IGeology {

	public Vector2i getRandomInternalPoint();
	
	public double getExternalDecay(Vector2i vec);
	public double getExternalDecay(Vector3i vec);
	
	public double getInternalDecay(Vector2i vec);
	public double getInternalDecay(Vector3i vec);
	
	public boolean isVectorInRegion(Vector2i center);
	public boolean isVectorInRegion(Vector3i query);
	
	public String getName();
	boolean isLeaf();
	public int getSubOrder();
	public Order getOrder();
	
	public void debug();
	public boolean equals(Object o);
	public int hashCode();
}
