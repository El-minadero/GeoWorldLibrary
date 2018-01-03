package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.List;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.IData;

public interface IGeology {
	
	public IData getData(Vector2i vector2i);
	public IData getData(Vector3i vector3i);
	
	
}
