import java.util.ArrayList;
import java.util.List;

public class Map {

  private static Node[][] mapNodes;
  private static int height;
  private static int width;
  
  public Map(Node[][] nodes, int h, int w) {
    mapNodes = nodes;
    height = h;
    width = w;
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
  
}
