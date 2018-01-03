package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache;

import com.flowpowered.math.vector.Vector3i;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.IPointCloud;


public class CloudCacheBuilder {

	private ICacheFactory factory;
	private double searchRadius;
	private Vector3i center;
	private IPointCloud pointCloud;
	
	public CloudCacheBuilder() {
		
	}
	
	double getSearchRadius() 			{	return searchRadius;	}
	Vector3i getCenter3i() 				{	return center;		}
	ICacheFactory getFactory() 			{	return factory;		}
	IPointCloud getPointCloudGenerator() {	return pointCloud; 	}

	public CloudCacheBuilder setFactory(ICacheFactory factory) 	{ this.factory = factory; return this; 		}
	public CloudCacheBuilder setSearchRadius(double radius) 		{ this.searchRadius = radius; return this; 	}
	public CloudCacheBuilder setCenter(Vector3i center) 			{ this.center = center; return this; 		}
	public CloudCacheBuilder setPointCloudGenerator(IPointCloud pointCloud) 
																{ this.pointCloud=pointCloud; return this; 	}
	
	public ICache build() {
		return new CloudCache(this);
	}

}
