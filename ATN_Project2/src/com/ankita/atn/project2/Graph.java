package com.ankita.atn.project2;

public class Graph {

	/*
	 * Number of nodes
	 */
	int n;
	
	/*
	 * Number of edges
	 */
	int m;
	
	/*
	 * Adjacency Matrix
	 */
	int M[][];
	
	
	/*
	 * Edge Connectivity between two sets A and B
	 */
	int edgeConnectivityAB;
	
	/*
	 * Edge Connectivity of the graph
	 */
	int edgeConnectivityGraph;
	
	public int a = -1;
	public int b = -1;
	
	public Graph(int n, int m) {
		this.n = n;
		this.m = m;
		M = new int[n][n];
	}
	
	/*
	 * Create graph with m edges
	 */
	public void createGraph() {
		
		//There are m edges
		for (int i = 0; i < m; i++) {
			
			//Since we need graph with random edges we use random function
			int nodeOne = (int)(Math.random() * n);
			int nodeTwo = (int)(Math.random() * n);
			
			//There cannot be self loop in graph
			while (nodeOne == nodeTwo) {
				nodeOne = (int)(Math.random() * 20);
				
			}
				
			M[nodeOne][nodeTwo] = 1;
			M[nodeTwo][nodeOne] = 1;
		}
	}
	
	/*
	 * Print Adjacency Matrix
	 */
	public void printGraph() {
		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M.length; j++) {
				System.out.print(M[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	/*
	 * This method calculates edge connectivity of graph using Nagamochi Ibaraki's algorithm
	 */
	public int calculateEdgeConnectivity() {
		
		//Base case
		if(n == 2)
			return M[0][1];
		else {
			// edge connectivity between two sets of nodes
			edgeConnectivityAB = MAOrder();			
			
			if(edgeConnectivityAB == 0) {
				System.out.println("Graph is disconnected");
				return 0;
			} else {
				//Merging the two sets of nodes, therefore we get a contracted graph
				Graph Gab = getContractedGraph();
				//System.out.println("New Graph (Contracted Graph): ");
				//Gab.printGraph();
				this.edgeConnectivityGraph = Math.min(edgeConnectivityAB, Gab.calculateEdgeConnectivity());
			}
			
			return edgeConnectivityGraph;
		}
		
		
	}
	
	/*
	 * This method calculates maximum adjacency ordering between verices
	 */
	
	public int MAOrder() {
		int maOrderAB = 0;
		
		//Array to store ordering of nodes
		int local[] = new int[n];
		
		//First node is selected at random from n nodes
		local[0] = (int) (Math.random() * n);
		//local[0] = 0;
		
		//This loop finds the ordering of remaining n-1 nodes
		for (int i = 1; i < local.length; i++) {
			
			int nodeToBeIncludedInSet = -1;
	    	int sum = 0;
	    	
	    	int nodeToBeTested = 0;
	    	while (nodeToBeTested < n) {
	    		
	    		int t = 0;
	    		boolean flag = true;
				for (int j = 0; j < i; j++) {
					if((nodeToBeTested == local[j])) {
						flag = false;
					}
				}
				if(flag) {
						
						for (int j2 = 0; j2 < i; j2++) {
							t += M[nodeToBeTested][local[j2]];
						}
						
					}
				
				if (t > sum)
	    		{
	    			nodeToBeIncludedInSet = nodeToBeTested;
	    			sum = t;
	    		}	
				nodeToBeTested++;
			}
	
	    	if(nodeToBeIncludedInSet == -1){
	  
				//System.out.println("Graph is a disconnected graph");
	    		return 0;
	    	}
	
	    	local[i] = nodeToBeIncludedInSet;
		}
		
		//Selecting last two nodes from the MA order in local array
		
		a = local[n-2];
		b = local[n-1];
		
		for(int z = 0; z < n; z++){
			maOrderAB = maOrderAB + M[b][z];
    	}
		
		//System.out.println("a = " + a + " B = " + b + " lambdaAB = " + maOrderAB);
		return maOrderAB;
	}
	
	/*
	 * This method generates a contracted graph
	 */
	
	public Graph getContractedGraph() {
		Graph newGraph = new Graph(n-1, m);
		newGraph.M = new int[n-1][n-1];
		
		int tempGraph[][] = new int[n][n];
		

		 //Copying the original graph in a temporary graph
		 
		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M.length; j++) {
				tempGraph[i][j] = M[i][j]; 
			}
		}
		
		
		  //Merging the values of two sets of nodes, by performing row wise and column wise addition
		 
		for (int i = 0; i < n; i++) {
			if(i != a){
				tempGraph[a][i] = tempGraph[a][i] + tempGraph[b][i];
				tempGraph[i][a] = tempGraph[i][a] + tempGraph[i][b];
				
			}
				
		}
		
		
		// Checking the condition to avoid self loops between two sets of nodes which are getting merged
		int p=0,q=0;
		
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				
				if((i != b)&&(j != b)){
					
					newGraph.M[p][q++]= tempGraph[i][j];
				}
			}
			
			if(i != b){
				p++;
				q=0;
			}
		}
    	

		return newGraph;
		
	}
	
}
