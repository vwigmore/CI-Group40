import java.util.ArrayList;


public class TSP {

  public static String products = "Resources/tspproducts.txt";
  public static ArrayList<Product> productNodes = new Reader().parseProducts(products);
  public static ArrayList<Path> possiblepaths = new ArrayList<>();

  public static void main(String[] args) {

      WalkingAnt.file = "Resources/hardmaze.txt";
      Reader r = new Reader();
      WalkingAnt.map = r.parseMaze(WalkingAnt.file);
    for (int i = 0; i < productNodes.size(); i++) {
      WalkingAnt.beginNode = productNodes.get(i).getNode();     
      for (int j = 0; j < productNodes.size(); j++) {
      	System.out.println("Combination: " + (i+1) * (j+1));
        if (j > i) {         
          WalkingAnt.endNode = productNodes.get(j).getNode();          
          Ant ant = WalkingAnt.computePath();
          Path path = new Path(productNodes.get(i),productNodes.get(j),ant.getPath().size());        
          possiblepaths.add(path);
        }
      }
    }
    
    //ArrayList<Path> possiblepaths = generateRandomPaths();
    Chromosome route = new GA().shortestPath(possiblepaths, productNodes);
    System.out.println("Fitness: " + route.computeFitness(possiblepaths));
    System.out.println("PathLenght: " + route.computePathLenght(possiblepaths));
    
    ArrayList<Ant> ants = new ArrayList<Ant>();
    int pathLength = 0;
    for (int i = 0; i < route.getProducts().size() - 1; i++) {
    	System.out.println("Link: " + (i + 1));
    	WalkingAnt.beginNode = route.getProducts().get(i).getNode();
    	System.out.println(WalkingAnt.beginNode.getXcoord() + ", " + WalkingAnt.beginNode.getYcoord());
    	WalkingAnt.endNode = route.getProduct(i + 1).getNode();
    	System.out.println(WalkingAnt.endNode.getXcoord() + ", " + WalkingAnt.endNode.getYcoord());
    	Ant ant = WalkingAnt.computePath();
    	ants.add(ant);
    	pathLength += ant.getPath().size();
    }
    r.initFile(pathLength, route.getProducts().get(0).getNode());
    for (int i = 0; i < ants.size(); i++) {
    	r.appendDirections(ants.get(i), route.getProduct(i + 1));
    }
}

  //Method for test purposes
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
