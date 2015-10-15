import java.util.ArrayList;

public class Node {

  private boolean value;
  private int xcoord;
  private int ycoord; 
  private ArrayList<Edge> edges;
  
  
  public Node(boolean val, int x, int y) {
    value = val;
    xcoord = x;
    ycoord = y;
  }
  
  
  public Edge getEdge(Node node) {
    for (Edge edge : edges) {
      if (edge.getEnd().equals(node)) {
        return edge;
      }
    }
    return null;
  }
  
  public int getXcoord() {
    return xcoord;
  }

  public int getYcoord() {
    return ycoord;
  }

  public boolean getValue() {
    return value;
  }

  public ArrayList<Edge> getEdges() {
    return edges;
  }

  public void setEdges(ArrayList<Edge> edges) {
    this.edges = edges;
  }
}
