
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {

	//Parse a maze textfile to an actual mazemap object
	public Map parseMaze(String file) {
		try {
			Scanner scanner = new Scanner(new File(file));
			int width = scanner.nextInt();
			int height = scanner.nextInt();
			Node[][] map = new Node[width][height];
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					int val = scanner.nextInt();
					if (val == 1) {
						map[j][i] = new Node(true, j, i);
					} else {
						map[j][i] = new Node(false, j, i);
					}
				}
			}
			scanner.close();
			return new Map(map, height, width);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Parse a product textfile to an actual list of product objects
	public ArrayList<Product> parseProducts(String file) {
		try {
			ArrayList<Product> res = new ArrayList<Product>();
			Scanner scanner = new Scanner(new File(file));
			scanner.useDelimiter(";");
			int size = scanner.nextInt();
			for (int i = 0; i < size; i++) {
				scanner.nextLine();
				String s = scanner.next();
				String[] split = s.split(", ");
				String[] split2 = split[0].split(": ");
				int number = Integer.parseInt(split2[0]);
				int x = Integer.parseInt(split2[1]);
				int y = Integer.parseInt(split[1]);
				res.add(new Product(number, new Node(true, x, y)));
			}
			scanner.close();
			return res;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Parse a coordinates textfile to an actual list of 1 begin node and 1 end node
	public ArrayList<Node> parseCoordinates(String file) {
		try {
			Scanner sc = new Scanner(new File(file));
			ArrayList<Node> result = new ArrayList<Node>();
			String a = sc.nextLine();
			String[] split = a.split(", ");
			result.add(new Node(true, Integer.parseInt(split[0]), Integer.parseInt(split[1].substring(0, split[1].length()-1))));
			a = sc.nextLine();
			split = a.split(", ");
			result.add(new Node(true, Integer.parseInt(split[0]), Integer.parseInt(split[1].substring(0, split[1].length()-1))));
			sc.close();
			return result;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Write the path of an ant into a resultfile
	public void writeMazePath(Ant ant) {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(new File("Resources/mazeresult.txt")));
			out.println(ant.getPath().size() + ";");
			out.println(WalkingAnt.beginNode.getXcoord() + ", " + WalkingAnt.beginNode.getYcoord() + ";");
			out.println(ant.parseDirections());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initFile(int pathLength, Node beginNode) {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(new File("Resources/tspresult.txt")));
			out.println(pathLength + ";");
			out.println(beginNode.getXcoord() + ", " + beginNode.getYcoord() + ";");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void appendDirections(Ant ant, Product p) {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(new File("Resources/tspresult.txt"), true));
			out.println(ant.parseDirections());
			out.println("take product #" + p.getNumber() + ";");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
