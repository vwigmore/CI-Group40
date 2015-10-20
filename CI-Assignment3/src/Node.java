
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
  
  public void setValue(Boolean newValue) {
    this.value =  newValue;
  }


  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Node ) {
      Node that = (Node) obj;
      if (this.pheromone == that.pheromone 
          && this.value == that.value 
          && this.xcoord == that.xcoord 
          && this.ycoord == that.ycoord) {
        return true;
      }
    }
    return false;
  }
  
  
  
  
}
