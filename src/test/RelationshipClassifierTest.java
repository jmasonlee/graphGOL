import cell.Cell;
import cell.RelationshipClassifier;
import org.junit.Test;

import static org.junit.Assert.*;

public class RelationshipClassifierTest {
  @Test
  public void testCellIsClassifiedAsSelf() {
    RelationshipClassifier.Relationships relationship =
        RelationshipClassifier.classify(new Cell(1, 2), new Cell(1, 2));
    assertEquals(RelationshipClassifier.Relationships.SELF, relationship);
  }
}
