package com.programC.KekeliAkouete;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
*
* @author kekeli D Akouete
* ICS-340 FALL 2018-2019
* Program C
* Due date 11/14/2018
* This class will open the file chooser dialog box and read the content as well as
* write back to an output file in the same directory.
* 
*/

public class FileChooserJFC {
	protected String fileName, outputFileName;
	protected JFileChooser chooser;
	protected File outPutFile;
	
	//File chooser constructor
	public FileChooserJFC() {
		fileName = "";
		outputFileName = "";
		outPutFile = null;
	}
	
	public File chooseFile() throws IOException {
		//Specify the current directory for the file chooser()
        File currentDir = new File(System.getProperty("user.dir")+"/src");
        this.chooser = new JFileChooser(currentDir);
        
        //filter on files with .text extension
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".TXT files", "txt");
        this.chooser.setFileFilter(filter);
        
        //open the file chooser dialog box
        int status = chooser.showOpenDialog(null);
        if(status == JFileChooser.APPROVE_OPTION) {
        	
            //Construct the output file name
            this.fileName =  chooser.getSelectedFile().getName();
            this.outputFileName = fileName.replaceAll(".txt", "_out.txt");
        }
        return chooser.getSelectedFile();
	}
	
	public void writeToFile(String text) throws IOException {
		
		//path and construct of the output file
		this.outPutFile = new File(System.getProperty("user.dir")+"/src/"+ this.outputFileName);
		//Instantiate a PrintWriter object
		PrintWriter writer = new PrintWriter(new BufferedWriter(
				new FileWriter(outPutFile)), false);
		//Write output stream to a output file
		writer.println(text);
		writer.close();
		
		//Message to display after the operation are done
		String message = String.format("The %s has been written successfully! \n" 
				+"Please refresh the project file to see it", outputFileName);
		JOptionPane.showMessageDialog(null, message);
	}
	
	public int getLine(File input) throws IOException {
		int lineNumber = 0;
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
		@SuppressWarnings("unused")
		int line = 0;
		while((line = reader.read())!= -1) {
			reader.readLine();
			lineNumber++;
		}
		reader.close();
		return lineNumber;
	}
	
	//Read the content of the chosen file from the source directory
	public Graph readFile(File input) throws IOException {
		//local variables to the read method
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
		ArrayList<Node> vertices = new ArrayList<>();
		ArrayList<Edge> edges = new ArrayList<>();
		int vertex = 0;
		Graph myGraph;
		
		int fileIndex = getLine(input);
		
		//Read the first line and make a node out of each line input
		String [] nodes = reader.readLine().substring(2).split("\\s");
		for(int i = 0; i < nodes.length; i++) {
			vertices.add(new Node(nodes[i]));
		}
		
		//create edges from the adjacency list
		for(int i = 0; i < fileIndex-1; i++) {
			
			//check if the line start with "h" parse the heuristic value
			String [] nextline = reader.readLine().replace(".", "-1").split("\\s");
			if(nextline[0].equals("h")) {
				for(int h =1; h < nextline.length; h++) {
					vertices.get(h-1).setHValue(Integer.parseInt(nextline[h]));
				}
			}else {
				for(int j = 1; j < nextline.length; j++) {
					int weight = Integer.parseInt(nextline[j]); 
					if(weight != -1){
						edges.add(new Edge(vertices.get(vertex),vertices.get(j-1),weight));
					}
				}
			vertex++;
			}
		}
		myGraph = new Graph(vertices, edges);
		reader.close();
		return myGraph;			
	}
}
