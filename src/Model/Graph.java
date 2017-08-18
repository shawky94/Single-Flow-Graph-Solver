package Model;

public class Graph {
	
	private Integer[][] g ;
	
	public Graph(int Number_Of_Nodes){
		
		g = new Integer[Number_Of_Nodes][Number_Of_Nodes];	
	}
	
	
	public void Add_Edge(int source , int des , int weight){
		
		if(g[source][des] == null ){
			g[source][des] = weight ;
		}
		else{
			g[source][des] += weight ;
		}
	}
	
	public Integer[][] rGraph(){
		
		return g ;
	}

}
