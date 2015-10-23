
public class Path {

  private Product begin;
  private Product end;
  private int length;  
  
  public Path(Product beg, Product e, int l) {
    begin = beg;
    end = e;
    length = l;
  }

  public Product getBegin() {
    return begin;
  }

  public Product getEnd() {
    return end;
  }

  public int getLength() {
    return length;
  }
  
}
