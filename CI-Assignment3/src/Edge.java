
public class Edge {

  private Node begin;
  private Node end;
  private double pheromone;
  
  
  public Edge(Node b, Node e) {
    begin = b;
    end = e;
    if (!b.getValue() || !e.getValue()) {
      pheromone = 0;
    } else {
      pheromone = 1;
    }
  }


  public double getPheromone() {
    return pheromone;
  }

  public void setPheromone(double pheromone) {
    this.pheromone = pheromone;
  }

  public Node getBegin() {
    return begin;
  }

  public Node getEnd() {
    return end;
  }
}
