package net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.flowpowered.math.vector.Vector2d;

public interface IPointCloud {

	List<Vector2d> generatePointCloud();
}
