package net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;

class SingleValueMap extends AbstractMap implements NoiseMap {
	
	final double value;
	
	SingleValueMap(double value){
		this.value = value;
	}

	@Override
	public double getNoise(double x, double y) {return value;}
	@Override
	public double getNoise(double x, double y, double z) {return value;}
	@Override
	public double getNoise(Vector3i vec) {return value;}
	@Override
	public double getNoise(Vector2i vec) {return value;}
	@Override
	public double getNoise(Vector3d vec) {return value;}
	@Override
	public double getNoise(Vector2d vec) {return value;}

}
