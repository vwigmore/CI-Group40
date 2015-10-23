import java.util.ArrayList;
import java.util.Stack;

public class WalkingAnt {

  public static String file;
  public static String coordinates;
  public static Map map;
  public static Node beginNode;
  public static Node endNode;

  public static int loops = 7;
  public static int ants = 5;
  public static int pheromone = 100;
  public static double evaporation = 0.1;

  public static ArrayList<Ant> antList = new ArrayList<Ant>();

  public static Ant computePath() {
    for (int i = 0; i < loops; i++) {

      System.out.println("loop: " + (i + 1));

      for (Node[] row : map.getNodes()) {
        for (Node node : row) {
          if (node.getValue()) {
            node.setPheromone(node.getPheromone() * (1 - evaporation));
          }
        }
      }

      for (int j = 0; j < ants; j++) {
        antList.add(new Ant(beginNode));
      }


      for (int j = 0; j < ants; j++) {
        Ant ant = antList.get(j);
        while (!ant.getCurrNode().equals(endNode)) {
          ant.move();
        }
        new Reader().writeMazePath(ant);
      }
      for (int j = 0; j < ants; j++) {
        Stack<Node> path = antList.get(j).getPath();
        for (Node n : path) {
          n.setPheromone(n.getPheromone() + pheromone / path.size());
        }
      }
      antList.clear();
    }

    Ant ant = new Ant(beginNode);
    while (!ant.getCurrNode().equals(endNode)) {
      ant.move();
    }
    return ant;
  }
  
}
