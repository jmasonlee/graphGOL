package gameOfLife.cell;

import java.util.List;
import java.util.Map;

public class RelatedCells {
  private final Map<Relationships, List<Cell>> relationships;

  public RelatedCells(Map<Relationships, List<Cell>> relationships) {
    this.relationships = relationships;
  }

  public Map<Relationships, List<Cell>> getRelationships() {
    return relationships;
  }

  @Override
  public String toString() {
    return relationships.toString();
  }

  public List<Cell> getCellsOfRelationshipType(Relationships relationshipType) {
    return relationships.get(relationshipType);
  }
}
