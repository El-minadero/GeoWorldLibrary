package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public interface IRegionFactory {

	Region getRegion(Vector2i vec, double[] axis, boolean b);

	Region getRegion(Vector3i vec, double[] axis, boolean b);
}
