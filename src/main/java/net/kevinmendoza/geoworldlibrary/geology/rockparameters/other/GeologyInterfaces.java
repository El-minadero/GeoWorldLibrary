package net.kevinmendoza.geoworldlibrary.geology.rockparameters.other;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;

public class GeologyInterfaces {

	
	public static class Replacement {
		
	}
	
	public static class Surface implements GeologyData<Surface> {
		
		private int surface;
		private Order order;

		public Surface(Order order) { this.order = order; }
		private Surface(Surface surface) { this.surface = surface.surface; this.order = surface.order; }
		
		public Surface getCopy() 	{ return new Surface(this); }
		public Order getOrder() 	{ return order; }
		public Surface getObject()  { return this; }
		public void setSurface(int surf) { this.surface = surf; }
		public int getSurface() 	{ return surface; }
		@Override
		public void merge(GeologyData<Surface> object) {
			if(object.getClass().equals(this.getClass())) {
				Surface otherSurf = (Surface)object;
				int s = otherSurf.getSurface();
				if(s > surface)
					surface = s;
			}
		}

		public Surface getObject(Order order) {
			if(order.equals(this.order))
				return this;
			else
				return null;
		}
		@Override
		public GeologyData<Surface> getValue(Order order) {
			return getObject(order);
		}
	}
}
