package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

public enum RegionTypes {
	ELLIPSE{
		@Override
		public boolean is3D() { return false; }
		@Override
		public IRegionFactory createFactory() { return new EllipseFactory(); }
	},
	RECTANGLE{
		@Override
		public boolean is3D() { return false; }
		@Override
		public IRegionFactory createFactory() { return new RectangleFactory(); }
	},
	ELLIPSOID {
		@Override
		public int getConstraintNumber() { return 3; }
		@Override
		public IRegionFactory createFactory() { return new EllipsoidFactory(); }
	},
	REC_PRISM {
		@Override
		public int getConstraintNumber() { return 3; }
		@Override
		public IRegionFactory createFactory() { return new RectangularPrismFactory(); }
	};
	public IRegionFactory createFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getConstraintNumber() { return 2; }
	public boolean is3D() { return true; }
}
