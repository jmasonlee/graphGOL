package gameOfLife.cell;

import java.util.List;
import java.util.Map;

public class RelatedCells {
  private final Map<Relationships, List<Cell>> relationships;
  private final Cell centralCell;

  public RelatedCells(Cell centralCell, Map<Relationships, List<Cell>> relationships) {
    this.centralCell = centralCell;
    this.relationships = relationships;
  }

  public Cell getCentralCell() {
    return centralCell;
  }

  public Map<Relationships, List<Cell>> getRelationships() {
    return relationships;
  }

  @Override
  public String toString() {
    return relationships.toString();
  }
}
