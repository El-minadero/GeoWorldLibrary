package net.kevinmendoza.geoworldlibrary.proceduralgeneration.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class VoronoiGraph<V extends Vertex,E extends Edge> {

	private HashMap<String,Vertex> vertices;
	private HashMap<String,Edge> edges;
	
	public VoronoiGraph(){
		vertices = new HashMap<>();
		edges 	 = new HashMap<>();
	}
	
	public VoronoiGraph(List<V> nodes){
		vertices = new HashMap<>();
		for(V node : nodes){
			vertices.put(node.toString(), node);
		}
	}

	public boolean addEdge(V o1, V o2) {
		Vertex one = vertices.get(o1.toString());
		Vertex two = vertices.get(o2.toString());
		if(one.equals(two)){
			return false;  
		}
		Edge e = new Edge(one, two);
		if(one.containsNeighbor(e) || two.containsNeighbor(e)){
			return false;
		}
		else {
			edges.put(e.label(), e);
			one.addNeighbor(e);
			two.addNeighbor(e);
		}
		return true;
	}

	public V getVertex(String key) {
		return (V)this.vertices.get(key);
	}
	
	public Set<String> vertexKeys(){
		return this.vertices.keySet();
	}

	public Set<String> getNeighborsOf(String key) {
		Vertex center = vertices.get(key);
		HashSet<String> connected = new HashSet<String>();
		for(Edge e : center.getNeighbors()){
			connected.add(e.getNeighbor(center).getLabel());
		}
		return connected;
	}
}
	
