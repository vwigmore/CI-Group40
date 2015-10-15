import java.util.ArrayList;
import java.util.List;

public class Map {

  private Node[][] mapNodes;
  private int height;
  private int width;
  
  public Map(Node[][] nodes, int h, int w) {
    mapNodes = nodes;
    height = h;
    width = w;
    setEdges();
  }
   
  private void setEdges() {
    for (int i=0; i < width; i++) {
      for (int j=0; j < height; j++) {
        Node temp = mapNodes[i][j];
        ArrayList<Edge> edges = new ArrayList<>();
        List<Node> neigh = getNeighbours(temp);
        for (Node n : neigh) {
          Edge edge = new Edge(temp,n);
          edges.add(edge);
        }
        temp.setEdges(edges);
      }
    }
  }
  
  private List<Node> getNeighbours(Node node) {
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
  
  
  public Node[][] getNodes() {
    return mapNodes;
  }
}
