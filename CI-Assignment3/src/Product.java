


public class Product {
	
	private Node node;
	private int number;

	public Product(int number, Node node) {
		this.node = node;
		this.number = number;
	}

	
    @Override
    public boolean equals(Object obj) {
      if (obj instanceof Product) {
        Product that = (Product) obj;
        if (this.node.equals(that.node) && this.number == that.number) {
          return true;
        }
      }     
      return false;
    }
    
	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	  
}
