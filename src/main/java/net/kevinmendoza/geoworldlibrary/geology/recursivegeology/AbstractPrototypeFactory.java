package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

import java.util.Random;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.geology.Geology;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes.Region;

public abstract class AbstractPrototypeFactory extends AbstractFactory {

	public abstract GeologyComposite makePrototype(Region superRegion);
	public abstract GeologyComposite makePrototype(Vector2i vec);
	public abstract GeologyComposite makeObject(GeologyPrototype prototype);
	public abstract Region makeRegion(Vector2i vec);

	
}
