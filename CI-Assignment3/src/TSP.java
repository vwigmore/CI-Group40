import java.util.ArrayList;


public class TSP {

  public static String products = "Resources/tspproducts.txt";
  public static ArrayList<Product> productNodes = new Reader().parseProducts(products);
  public static ArrayList<Path> possiblepaths = new ArrayList<>();

  public static void main(String[] args) {

//    WalkingAnt.file = "Resources/hardmaze.txt";
//    Reader r = new Reader();
//    WalkingAnt.map = r.parseMaze(WalkingAnt.file);
//    for (int i = 0; i < productNodes.size(); i++) {
//      WalkingAnt.beginNode = productNodes.get(i).getNode();     
//      for (int j = 0; j < productNodes.size(); j++) {
//      	System.out.println("Combination: " + (i+1) * (j+1));
//        if (j > i) {         
//          WalkingAnt.endNode = productNodes.get(j).getNode();          
//          Ant ant = WalkingAnt.computePath();
//          Path path = new Path(productNodes.get(i),productNodes.get(j),ant.getPath().size());        
//          possiblepaths.add(path);
//        }
//      }
//    }
    
    ArrayList<Path> path = generateRandomPaths();
    Chromosome route = new GA().shortestPath(path, productNodes);
    System.out.println("Fitness: " + route.computeFitness(path));
    System.out.println("PathLenght: " + route.computePathLenght(path));
  }

  private static ArrayList<Path> generateRandomPaths() {
	  ArrayList<Path> res = new ArrayList<Path>();
	  for (int i = 0; i < productNodes.size(); i++) {
	      for (int j = 0; j < productNodes.size(); j++) {
	        if (j > i) {         
	        	int random = (int) (Math.random() * 100);
	          res.add(new Path(productNodes.get(i),productNodes.get(j), random));        
	        }
	      }
	    }
	  return res;
  }	

}
