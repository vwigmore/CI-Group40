import java.util.ArrayList;
import java.util.Random;


public class GA {
	
	private int N = 50;
	private ArrayList<Path> paths;

	public ArrayList<Path> shortestPath(ArrayList<Path> pathsIn, ArrayList<Product> products) {
		this.paths = pathsIn;
		ArrayList<Chromosome> initialPopulation = new ArrayList<Chromosome>();
		double totalFitness = 0;
		for (int i = 0; i < N; i++) {
			Chromosome p = randomOrder(products);
			initialPopulation.add(p);
			totalFitness += p.computeFitness(paths);
		}
		for (int i = 0; i < N; i++) {
			Chromosome c = initialPopulation.get(i);
			c.setRatio(c.getFitness() / totalFitness);
			double cumul = c.getRatio();
			for (int j = 0; j < i; j++) {
				cumul += initialPopulation.get(j).getRatio();
			}
			c.setCumulativeRatio(cumul);
		}
		while (!initialPopulation.isEmpty()) {
			double randoma = Math.random();
			double randomb = Math.random();
			Chromosome selecteda = null;
			for (Chromosome c : initialPopulation) {
				if (randoma < c.getCumulativeRatio()) {
					selecteda = c; 
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		
	}
	
	public Chromosome randomOrder(ArrayList<Product> products) {
		ArrayList<Product> result = new ArrayList<Product>();
		Random r = new Random();
		int n = products.size();
		for (int i = 0; i < products.size(); i++) {
			int j = r.nextInt(n);
			result.add(products.remove(j));
			n--;
		}
		return new Chromosome(result);
	}
	
	public boolean[][] createChromosome(ArrayList<Product> products) {
		int n = products.size();
		boolean[][] result = new boolean[n][5];
		for (int i = 0; i < products.size(); i++) {
			result[i] = createGen(products.remove(i));
		}
		return result;
	}
	
	public boolean[] createGen(Product p) {
		int input = p.getNumber();	
		boolean[] bits = new boolean[5];
	    for (int i = 4; i >= 0; i--) {
	        bits[i] = (input & (1 << i)) != 0;
	    }
	    return bits;
	}

}
