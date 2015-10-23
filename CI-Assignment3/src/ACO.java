import java.util.Scanner;

public class ACO {

  public static void main(String[] args) {

    System.out.println("Which maze do you want to run?");
    System.out.println("0 for easy, 1 for medium, 2 for hard, 3 for insane.");
    Scanner sc = new Scanner(System.in);
    int choice = sc.nextInt();
    sc.close();

    switch(choice) {
      case 0: WalkingAnt.file = "Resources/easymaze.txt"; WalkingAnt.coordinates = "Resources/easycoordinates.txt"; break;
      case 1: WalkingAnt.file = "Resources/mediummaze.txt"; WalkingAnt.coordinates = "Resources/mediumcoordinates.txt"; break;
      case 2: WalkingAnt.file = "Resources/hardmaze.txt"; WalkingAnt.coordinates = "Resources/hardcoordinates.txt"; break;
      case 3: WalkingAnt.file = "Resources/insanemaze.txt"; WalkingAnt.coordinates = "Resources/insanecoordinates.txt"; break;
      default: throw new IllegalArgumentException();
    }
    Reader r = new Reader();
    WalkingAnt.map = r.parseMaze(WalkingAnt.file);
    WalkingAnt.beginNode = r.parseCoordinates(WalkingAnt.coordinates).get(0);
    WalkingAnt.endNode = r.parseCoordinates(WalkingAnt.coordinates).get(1);

    new Reader().writeMazePath(WalkingAnt.computePath());
  }

}
