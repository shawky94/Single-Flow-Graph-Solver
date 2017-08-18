package control;
import javax.swing.SwingUtilities;


import View.*;
import Model.* ;
public class merge {
 static Gui gui ;
 static Manger m;
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		 
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				gui = new Gui();
			}
		});
	
		try {
			
	        while (true) {
	            Thread.sleep(1 * 1000);
	            if(gui.calculateFlag==true){
	            play();
	            gui.calculateFlag=false;
	            gui.showresult(m.lout());
	            }
	        }
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
		
	}
	
	@SuppressWarnings({ "static-access" })
	public static void play(){
		/*
		 * creating the graph
		 */
		int start=gui.startingNode;
		int end=gui.endingNode;
		int makingUp=0;
		Graph g = new Graph(gui.noOfShape);
		for (int i = 0; i < gui.noOfEdges; i++) {
			if(gui.edges[i][9] != -1){
	
				g.Add_Edge(gui.edges[i][6]-makingUp, gui.edges[i][7]-makingUp, gui.edges[i][8]-makingUp);
			}
			
		}
		
//		mange t = new mange();
//		t.printMatrix(g.rGraph());
		
		 m = new Manger(g.rGraph(), start,end );
	}
	
	
	
}
