package com.programC.KekeliAkouete;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author kekeli D Akouete
 * ICS-340 FALL 2018-2019
 * Program C
 * Due date 11/14/2018
 * Description: This program will open a file chooser in the present directory 
 * read the adjacency matrix in a text file, implement A* search on the graph 
 * created from the input data.
 * Write the output as a list of the order in which the nodes are expanded,
 * showing the contents of the priority queue when each node is expanded to
 * the same directory.
 * 
 */

public class AStarSearchDemo {

	public static void main(String[] args)throws Exception {
		//Instantiate a JFilechooser object
		FileChooserJFC chooser = new FileChooserJFC();
		ArrayList<Node> path = new ArrayList<>();
		//Create a Graph instance
		Graph myGraph = chooser.readFile(chooser.chooseFile());
		String goalNode = JOptionPane.showInputDialog("Enter the goal node to be search");
		path = myGraph.AStarSearch(goalNode);
		//Formating the output to be written to a file
		String output = String.format("The expanded nodes: \n%s\n\n-\t-\t-\t-\t-\t-\n"+
										"The priority queue: \n%s\n\n-\t-\t-\t-\t-\t-\n"+
			"The Shortest path: \n%s", myGraph.expandedNode,myGraph.queueList,path);
		
		//write the output to a file
		chooser.writeToFile(output);		
	}
}