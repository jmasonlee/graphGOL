import org.junit.Test;

import static org.junit.Assert.*;

public class CellRelationshipClassifierTest {
  @Test
  public void testCellIsClassifiedAsSelf() {
    CellRelationshipClassifier.Relationships relationship =
        CellRelationshipClassifier.classify(new Cell(1, 2), new Cell(1, 2));
    assertEquals(CellRelationshipClassifier.Relationships.SELF, relationship);
  }
}
