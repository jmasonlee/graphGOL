import cell.Cell;
import cell.RelationshipClassifier;
import org.approvaltests.Approvals;
import org.approvaltests.strings.Printable;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class RelationshipClassifierTest {
  @Test
  public void testCellIsClassifiedAsSelf() {
    Map<RelationshipClassifier.Relationships, Cell> relationship=
        RelationshipClassifier.classify(new Cell(1, 2), new Cell(1, 2));

    Approvals.verify(relationship);
  }

}
