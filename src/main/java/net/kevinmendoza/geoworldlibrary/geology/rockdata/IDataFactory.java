package net.kevinmendoza.geoworldlibrary.geology.rockdata;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

public interface IDataFactory {

	IData getData(Vector2i modifiedPoint);

	IData getData(Vector3i modifiedPoint);

}
