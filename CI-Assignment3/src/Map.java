import java.util.ArrayList;
import java.util.List;

public class Map {

  private Node[][] mapNodes;
  private int height;
  private int width;
    
  private ArrayList<Node> totalset;
  
  public Map(Node[][] nodes, int h, int w) {
    mapNodes = nodes;
    height = h;
    width = w;
    totalset = new ArrayList<>();
  }  

  public List<Node> getNeighbours(Node node) {
    List<Node> temp = new ArrayList<>();    
    if (node.getXcoord() > 0) {
      Node neigh = mapNodes[node.getXcoord()-1][node.getYcoord()];
      if (neigh.getValue()) {
        temp.add(neigh);
      }
    }
    if (node.getXcoord() < width - 1) {
      Node neigh = mapNodes[node.getXcoord()+1][node.getYcoord()];
      if (neigh.getValue()) {
        temp.add(neigh);
      }
    }
    if (node.getYcoord() > 0) {
      Node neigh = mapNodes[node.getXcoord()][node.getYcoord()-1];
      if (neigh.getValue()) {
        temp.add(neigh);
      }
    }
    if (node.getYcoord() < height - 1) {
      Node neigh = mapNodes[node.getXcoord()][node.getYcoord()+1];
      if (neigh.getValue()) {
        temp.add(neigh);
      }
    }    
    return temp;
  }
  
  public ArrayList<Node> getTotalset() {
    return totalset;
  }
  
  public Node[][] getNodes() {
    return mapNodes;
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }
  
  
}
