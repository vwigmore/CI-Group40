import java.util.ArrayList;
import java.util.Stack;

public class WalkingAnt {

  public static String file;                                        //File to read the maze from
  public static String coordinates;                               //File to read the coordinates from
  public static Map map;                                          //The maze object
  public static Node beginNode;                                   //The node where the ants start from
  public static Node endNode;                                     //The node where the ants have to end

  public static int loops = 100;                                 //Number of times the ants run through the maze
  public static int ants = 3;                                   //Number of ants per loop
  public static int pheromone = 100;                              //Pheromone constant
  public static double evaporation = 0.1;                         //Evaporation constant


  public static Ant computePath() {
    //Run the loops
    for (int i = 0; i < loops; i++) {
      ArrayList<Ant> antList = new ArrayList<Ant>();
      //System.out.println("loop: " + (i + 1));

      //Let old pheromone slowly disapear with the evaporation constant
      for (Node[] row : map.getNodes()) {
        for (Node node : row) {
          if (node.getValue()) {
            node.setPheromone(node.getPheromone() * (1 - evaporation));
          }
        }
      }

      //Initialize all ants 
      for (int j = 0; j < ants; j++) {
        antList.add(new Ant(beginNode));
      }

      //Let every ant through the maze until the end is found
      for (int j = 0; j < ants; j++) {
        Ant ant = antList.get(j);
        while (!ant.getCurrNode().equals(endNode)) {
          ant.move();
        }
        new Reader().writeMazePath(ant);
      }

      //Distribute pheromone along the path of every ant
      for (int j = 0; j < ants; j++) {
        Stack<Node> path = antList.get(j).getPath();
        for (Node n : path) {
          n.setPheromone(n.getPheromone() + pheromone / path.size());
        }
      }

      //Clear the antlist for a new set of ants in the next loop
      antList.clear();
    }

    //After the loops let one final ant run through the maze to determine the shortest path
    Ant ant = new Ant(beginNode);
    while (!ant.getCurrNode().equals(endNode)) {
      ant.move();
    }
    return ant;
  }
}
