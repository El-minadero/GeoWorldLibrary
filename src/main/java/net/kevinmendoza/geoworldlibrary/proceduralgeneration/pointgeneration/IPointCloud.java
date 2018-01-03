package net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;

public interface IPointCloud {

	Set<Vector2i> generatePointCloud();
	
	void setSeed(long seed);
}
