

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class ACO {
	
	public static String file;
	public static String coordinates;
	public static Map map;
	public static Node beginNode;
	public static Node endNode;
	
	public static int loops = 100;
	public static int ants = 10;
	public static int pheromone = 100;
	public static double evaporation = 0.1;
	
	public static ArrayList<Ant> antList = new ArrayList<Ant>();

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
		new Reader().writeMazePath(ant);
	}

}
