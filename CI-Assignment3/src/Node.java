
public class Node {

  private boolean value;
  private int xcoord;
  private int ycoord; 
  private double pheromone;

  public Node(boolean val, int x, int y) {
    value = val;
    xcoord = x;
    ycoord = y;
    if (val) {
      pheromone = 1;
    } else {
      pheromone = 0;
    }
  } 
  
  
  public double getPheromone() {
    return pheromone;
  }

  public void setPheromone(double pheromone) {
    this.pheromone = pheromone;
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
  
}
