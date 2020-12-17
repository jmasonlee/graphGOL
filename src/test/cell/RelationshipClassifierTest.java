package cell;

import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.Map;

public class RelationshipClassifierTest {
  @Test
  public void testCellIsClassifiedAsSelf() {
    Map<RelationshipClassifier.Relationships, Cell> relationship =
        RelationshipClassifier.classify(new Cell(1, 2), new Cell(1, 2));

    Approvals.verify(relationship);
  }
}
