package cell;

public class Cell implements Comparable {
  public Integer y;
  public Integer x;

  public Cell(Integer x, Integer y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int compareTo(Object o) {
    return 0;
  }

  @Override
  public boolean equals(Object o) {

    if (o == this) {
      return true;
    }

    if (!(o instanceof Cell)) {
      return false;
    }

    Cell otherCell = (Cell) o;

    return otherCell.x == this.x && otherCell.y == this.y;
  }

  @Override
  public String toString(){
      return "[" + x + "," + y + "]";
  }
}
