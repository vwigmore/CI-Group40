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
    List<Node> nodes = new ArrayList<>(map.getNeighbours(currNode));
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
  
  public String pathToString() {
	  String res = "START: ";
	  for (int i : directions) {
		  if (i == 0) {
			  res += "East, ";
		  } else if (i == 1) {
			  res += "North, ";
		  } else if (i == 2) {
			  res += "West , ";
		  } else if (i == 3) {
			  res += "South, ";
		  }
	  }
	  res = res.substring(0, res.length() - 2);
	  res += " :END";
	  return res;
  }
  
  public String parseDirections() {
	  String res = "";
	  for (int i : directions) {
		  res += i + ";";
	  }
	  res = res.substring(0, res.length()-1);
	  return res;
  }
}
