package Model;
import java.util.ArrayList;
import java.util.Collections;


public class Manger {
	
	private Integer[][] g ;
	
	private tuning_path t ;
	private tuning_Loops l ;
	
	ArrayList<Integer> pathsgain ;
	
	ArrayList<Integer> Deltas ;
	
	String layout = "" ;
	int r = 0 ;
	
	public Manger(Integer[][] graph , int start , int end){
		this.g = graph;
		
		pathsgain = new ArrayList<Integer>();
		Deltas = new ArrayList<Integer>();
		
		t = new tuning_path(g);
		l = new tuning_Loops(g, start , end);
		Do(start , end);
	}
	
	private void Do(int start , int end){
		
		t.set_path(start, end);
		
		for (int i = 0; i < t.paths.size(); i++) {
			pathsgain.add(t.gain(t.paths.get(i)));
		}
		
		//print all paths///////
		
		layout+="---------------All simple pathes------------------------------\n" ;
		for (int i = 0; i < t.paths.size(); i++) {
			layout+=t.paths.get(i)+"/"+pathsgain.get(i)+"\n" ;
		}
		layout+="--------------All Loops---------------------------------------\n\n";
		for (int i = 0; i < l.loops.size(); i++) {
			layout+=l.loops.get(i)+""+t.gain(l.loops.get(i))+"\n" ;
		}
		
		layout+="--------------------------------------------------------------\n";
		
		
		/*
		 * Calculating Delta and Delta s i 
		 */
		
		cDeltas(l.loops ,r++);
		
		for (int i = 0; i < t.paths.size(); i++) {
			ArrayList<ArrayList<Integer>> dloops = new ArrayList<ArrayList<Integer>>();
			for (int j = 0; j < l.loops.size(); j++) {
				if(Collections.disjoint(t.paths.get(i),l.loops.get(j))){
					dloops.add(l.loops.get(j));
				}
			}
			cDeltas(dloops, r++);
		}
		
		for (int i = 0; i < Deltas.size(); i++) {
			layout+= " Delta #"+i+" :"+" "+Deltas.get(i)+"\n" ;
		}
		
		layout+="The result is : "+result()+"\n" ;
		
	}
	
	//***********************************************  Delta evaluation Method  ********************************************************
	
	private void cDeltas(ArrayList<ArrayList<Integer>> loop , int r){
		
		Non_Touched u = new Non_Touched(loop, t);
		layout += "----------- non touched loops of Delat"+r+"--------------------------------\n";
		for (int i = 0; i < u.NonReal.size(); i++) {
			layout+=u.NonReal.get(i)+"\n" ;
		}
		int temp = 1- (u.loopsgain) ;
		
		for (int i = 0; i < u.Non.size(); i++) {
			if(i%2 == 0){
				for (int j = 0; j < u.Non.get(i).size(); j++) {
					temp += u.Non.get(i).get(j);
				} 
			}
			else{
				for (int j = 0; j < u.Non.get(i).size(); j++) {
					temp -= u.Non.get(i).get(j);
			}
		}
		
		Deltas.add(temp);
		}
		
	}
	
	public float result(){
		
		float nominator = 0 ;
		for (int i = 0; i < pathsgain.size(); i++) {
			nominator +=( pathsgain.get(i) * Deltas.get(i+1)) ;
		}
//		System.out.println(nominator+" "+Deltas.get(0));
		return  (nominator/Deltas.get(0));
	}
	
	public String lout(){
		return layout ;
	}
	
	

}
