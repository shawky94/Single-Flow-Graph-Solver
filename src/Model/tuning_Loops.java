package Model;
import java.util.ArrayList;
import java.util.Collections;


public class tuning_Loops {
	
	private Integer [][] graph ;
	private tuning_path forward ;
	private tuning_path back;
	
	ArrayList<ArrayList<Integer>> loops ;
	
	public tuning_Loops(Integer [][] g, int start , int end ){
		this.graph = g ;
		loops = new ArrayList<ArrayList<Integer>>();
		forward = new tuning_path(graph);
//		transpose();
		back = new tuning_path(transpose());
		
		GenerateLoops(start , end);
		
	}
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	private void GenerateLoops(int start , int end){

		for (int i = start; i <= end; i++) {
			for (int j = i+1; j <= end; j++) {
				//****************************************all forward paths*********************************
				forward.set_path(i, j);
				//****************************************all backward paths********************************
				back.set_path(i, j);
				
				//****************************handle back paths ******************************************
				for (int k = 0; k < back.count; k++) {
					back.paths.get(k).remove(back.paths.get(k).size()-1);
					Collections.reverse(back.paths.get(k));
//					System.out.println(back.paths.get(k));
				}
				//******************************combine forward and backward to get loops*****************
				
				
				for (int j2 = 0; j2 < forward.count; j2++) {
					ArrayList<Integer> test = new ArrayList<>(forward.paths.get(j2));
						for (int k = 0; k < back.count; k++) {
							test.retainAll(back.paths.get(k));
							if(test.size() <= 1){
								
//								System.out.println(forward.paths.get(j2)+" "+back.paths.get(k));
								test.clear();
								test = new ArrayList<>(forward.paths.get(j2));
								test.addAll(back.paths.get(k));
								int flag = 0 ;
								for (int k2 = 0; k2 < loops.size(); k2++) {
									if(loops.get(k2).equals(test)){
										flag = 1 ;
										break ;
									}
								}
								if(flag == 0){
									loops.add(new ArrayList<Integer>());
									for (int k2 = 0; k2 < forward.paths.get(j2).size(); k2++) {
										loops.get(loops.size()-1).add(forward.paths.get(j2).get(k2));
									}
									loops.get(loops.size()-1).addAll(back.paths.get(k));
								}
							}
							test.clear();
							test = new ArrayList<>(forward.paths.get(j2));
						}
				}
				
			}
		}
		
		
		//*****************************************add self loops************************************
		
		for (int i = start; i <= end; i++) {
			if(graph[i][i] != null){
				
				loops.add(new ArrayList<Integer>());
				loops.get(loops.size()-1).add(i);
				loops.get(loops.size()-1).add(i);
				
			}
		}
		
		////////////////////////////////////////End Looping//////////////////////////////////////////////
		
//		System.out.println("----------------------------loops--------------------------------------------");
//		for (int i = 0; i < loops.size(); i++) {
//			System.out.println(loops.get(i));
//		}
//		
//		System.out.println("--------------------------Finish Loops----------------------------------------");
		
	}
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	/*
	 * reverse our graph to get backward simple paths
	 */
	private Integer[][] transpose(){
		Integer[][] gtanspose = new Integer[graph.length][graph[0].length];
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[0].length; j++) {
				Integer temp = graph[i][j];
			    gtanspose[i][j] = graph[j][i];
			    gtanspose[j][i] = temp;
			}
		}
		
		
		return gtanspose;
		
	}
}
