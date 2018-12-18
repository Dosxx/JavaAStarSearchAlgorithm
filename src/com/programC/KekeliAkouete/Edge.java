package com.programC.KekeliAkouete;


public class Edge implements Comparable<Edge>{
	
	private String name;
	private Node parentNode, adjacentNode;
	private int weight = 0;
	private int fValue = 0;
	
	// Edge constructor
	public Edge(Node u , Node v, int weight) {
		name ="("+ u.getName()+","+v.getName()+")";
		this.weight = weight;
		parentNode = u;
		adjacentNode = v;
	}
	
	public int getfValue() {
		return fValue;
	}
	public void setFValue(int h) {
		this.fValue = this.weight + h;
	}
	
	public String getName() {
		return name;
	}
	public void setName(Node n, Node m) {
		this.name = "("+ n.getName()+","+ m.getName()+")";
	}
	public int getWeigth() {
		return weight;
	}
	public void setWeigth(int weigth) {
		this.weight = weigth;
	}
	public Node getParentNode() {
		return parentNode;
	}
	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}
	public Node getAdjacentNode() {
		return adjacentNode;
	}
	public void setAdjacentNode(Node adjacentNode) {
		this.adjacentNode = adjacentNode;
	}
	
	@Override
	public String toString (){
		return this.name+this.fValue;
	}
	@Override
	public boolean equals(Object o) {
		if(o instanceof Edge) {
			Edge e = (Edge)o;
			return parentNode == e.parentNode && adjacentNode == e.adjacentNode && name == e.name;
		}
		return false;
	}
	@Override 
	public int compareTo(Edge i) {
		return this.fValue - i.fValue;
	}
}
