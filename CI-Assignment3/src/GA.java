import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GA {

	private int N = 20;
	private int generations = 500;
	private double Pc = 0.7;
	private double Pm = 0.01;
	private ArrayList<Path> paths;

	public Chromosome shortestPath(ArrayList<Path> pathsIn,
			ArrayList<Product> products) {
		this.paths = pathsIn;
		ArrayList<Chromosome> currentPopulation = new ArrayList<Chromosome>();

		for (int i = 0; i < N; i++) {
			Chromosome p = randomOrder(products);
			currentPopulation.add(p);
		}

		for (int i = 0; i < generations; i++) {
			System.out.println("Generation: " + (i + 1));
			currentPopulation = evolve(currentPopulation);
		}
		return findFittest(currentPopulation);
	}

	public ArrayList<Chromosome> evolve(ArrayList<Chromosome> population) {
		ArrayList<Chromosome> result = new ArrayList<Chromosome>();
		while (!population.isEmpty()) {
			setFitnesses(population);
			setCumulativeRatios(population);
			double randoma = Math.random();
			double randomb = Math.random();
			Chromosome selecteda = null;
			Chromosome selectedb = null;
			for (Chromosome c : population) {
				if (randoma < c.getCumulativeRatio() && selecteda == null) {
					selecteda = c;
				}
				if (randomb < c.getCumulativeRatio() && selectedb == null) {
					selectedb = c;
				}
			}
			if (!selecteda.equals(selectedb)) {
				population.remove(selecteda);
				population.remove(selectedb);
				Chromosome a;
				Chromosome b;
				if (Math.random() < Pc) { 
					a = crossOver(selecteda, selectedb);
					b = crossOver(selectedb, selecteda);
				} else {
					a = selecteda;
					b = selectedb;
				}
				if (Math.random() < Pm) {
					a = mutate(a);
					b = mutate(b);
				}
				result.add(a);
				result.add(b);
			}
		}
		//System.out.println(findFittest(result).computePathLenght(paths));
		return result;
	}

	public Chromosome mutate(Chromosome oldGen) {
		ArrayList<Product> temp = oldGen.getProducts();
		int rand1 = (int) Math.random() * temp.size();
		int rand2 = (int) Math.random() * temp.size();
		Collections.swap(temp, rand1, rand2);
		return new Chromosome(temp);
	}

	public Chromosome randomOrder(ArrayList<Product> products) {
		ArrayList<Product> result = new ArrayList<Product>();
		ArrayList<Product> copy = new ArrayList<Product>(products);
		Random r = new Random();
		int n = copy.size();
		for (int i = 0; i < n; i++) {
			int j = r.nextInt(copy.size());
			result.add(copy.remove(j));
		}
		return new Chromosome(result);
	}

	public double totalFitness(ArrayList<Chromosome> population) {
		double result = 0;
		for (Chromosome c : population) {
			result += c.computeFitness(paths);
		}
		return result;
	}

	public void setCumulativeRatios(ArrayList<Chromosome> population) {
		for (int i = 0; i < population.size(); i++) {
			Chromosome c = population.get(i);
			c.setRatio(c.getFitness() / totalFitness(population));
			double cumul = c.getRatio();
			for (int j = 0; j < i; j++) {
				cumul += population.get(j).getRatio();
			}
			c.setCumulativeRatio(cumul);
		}
	}

	public void setFitnesses(ArrayList<Chromosome> population) {
		for (Chromosome c : population) {
			c.computeFitness(paths);
		}
	}

	public Chromosome crossOver(Chromosome a, Chromosome b) {
		ArrayList<Product> child = new ArrayList<Product>();
		for (int i = 0; i < a.size(); i++) {
			child.add(null);
		}
		// Get start and end sub tour positions for parent1's tour
		int startPos = (int) (Math.random() * a.size());
		int endPos = (int) (Math.random() * a.size());

		// Loop and add the sub tour from parent1 to our child
		for (int i = 0; i < a.size(); i++) {
			// If our start position is less than the end position
			if (startPos < endPos && i > startPos && i < endPos) {
				child.set(i, a.getProduct(i));
			} // If our start position is larger
			else if (startPos > endPos && i > endPos && i < startPos) {
				child.set(i, a.getProduct(i));
			}
		}

		// Loop through parent2's city tour
		for (int i = 0; i < b.size(); i++) {
			// If child doesn't have the city add it
			if (!child.contains(b.getProduct(i))) {
				// Loop to find a spare position in the child's tour
				for (int ii = 0; ii < child.size(); ii++) {
					// Spare position found, add city
					if (child.get(ii) == null) {
						child.set(ii, b.getProduct(i));
						break;
					}
				}
			}
		}
		return new Chromosome(child);
	}
	
	public Chromosome findFittest(ArrayList<Chromosome> population) {
		Chromosome fittest = population.get(0);
		for (int i = 1; i < N; i++) {
			if (fittest.getFitness() < population.get(i).getFitness()) {
				fittest = population.get(i);
			}
		}
		return fittest;
	}

}
