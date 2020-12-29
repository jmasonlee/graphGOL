package gameOfLife.cell;

import java.util.List;
import java.util.Map;

public class RelatedCells {
  private final Map<Relationships, List<Cell>> value;

  public RelatedCells(Map<Relationships, List<Cell>> value) {
    this.value = value;
  }

  public Map<Relationships, List<Cell>> getValue() {
    return value;
  }

  @Override
  public String toString(){
    return value.toString();
  }
}
