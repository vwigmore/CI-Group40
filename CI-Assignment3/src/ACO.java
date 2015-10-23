

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class ACO {
	
	public static String file;										//File to read the maze from
	public static String coordinates;								//File to read the coordinates from
	public static Map map;											//The maze object
	public static Node beginNode;									//The node where the ants start from
	public static Node endNode;										//The node where the ants have to end
	
	public static int loops = 1000;									//Number of times the ants run through the maze
	public static int ants = 100;									//Number of ants per loop
	public static int pheromone = 100;								//Pheromone constant
	public static double evaporation = 0.1;							//Evaporation constant
	
	public static ArrayList<Ant> antList = new ArrayList<Ant>();	//A list of ants per loop
 
	public static void main(String[] args) {
		
		System.out.println("Which maze do you want to run?");
		System.out.println("0 for easy, 1 for medium, 2 for hard, 3 for insane.");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		sc.close();
		
		switch(choice) {
		case 0: file = "Resources/easymaze.txt"; coordinates = "Resources/easycoordinates.txt"; break;
		case 1: file = "Resources/mediummaze.txt"; coordinates = "Resources/mediumcoordinates.txt"; break;
		case 2: file = "Resources/hardmaze.txt"; coordinates = "Resources/hardcoordinates.txt"; break;
		case 3: file = "Resources/insanemaze.txt"; coordinates = "Resources/insanecoordinates.txt"; break;
		default: throw new IllegalArgumentException();
		}
		Reader r = new Reader();
		map = r.parseMaze(file);
		beginNode = r.parseCoordinates(coordinates).get(0);
		endNode = r.parseCoordinates(coordinates).get(1);
				 
		//Run the loops
		for (int i = 0; i < loops; i++) {
		
			System.out.println("loop: " + (i + 1));
			
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
		
		//Write the shortest path to the result file
		new Reader().writeMazePath(ant);
	}

}
