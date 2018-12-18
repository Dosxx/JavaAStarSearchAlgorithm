package com.programC.KekeliAkouete;

import java.util.ArrayList;
import java.util.Collections;

public class Node {
	private String name;
	private int HValue;
	private ArrayList<Edge> edge;
	
	//Node constructor
	public Node (String node_name) {
		name = node_name;
		HValue = 0;
		edge = new ArrayList<>();
	}
	//setting and retrieving edges associated with a vertex
	public ArrayList<Edge> getEdge() {
		return edge;
	}
	
	public void setEdge(ArrayList<Edge> edgelist) {
		for(Edge v: edgelist) {
			if(v.getParentNode()== this) {
				v.setFValue(HValue);
				edge.add(v);
			}
		}
		Collections.sort(edge);
	}
	
	//accessors and mutators methods
	public void setHValue(int hValue) {
		HValue = hValue;
	}
	public int getHValue() {
		return HValue;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//Overriding the toString method of Object class
	@Override
	public String toString() {
		return this.name;
	}
}
