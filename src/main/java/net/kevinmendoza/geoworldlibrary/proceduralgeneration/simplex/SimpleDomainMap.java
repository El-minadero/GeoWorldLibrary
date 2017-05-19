package net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;

public class SimpleDomainMap extends AbstractMap {

	private NoiseMap map1;
	private final double cutoff;
	private final double inv;
	
	SimpleDomainMap(NoiseMap map1, double cutoff) {
		this.map1=map1;
		this.cutoff=cutoff;
		this.inv = 1-cutoff;
	}
	
	public double getNoise(double x, double y, double z){
		double domainMap = map1.getNoise(x, y, z);
		if (domainMap >=cutoff)
			return (domainMap - cutoff)/inv;
		else
			return 0.0;
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