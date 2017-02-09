package net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;

class SmoothDomainMap implements NoiseMap {

	private NoiseMap map1;
	private NoiseMap map2;
	private double cutoff;
	private double reNorm;

	SmoothDomainMap(NoiseMap map1, NoiseMap map2, double cutoff) {
		this.map1=map1;
		this.map2=map2;
		this.cutoff = cutoff;
		this.reNorm = 1 - cutoff;
	}
	
	@Override
	public double getNoise(double x,double y,double z) {
		double threshVal = (map1.getNoise(x,y,z) + 1) / 2;
		if (threshVal < cutoff)
			return 0.0;
		else {
			return (threshVal - cutoff) * map2.getNoise(x,y,z) / reNorm;
		}
	}
	
	@Override
	public double getNoise(double x, double y) {
		return getNoise(x,0,y);
	}
	
	@Override
	public double getNoise(Vector3i vec) {
		return getNoise(vec.getX(),vec.getY(),vec.getZ());
	}

	@Override
	public double getNoise(Vector2i vec) {
		return getNoise(vec.getX(),0,vec.getY());
	}

	@Override
	public double getNoise(Vector3d vec) {
		return getNoise(vec.getX(),vec.getY(),vec.getZ());
	}

	@Override
	public double getNoise(Vector2d vec) {
		return getNoise(vec.getX(),0,vec.getY());
	}
	
}
