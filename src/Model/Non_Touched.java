package Model;
import java.util.ArrayList;
import java.util.Collections;



public class Non_Touched {
	
	ArrayList<ArrayList<Integer>> loop ;
	ArrayList<ArrayList<Integer>> Non ;
	ArrayList<ArrayList<Integer>> NonReal ;
	int loopsgain ;
	int total = 0 ;
	
	tuning_path p ;
	
	
	
	public Non_Touched(ArrayList<ArrayList<Integer>> loops , tuning_path t){
		
		loop = new ArrayList<ArrayList<Integer>>();
		loopsgain = 0 ;
		this.loop = loops ;
		Non =  new ArrayList<ArrayList<Integer>>();
		NonReal =  new ArrayList<ArrayList<Integer>>();
		this.p = t ; 
		MageLoops();
	}
	
	
	private void MageLoops(){
		
		
		for (int i = 0; i < loop.size(); i++) {
			loopsgain += p.gain(loop.get(i)) ;
		}
		
		
		//////make 2 non touched//////
		NonTouched(loop);
		int size = NonReal.size() ;
		
		//////make 3 non touched//////
		nTHNontouched(NonReal,0);
		int size2 = NonReal.size();
		
		///generate more if we find////
		while(size2 > size){
			
			nTHNontouched(NonReal,size);
			size = size2;
			size2 = NonReal.size();
			
		}
		
		Finish();
		
	}
	
	
	private void NonTouched(ArrayList<ArrayList<Integer>> Given){
		
		Non.add(new ArrayList<Integer>());
		
		for (int i = 0; i < Given.size(); i++) {
			for (int j = i+1; j < loop.size(); j++) {
				if(Collections.disjoint(Given.get(i),loop.get(j))){
					
//					Non.add(new ArrayList<Integer>());
					int temp = p.gain(Given.get(i)) * p.gain(loop.get(j)) ;
					
//					Non.get(Non.size()-1).add(p.gain(Given.get(i)));
//					Non.get(Non.size()-1).add(p.gain(loop.get(j)));
					Non.get(Non.size()-1).add(temp);
					
					
					
					
					NonReal.add(new ArrayList<Integer>());
					
					
					for (int k = 0; k < Given.get(i).size(); k++) {
						NonReal.get(NonReal.size()-1).add(Given.get(i).get(k));
					}
//					NonReal.get(NonReal.size()-1).add(null);
					NonReal.get(NonReal.size()-1).addAll(loop.get(j));
					
				}
			}
		}
//		Non.add(null);
		
		for (int i = 0; i < NonReal.size(); i++) {
//			System.out.println(NonReal.get(i));
		}
//		System.out.println("////////////////////////////////////////////////////");
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	private void nTHNontouched(ArrayList<ArrayList<Integer>> Given, int index){
		
		Non.add(new ArrayList<Integer>());
		int s = Given.size();

		
		for (int i = index , k1 = 0; i < s ; i++ , k1 ++) {

			for (int j = 0; j < loop.size(); j++) {
				if(Collections.disjoint(Given.get(i),loop.get(j)) == true){
					
				
					ArrayList<Integer> h = new ArrayList<Integer>();
										
					for (int k = 0; k < Given.get(i).size(); k++) {

						h.add(Given.get(i).get(k));
					}
					
					h.addAll(loop.get(j));
					int flag = 0;
					for (int k = 0; k < Given.size(); k++) {
						if(NonReal.get(k).containsAll(h) && h.containsAll(NonReal.get(k))){
							flag = 1 ;
							break ;
						}
					}
					if(flag != 1){
						NonReal.add(h);
						int temp  = Non.get(Non.size()-2).get(k1) * p.gain(loop.get(j)) ;
						Non.get(Non.size()-1).add(temp);
					}
					

					
				}
			}
		}
		
		for (int i = 0; i < NonReal.size(); i++) {
//			System.out.println(NonReal.get(i));
		
	}
//		System.out.println("////////////////////////////////////////////////////////////////////");
}		
	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void Finish(){
	
		if(Non.get(Non.size()-1).size() == 0){
			Non.remove(Non.size()-1);
		}
		
		for (int i = 0; i < Non.size(); i++) {
//			System.out.println(Non.get(i));
		}
		
	}


}
