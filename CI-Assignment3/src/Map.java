

import java.util.ArrayList;

public class Map {

  private Node[][] mapNodes;
  private int height;
  private int width;
      
  public Map(Node[][] nodes, int h, int w) {
    mapNodes = nodes;
    height = h;
    width = w;
  }  

  //Returns all nodes that are possible to travel to from a certain node that is given as a parameter
  public ArrayList<Node> getNeighbours(Node node) {
    int x = node.getXcoord();
    int y = node.getYcoord();
    ArrayList<Node> result = new ArrayList<Node>();
    Node n = null;
    if (x > 0) {
    	n = mapNodes[x-1][y];
    	if (n.getValue()) {
    		result.add(n);
    	}
    }
    if (x < width-1) {
    	n = mapNodes[x+1][y];
    	if (n.getValue()) {
    		result.add(n);
    	}
    }
    if (y > 0) {
    	n = mapNodes[x][y-1];
    	if (n.getValue()) {
    		result.add(n);
    	}
    }
    if (y < height-1) {
    	n = mapNodes[x][y+1];
    	if (n.getValue()) {
    		result.add(n);
    	}
    }
    return result;
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
