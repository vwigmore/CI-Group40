

import java.util.ArrayList;
import java.util.Stack;

public class Ant {

	Node currNode;

	Stack<Node> path;
	ArrayList<Node> deadends;

	public Ant(Node beginNode) {
		this.currNode = beginNode;
		path = new Stack<Node>();
		path.add(currNode);
		deadends = new ArrayList<Node>();
	}

	public void move() {
		Node next = nextNode();
		currNode = next;
		path.add(currNode);	
	}

	public Node nextNode() {
		ArrayList<Node> neighbours = ACO.map.getNeighbours(currNode);
		if (freeWays(neighbours) == 0) {
			neighbours = reverse();
		}
		neighbours = preferNewPath(neighbours);
		double number = Math.random();
		double total = 0;
		for (Node n : neighbours) {
			total += n.getPheromone();
		}
		double[] chances = new double[neighbours.size()];
		for (int i = 0; i < neighbours.size(); i++) {
			chances[i] = neighbours.get(i).getPheromone() / total;
			if (i > 0) {
				chances[i] += chances[i-1];
			}
			if (number < chances[i]) {
				return neighbours.get(i);
			}
		}
		return neighbours.get(0);
	}
	
	public int freeWays(ArrayList<Node> neighbours) {
		int count = 0;
		for (Node n : neighbours) {
			if (!path.contains(n) && !deadends.contains(n)) {
				count++;
			}
		}
		return count;
	}		
	
	public ArrayList<Node> reverse() {
		while (freeWays(ACO.map.getNeighbours(currNode)) < 1) {
			deadends.add(path.pop());
			currNode = path.lastElement();
		}
		return ACO.map.getNeighbours(currNode);
	}

	public ArrayList<Node> preferNewPath(ArrayList<Node> neighbours) {
		ArrayList<Node> result = new ArrayList<Node>();
		for (Node n : neighbours) {
			if (!path.contains(n) && !deadends.contains(n)) {
				result.add(n);
			}
		}
		return result;
	}

	public Node getCurrNode() {
		return currNode;
	}

	public void setCurrNode(Node currNode) {
		this.currNode = currNode;
	}

	public Stack<Node> getPath() {
		return path;
	}

	public void setPath(Stack<Node> path) {
		this.path = path;
	}
	
	public String parseDirections() {
		String result = "";
		for (int i = 0; i < path.size() - 1; i++) {
			Node n1 = path.get(i);
			Node n2 = path.get(i+1);
			if (n1.getXcoord() < n2.getXcoord()) {
				result += 0 + ";";
			} else if (n1.getXcoord() > n2.getXcoord()) {
				result += 2 + ";";
			} else if (n1.getYcoord() < n2.getYcoord()) {
				result += 3 + ";";
			} else if (n1.getYcoord() > n2.getYcoord()) {
				result += 1 + ";";
			}
		}
		return result;
	}

}
