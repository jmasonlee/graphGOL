package cell;

import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RelationshipClassifierTest {
  @Test
  public void testCellIsClassification() {
    List<Cell> cellsToClassify = Arrays.asList(new Cell[]{new Cell(1,2)});

    Map<RelationshipClassifier.Relationships, List<Cell>> relationship =
        RelationshipClassifier.classify(new Cell(1, 2), cellsToClassify);

    Approvals.verify(relationship);
  }
}
