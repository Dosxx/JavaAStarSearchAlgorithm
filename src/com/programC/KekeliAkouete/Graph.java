package com.programC.KekeliAkouete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;


/**
*
* @author kekeli D Akouete
* ICS-340 FALL 2018-2019
* Program C
* Due date 11/14/2018
* This class define a graph and its attributes and the functions that operate on the graph
* 
*/

public class Graph {
	protected ArrayList<Node> nodes;
	protected int FValue;
	protected Map<Node, ArrayList<Node>> adjList;
	PriorityQueue<Edge> queue;
	protected ArrayList<Node> path;
	ArrayList<Edge> expandedNode;
	ArrayList <String> queueList;
//	ArrayList <PriorityQueue<Edge>> queueList;
	
	
	//Graph constructor
	public Graph(ArrayList<Node> allNode, ArrayList<Edge> allEdge) {
		nodes = allNode;
		adjList = new LinkedHashMap<>();
		FValue = 0;
		//Set a map of all the nodes in the graph
		for(Node u : nodes){
			u.setEdge(allEdge);
			adjList.put(u, new ArrayList<Node>());
			for(Edge v: u.getEdge()) {
				v.setFValue(v.getAdjacentNode().getHValue());
				ArrayList<Node> adjNode = adjList.get(u);
					adjNode.add(v.getAdjacentNode());
			}
		}
	}
	
	//public accessors and mutators
	public ArrayList<Node> getNodes() {
		return nodes;
	}
	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

	//search for a node in Nodes list by a letter
	public Node findNode(String letter) {
		Node node = null;
		boolean found = false;
		while(!found) {
			for(Node u: nodes) {
				if(u.getName().equals(letter.toUpperCase())) {
					node = u;
					found = true;
				}
			}
		}
		return node;
	}
		
	//A* search implementation
	public ArrayList<Node> AStarSearch(String node) {
		queue = new PriorityQueue<>();
		path = new ArrayList<>(); 
		expandedNode = new ArrayList<>();
		queueList = new ArrayList<>();
		
		//setting the start and goal node from input
		Node goal = findNode(node);
		boolean found = false;
		//Always start from node A
		Node start = nodes.get(0);
		//add start node frontier to the queue
		queue.addAll(start.getEdge());
		queueList.add("\r"+queue.toString()+"\n");
		
		while(!queue.isEmpty() && !found) {
			if(queue.element().getAdjacentNode().equals(goal)) {
				found = true;
				//we are done! add the adjacent node to the path
				expandedNode.addAll(queue);
				path.add(queue.poll().getAdjacentNode());
				while(!queue.isEmpty()) {
					Node frontier = queue.poll().getParentNode();
					if(!path.contains(frontier)) {
						path.add(frontier);
					}
				}
				Collections.reverse(path);
				return path;
			}
			//prune node expansion if goal is in the frontier of the start node
			else if(!queue.element().getAdjacentNode().equals(goal) && !found){
				Iterator<Edge> it = queue.iterator();
				while(it.hasNext()) {
					Edge current = it.next();
					if(current.getAdjacentNode().equals(goal)) {
						found = true;
						path.add(current.getAdjacentNode());
						path.add(current.getParentNode());
						expandedNode.addAll(queue);
						break;
					}
				}
				if(!found){
					//expand the frontier to the neighbor nodes with the smallest f value
					Node currentNode = queue.poll().getAdjacentNode();
					queue.addAll(currentNode.getEdge());
					queueList.add("\r"+queue.toString()+"\n");
				}
			}
		}
		Collections.reverse(expandedNode);
		Collections.reverse(path);
		return path;
	}
}
