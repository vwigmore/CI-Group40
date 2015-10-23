import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class GA {

  private int N = 50;
  private int generations = 1000;
  private ArrayList<Path> paths;

  public Chromosome shortestPath(ArrayList<Path> pathsIn, ArrayList<Product> products) {
    this.paths = pathsIn;
    ArrayList<Chromosome> currentPopulation = new ArrayList<Chromosome>();

    for (int i = 0; i < N; i++) {
      Chromosome p = randomOrder(products);
      currentPopulation.add(p);
    }

    for(int i = 0; i < generations; i++) {
      currentPopulation = evolve(currentPopulation);
    }
    Chromosome fittest = currentPopulation.get(0);
    for (int i = 1; i < N; i++) {
      if (fittest.getFitness() < currentPopulation.get(i).getFitness()) {
        fittest = currentPopulation.get(i);
      }
    }
    return fittest;
  }

  public ArrayList<Chromosome> evolve(ArrayList<Chromosome> population) {
    ArrayList<Chromosome> result = new ArrayList<Chromosome>();
    while (!population.isEmpty()) {
      setCumulativeRatios(population);
      double randoma = Math.random();
      double randomb = Math.random();
      Chromosome selecteda = null;
      Chromosome selectedb = null;
      for (Chromosome c : population) {
        if (randoma < c.getCumulativeRatio()) {
          selecteda = c; 
        } 
        if (randomb < c.getCumulativeRatio()) {
          selectedb = c;
        }
      }
      if (!selecteda.equals(selectedb)) {
        population.remove(selecteda);
        population.remove(selectedb);
      }
      result.add(crossOver(selecteda, selectedb));
      result.add(crossOver(selectedb, selecteda));
    }

    return result;
  }



  public ArrayList<Chromosome> mutate(ArrayList<Chromosome> oldGen) {
    ArrayList<Chromosome> newGen = new ArrayList<>();
    for (Chromosome chrome : oldGen) {
      ArrayList<Product> temp = chrome.getProducts();
      int rand1 = (int) Math.random() * temp.size();
      int rand2 = (int) Math.random() * temp.size();
      Collections.swap(oldGen, rand1, rand2);

    }
    return newGen;
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

  public double totalFitness(ArrayList<Chromosome> population) {
    double result = 0;
    for (Chromosome c : population) {
      result += c.computeFitness(paths);
    }
    return result;
  }

  public void setCumulativeRatios(ArrayList<Chromosome> population) {
    for (int i = 0; i < N; i++) {
      Chromosome c = population.get(i);
      c.setRatio(c.getFitness() / totalFitness(population));
      double cumul = c.getRatio();
      for (int j = 0; j < i; j++) {
        cumul += population.get(j).getRatio();
      }
      c.setCumulativeRatio(cumul);
    }
  }

  public Chromosome crossOver(Chromosome a, Chromosome b) {
    ArrayList<Product> child = new ArrayList<Product>();
    // Get start and end sub tour positions for parent1's tour
    int startPos = (int) (Math.random() * a.size());
    int endPos = (int) (Math.random() * a.size());

    // Loop and add the sub tour from parent1 to our child
    for (int i = 0; i < child.size(); i++) {
      // If our start position is less than the end position
      if (startPos < endPos && i > startPos && i < endPos) {
        child.add(i, a.getProduct(i));
      } // If our start position is larger
      else if (startPos > endPos) {
        if (!(i < startPos && i > endPos)) {
          child.add(i, a.getProduct(i));
        }
      }
    }

    // Loop through parent2's city tour
    for (int i = 0; i < a.size(); i++) {
      // If child doesn't have the city add it
      if (!child.contains(b.getProduct(i))) {
        // Loop to find a spare position in the child's tour
        for (int ii = 0; ii < child.size(); ii++) {
          // Spare position found, add city
          if (child.get(ii) == null) {
            child.add(ii, b.getProduct(i));
            break;
          }
        }
      }
    }
    return new Chromosome(child);
  }

}
