import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Reader {
	
	private Scanner scanner;

	public Reader(String file) {
		try {
			scanner = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Map read() {
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
		return new Map(map, height, width);
	}

}
