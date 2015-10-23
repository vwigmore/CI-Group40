import java.util.ArrayList;


public class Chromosome {
	
	private ArrayList<Product> products;
	private double fitness;
	private double cumulativeRatio;
	private double ratio;

	public Chromosome(ArrayList<Product> products) {
		this.products = products;
	}
	
	// Computes the fitness by dividing by the total pathlengths.
	public double computeFitness(ArrayList<Path> paths) {
		int total = 0;
		for (int i = 0; i < products.size() - 1; i++) {
			total += findPathLength(products.get(i), products.get(i + 1), paths);
		}
		fitness =  100000 / total;
		return fitness;
	}
	
	// Computes the total pathlength.
	public double computePathLenght(ArrayList<Path> paths) {
		int total = 0;
		for (int i = 0; i < products.size() - 1; i++) {
			total += findPathLength(products.get(i), products.get(i + 1), paths);
		}
		return total;
	}
	
	// Finds the pathlength between two products.
	public int findPathLength(Product a, Product b, ArrayList<Path> paths) {
		for (Path p : paths) {
			if (p.getBegin().equals(a) || p.getEnd().equals(a)) {
				if (p.getBegin().equals(b) || p.getEnd().equals(b)) {
					return p.getLength();
				}
			}
		}
		return 0;
	}
	
	public int size() {
		return products.size();
	}
	
	public Product getProduct(int index) {
		return products.get(index);
	}
	
	public boolean containsProduct(Product p) {
		return products.contains(p);
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public double getCumulativeRatio() {
		return cumulativeRatio;
	}

	public void setCumulativeRatio(double cumulativeRatio) {
		this.cumulativeRatio = cumulativeRatio;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

}
