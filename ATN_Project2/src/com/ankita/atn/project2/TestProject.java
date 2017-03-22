package com.ankita.atn.project2;
/**
 * A program to find the minimum cut in an undirected graph
 * @author Ankita Patil
 *
 */
public class TestProject {
	static int m = 20;
	public static void main(String[] args) {
		
		/*
		 * Number of nodes are fixed
		 * Number of edges vary between 20 and 200 in steps of 4
		 */
		while(m <= 200){
		  for (int i = 0; i < 5; i ++){
		  
		  		Graph g = new Graph(21, m);
		  		g.createGraph();
		  		//System.out.println("Adjacency Matrix : Graph " + i + " with Nodes n = 21 and Edges m = "+ m );
		  		System.out.println("Graph with nodes n = 21 and edges m = " + m);
		  		//g.printGraph();
		  		System.out.println("Minimum cut of Graph "+ (i+1) +": " +g.calculateEdgeConnectivity());
		  		System.out.println();
		  }
		  
			m = m + 4;
		}
	}

}
