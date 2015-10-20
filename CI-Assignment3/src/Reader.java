import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {

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

}
