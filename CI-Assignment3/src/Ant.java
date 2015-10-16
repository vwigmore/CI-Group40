import java.util.ArrayList;
import java.util.List;

public class Ant {

  private Node currNode;
  private Node prevNode;

  private ArrayList<Node> travelled;

  public Ant(Node node) {
    currNode = node;
    prevNode = null;
    travelled = new ArrayList<>();
  }
  
  public void move() {
    Node next = getNextNode();
    if (!travelled.contains(currNode)) {
      travelled.add(currNode);
    }
    if (!Map.getTotalset().contains(currNode)) {
      Map.getTotalset().add(currNode);
    }
    prevNode = currNode;
    currNode = next;
  }
  
  private Node getNextNode() {
    List<Node> nodes = Map.getNeighbours(currNode);
    if (prevNode != null) {
      nodes.remove(prevNode);
    }  
    
    double total = 0;
    for (Node node : nodes) {
      total += node.getPheromone();
    }
    Node next = null;
    double random = Math.random();
    for (Node node: nodes) {     
      double pher = node.getPheromone();
      if ((pher / total) > random) {
        next = node;
        break;
      } else {
        random -= (pher / total);
      }
    }
    return next;
  }
  
  public Node getCurrNode() {
    return currNode;
  }

  public ArrayList<Node> getTravelled() {
    return travelled;
  }
  
}
