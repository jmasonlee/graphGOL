package cell;

import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class RelationshipClassifierTest {
  @Test
  public void testCellClassification() {
    List<Cell> cellsToClassify = Arrays.asList(new Cell[]{new Cell(1,2)});

    Map<RelationshipClassifier.Relationships, List<Cell>> relationship =
        RelationshipClassifier.classify(new Cell(1, 2), cellsToClassify);

    Approvals.verify(relationship);
  }

  @Test
  public void testNeighbourRelationship() {
    List<Cell> cellsToClassify = Arrays.asList(new Cell[]{new Cell(1,1)});

    Map<RelationshipClassifier.Relationships, List<Cell>> relationship =
        RelationshipClassifier.classify(new Cell(1, 2), cellsToClassify);

    assertEquals(new Cell(1,1), relationship.get(RelationshipClassifier.Relationships.NEIGHBOUR).get(0));
  }
}
