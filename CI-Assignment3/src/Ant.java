

import java.util.ArrayList;
import java.util.Stack;

public class Ant {

	Node currNode;

	Stack<Node> path;			//The path the ant has travelled so far
	ArrayList<Node> deadends;	//All path nodes the ant shouldn't travel

	public Ant(Node beginNode) {
		this.currNode = beginNode;
		path = new Stack<Node>();
		path.add(currNode);
		deadends = new ArrayList<Node>();
	}

	//Travel to a new node
	public void move() {
		Node next = nextNode();
		currNode = next;
		path.add(currNode);	
	}

	//Compute which node is the best node to move to
	public Node nextNode() {
		ArrayList<Node> neighbours = ACO.map.getNeighbours(currNode);
		
		//If there are no untravelled paths to go to then reverse
		if (freeWays(neighbours) == 0) {
			neighbours = reverse();
		}
		
		//Remove all paths that have already been travelled
		neighbours = preferNewPath(neighbours);
		
		//Choose a new node dependend on probabilities which depend on the amount of pheromone
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
	
	//Return the amount of next nodes that have not been travelled yet
	public int freeWays(ArrayList<Node> neighbours) {
		int count = 0;
		for (Node n : neighbours) {
			if (!path.contains(n) && !deadends.contains(n)) {
				count++;
			}
		}
		return count;
	}		
	
	//Move back nodes until a junction has been found where another untravelled path can be chosen
	public ArrayList<Node> reverse() {
		while (freeWays(ACO.map.getNeighbours(currNode)) < 1) {
			deadends.add(path.pop());
			currNode = path.lastElement();
		}
		return ACO.map.getNeighbours(currNode);
	}

	//Prefer new nodes that have not been travelled yet be removing all nodes that have been travelled
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
