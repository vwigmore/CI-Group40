import java.util.ArrayList;
import java.util.List;

public class Ant {

  private Node currNode;
  private Node prevNode;

  private ArrayList<Node> travelled;
  private ArrayList<Integer> directions;
  
  private Map map;

  public Ant(Node node) {
    currNode = node;
    prevNode = null;
    travelled = new ArrayList<>();
    directions = new ArrayList<>();
    map = ACO.map;
  }
  
  public void move() {
    Node next = getNextNode();
    if (!travelled.contains(currNode)) {
      travelled.add(currNode);
    }
    if (!map.getTotalset().contains(currNode)) {
      map.getTotalset().add(currNode);
    }
    prevNode = currNode;
    currNode = next;
  }
  
  private Node getNextNode() {
    List<Node> nodes = map.getNeighbours(currNode);
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
        nextDir(next);
        break;
      } else {
        random -= (pher / total);
      }
    }
    return next;
  }
  
  private void nextDir(Node next) {
    Node current = currNode;   
    if (current.getXcoord() > next.getXcoord()) {           // moving left
      directions.add(new Integer(2));
    } else if (current.getXcoord() < next.getXcoord()) {    // moving right
      directions.add(new Integer(0));
    } else if (current.getYcoord() > next.getYcoord()) {    // moving up
      directions.add(new Integer(1));
    } else if (current.getYcoord() < next.getYcoord()) {    // moving down
      directions.add(new Integer(3));
    }
  }
  
  public Node getCurrNode() {
    return currNode;
  }

  public ArrayList<Node> getTravelled() {
    return travelled;
  }
  
  public ArrayList<Integer> getDirections() {
    return directions;
  }
  
}
