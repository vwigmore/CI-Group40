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
        if (j > i) {         
          WalkingAnt.endNode = productNodes.get(j).getNode();          
          Ant ant = WalkingAnt.computePath();
          Path path = new Path(productNodes.get(i),productNodes.get(j),ant.getPath().size());        
          possiblepaths.add(path);
        }
      }
    }
    
    Chromosome route = new GA().shortestPath(possiblepaths, productNodes);  
  }

}
