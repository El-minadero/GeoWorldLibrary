package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingbox.BoxType;

public enum ConicType {
	ELLIPSE,RECTANGLE,
	ELLIPSOID{
		@Override
		public BoxType getBoundingBoxType() { return BoxType.D3; } 
		@Override
		public int getConstraintNumber() { return 3; } 
	},
	BOX{
		@Override
		public BoxType getBoundingBoxType() { return BoxType.D3; } 
		@Override
		public int getConstraintNumber() { return 3; } 
	};


	public BoxType getBoundingBoxType() {
		return BoxType.D2;
	}
	
	public int getConstraintNumber() {
		return 2;
	}
	
	
}
