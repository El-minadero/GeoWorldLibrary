package net.kevinmendoza.geoworldlibrary.proceduralgeneration.graphs;

import java.util.ArrayList;

public abstract class Vertex {

		private ArrayList<Edge> neighborhood;

		Vertex(){
			this.neighborhood = new ArrayList<Edge>();
		}

		void addNeighbor(Edge edge){
			if(this.neighborhood.contains(edge)){
				return;
			}
			this.neighborhood.add(edge);
		}

		boolean containsNeighbor(Edge edge){
			return this.neighborhood.contains(edge);
		}

		void removeNeighbor(Edge edge){
			this.neighborhood.remove(edge);
		}

		abstract String getLabel();

		public ArrayList<Edge> getNeighbors() {
			return new ArrayList<Edge>(this.neighborhood);
		}

		@Override
		public int hashCode() {
			return this.getLabel().hashCode();
		}

		@Override
		public boolean equals(Object other){
			if(!(other instanceof Vertex)){
				return false;
			}
			Vertex v = (Vertex) other;
			return this.getLabel().equals(v.getLabel());
		}
}
