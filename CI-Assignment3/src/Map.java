import java.util.ArrayList;
import java.util.List;

public class Map {

  private static Node[][] mapNodes;
  private static int height;
  private static int width;
    
  private static ArrayList<Node> totalset;
  
  public Map(Node[][] nodes, int h, int w) {
    mapNodes = nodes;
    height = h;
    width = w;
    totalset = new ArrayList<>();
  }  

  public static List<Node> getNeighbours(Node node) {
    List<Node> temp = new ArrayList<>();    
    if (node.getXcoord() > 1) {
      temp.add(mapNodes[node.getXcoord()-1][node.getYcoord()]);
    }
    if (node.getXcoord() < width) {
      temp.add(mapNodes[node.getXcoord()+1][node.getYcoord()]);
    }
    if (node.getYcoord() > 1) {
      temp.add(mapNodes[node.getXcoord()][node.getYcoord()-1]);
    }
    if (node.getYcoord() < height) {
      temp.add(mapNodes[node.getXcoord()][node.getYcoord()+1]);
    }    
    return temp;
  }
  
  
  public static void removeDeadEnds(Node deadEnd) {
    boolean progress = true;
    while (progress) {
      progress = false;
      for ( int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          Node node = mapNodes[i][j];
          if (node.getValue() == true && !node.equals(deadEnd) && getNeighbours(node).size() < 2) {
            mapNodes[i][j].setValue(false);
            progress = true;
          }
        }
      }
    }
  }
  
  public static ArrayList<Node> getTotalset() {
    return totalset;
  }
  
  public Node[][] getNodes() {
    return mapNodes;
  }

  public static int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }
  
  
}
