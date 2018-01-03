package net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

class StaticShiftModifier implements IPointModifier {

	private int x;
	private int y;
	private int z;
	
	StaticShiftModifier(int x, int y, int z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	@Override
	public Vector2i getPoint(Vector2i vec) {
		return new Vector2i(vec.getX()+x, vec.getY()+z);
	}

	@Override
	public Vector3i getPoint(Vector3i vec) {
		return new Vector3i(vec.getX()+x, vec.getY()+y,vec.getY()+z);
	}

}
