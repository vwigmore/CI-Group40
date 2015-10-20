import java.util.ArrayList;
import java.util.List;

public class ACO {

  private static final int ants = 10;
  private static final int loops = 1000;
  private static final int standard_pheromone = 100;
  private static final double evaporation = 0.1;

  private static final Node beginnode = new Node(true, 0, 0);
  private static final Node endnode = new Node(true, 24, 14);

  private static List<Ant> antlist = new ArrayList<>();
  public static Map map = new Reader("Resources/easymaze.txt").read();

  public static void main(String[] args) {
    /**
     * Optimalisation, removing dead ends
     */
    // map.removeDeadEnds(endnode);

    /**
     * After removing all dead ends, start with the loops.
     */
    for (int i = 0; i < loops; i++) {
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
       * Initialize the ants.
       */
      for (int k = 0; k < ants; k++) {
        Ant ant = new Ant(beginnode);
        antlist.add(ant);
      }
      /**
       * Each ant should move until it has reached the end.
       */
      for (int j = 0; j < ants; j++) {
//        int path = 0;
        Ant ant = antlist.get(j);
        while (!ant.getCurrNode().equals(endnode)) {
          ant.move();
//          path++;
          //		               System.out.println(ant.pathToString());

        }
        //				System.out.println("length: " + path);
        //				System.out.println(ant.parseDirections());
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
    int path = 0;

    while (!ant.getCurrNode().equals(endnode)) {
      ant.move();
      path++;
    }
    System.out.println("length: " + path);

    System.out.println(ant.parseDirections());
  }
}
