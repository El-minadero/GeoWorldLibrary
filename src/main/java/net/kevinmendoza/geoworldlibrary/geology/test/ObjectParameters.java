package net.kevinmendoza.geoworldlibrary.geology.test;

import java.util.Random;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModificationFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.RegionFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.RegionTypes;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMap;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex.NoiseMapFactory;

public interface ObjectParameters{

	double getExternalDecayConstant();

	double getInternalDecayConstant();

	Order getOrder();

	Region getRegion(Region superRegion);

	PointModifier getOffsetMap(Region superRegion);

	PointModifier getOffsetMap(Vector2i vec);

	Region getRegion(Vector2i vec);

	int getSubObjectNumber(Vector2i vec);

	NoiseMap getNoiseMap(Vector2i vec);


}
