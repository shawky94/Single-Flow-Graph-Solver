package Model;
import java.util.ArrayList;


public class tuning_path {
	
	private Integer [][] graph ;
	ArrayList<ArrayList<Integer>> paths ;
	private ArrayList<Integer> helper ;
	private boolean [] visit ;
	int count = 0; 
	int c ;
	int s ;
	int e ;
	
	public tuning_path(Integer [][] g){
		
		this.graph = g ;
		visit = new boolean[g.length];
		paths = new ArrayList<ArrayList<Integer>>();
		helper = new ArrayList<Integer>() ;
		
//		get_path(0,2);
	}
	
	public void  set_path(int start , int end){
		paths.clear();
		helper.clear();
		count = 0 ;
		s= start ; e = end ;
		helper.add(s);
		get_path(start,end);
		
	}
	
	private void get_path(int is ,  int ie ){
		visit[is] = true ;
		if(is == ie ){

			count ++ ;
			paths.add(new ArrayList<Integer>());
//			for (int i = 0; i <visit.length; i++) {
//				if(visit[i]){
//					paths.get(count-1).add(i);
//				}
//				
//			}
			paths.get(paths.size()-1).addAll(helper);
//			paths.get(count-1).add(ie);
//			System.out.println(paths.get(count-1));
//			System.out.println("");
			
			return ;
		}
		
		for (int j = 0; j <= graph.length-1; j++) {
			if(graph[is][j] != null && !visit[j]){
//				visit[j] = true ;
				helper.add(j);
//				System.out.println(helper);
				get_path(j,ie);
				helper.remove(helper.size()-1);
				visit[j] = false ;
//				visit[is][j] = false ;
				
				
			}
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public int gain(ArrayList<Integer> path){
		int gain = 1 ;
		for (int i = 1; i < path.size(); i++) {
			gain *= graph[path.get(i-1)][path.get(i)];
		}
		return gain ;
	}
}


