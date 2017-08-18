package Model;
import java.io.BufferedReader;
import java.io.FileReader;


public class mange {

	public static void main(String[] args) {

		
		Integer [][] graph = null ;
		try {
			graph = read_file();
			printMatrix(graph);
			Manger m = new Manger(graph, 1, 6);
			System.out.println(m.layout);
////			Path p = new Path(graph);
//			tuning_path p1 = new tuning_path(graph);
//			p1.set_path(0, graph.length-1);
//			
////			System.out.println("---------------Simple forward Paths------------------");
////			for (int i = 0; i < p1.paths.size(); i++) {
////				System.out.println(p1.paths.get(i));
////			}
////			System.out.println("------------------------------------------------------");
//			tuning_Loops l1 = new tuning_Loops(graph);
//			Non_Touched n = new Non_Touched(l1.loops , p1);
////			p1 .set_path(1, 2);
////			System.out.println()
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("resource")
	public static Integer[][] read_file() throws Exception{
		BufferedReader re = new BufferedReader(new FileReader("Graph.txt"));
		Integer[][] graph = null;
		
		 String line;
	        int row = 0;
	        int size = 0;

	        while ((line = re.readLine()) != null) {
	            String[] vals = line.trim().split("\\s+");

	            // Lazy instantiation.
	            if (graph == null) {
	                size = vals.length;
	                graph = new Integer[size][size];
	            }

	            for (int col = 0; col < size; col++) {
	                graph[row][col] = Integer.parseInt(vals[col]);
	            }

	            row++;
	        }

		
		
		return graph ;
	}
	
	//***************************************************print my graph****************************************************************
	
	
	public static void printMatrix(Integer[][] matrix) {
        String str = "";
        int size = matrix.length;

        if (matrix != null) {
            for (int row = 0; row < size; row++) {
                str += " ";
                for (int col = 0; col < size; col++) {
                    str += String.format("%2d",  matrix[row][col]);
                    if (col < size - 1) {
                        str += " | ";
                    }
                }
                if (row < size - 1) {
                    str += "\n";
                    for (int col = 0; col < size; col++) {
                        for (int i = 0; i < 4; i++) {
                            str += "-";
                        }
                        if (col < size - 1) {
                            str += "+";
                        }
                    }
                    str += "\n";
                } else {
                    str += "\n";
                }
            }
        }

        System.out.println(str);
    }
}
