import java.util.ArrayList;
import java.util.List;

public class ACO {

  public static final int ants = 150;
  public static final int loops = 1000;
  public static final int standard_pheromone = 100;
  public static final double evaporation = 0.1;

  public static final Node beginnode = new Node(true, 0, 0);
  public static final Node endnode = new Node(true, 21, 25);

  public static Map map = new Reader().parseMaze("Resources/mediummaze.txt");

  public static void main(String[] args) {
    for (int i = 0; i < loops; i++) {
      List<Ant> antlist = new ArrayList<>();

      /**
       * For every node walked on, deal with the evaporation.
       */
      List<Node> total = map.getTotalset();
      if (total != null) {
        for (Node node : total) {
          node.setPheromone(node.getPheromone() * (1 - evaporation));
        }
      }

      /**
       * Each ant should move until it has reached the end.
       */
      for (int j = 0; j < ants; j++) {
        Ant ant = new Ant(beginnode);
        antlist.add(ant);
        while (!ant.getCurrNode().equals(endnode)) {
          ant.move();
        }
      }
      /**
       * Update the pheromones on the travelled path for every ant.
       */
      for (Ant ant : antlist) {
        List<Node> travelled = ant.getTravelled();
        for (Node node : travelled) {
          double temp = standard_pheromone / travelled.size();
          node.setPheromone(node.getPheromone() + temp);
        }
      }
    }

    Ant ant = new Ant(beginnode);

    while (!ant.getCurrNode().equals(endnode)) {
      ant.move();
    }

    new Reader().writeMazePath(ant);
  }
}
