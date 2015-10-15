import java.util.ArrayList;
import java.util.List;

public class Ant {

  private Node currNode;
  private Node prevNode;

  private ArrayList<Edge> travelled;

  public Ant(Node node) {
    currNode = node;
    travelled = new ArrayList<>();
  }

  public void move() {
    Node next = getNextNode();   
    travelled.add(currNode.getEdge(next));
    prevNode = currNode;
    currNode = next;
  }
  
  public Node getNextNode() {
    List<Edge> edges = currNode.getEdges();  

    double total = 0;
    for (Edge edge : edges) {
      total += edge.getPheromone();
    }

    Edge next = null;
    double random = Math.random();
    for (Edge edge: edges) {     
      double pher = edge.getPheromone();
      if ((pher / total) > random) {
        next = edge;
        break;
      } else {
        random -= (pher / total);
      }
    }
    if (next.getBegin().equals(currNode)) {
      return next.getEnd();
    } else {
      return next.getBegin();
    }
  }
}
