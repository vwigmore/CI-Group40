import java.util.ArrayList;
import java.util.List;


public class TSP {
	
	public static String maze = "Resources/easymaze.txt";
	public static String products = "Resources/tspproducts.txt";
	
	private static final int ants = 5;
	private static final int loops = 100;
	private static final int standard_pheromone = 100;
	private static final double evaporation = 0.1;
	
	private static final Node beginnode = new Node(true, 0, 0);

	public static Map map = new Reader().parseMaze(maze);
	public static ArrayList<Product> productNodes = new Reader().parseProducts(products);
	public static ArrayList<Ant> antlist = new ArrayList<Ant>();
	
	public static void main(String[] args) {
		
	}

}
