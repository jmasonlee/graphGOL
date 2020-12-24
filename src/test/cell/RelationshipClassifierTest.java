package cell;

import org.approvaltests.Approvals;
import org.junit.Test;
import output.BoardOutputter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RelationshipClassifierTest {
  private final TestHelper testHelper = new TestHelper();

  @Test
  public void testCellClassification() {
    List<Cell> cellsToClassify = testHelper.getListOfCellsToClassify(new Cell(0, 0));

    Map<RelationshipClassifier.Relationships, List<Cell>> relationship =
        RelationshipClassifier.classify(new Cell(0, 0), cellsToClassify);

    List<String> relationshipBoards = new ArrayList<>();
    relationshipBoards.add("\n"
        + BoardOutputter.createBoardOutput(relationship.get(RelationshipClassifier.Relationships.SELF)));
    relationshipBoards.add("\n"
        + BoardOutputter.createBoardOutput(relationship.get(RelationshipClassifier.Relationships.NEIGHBOUR)));
    relationshipBoards.add("\n"
        + BoardOutputter.createBoardOutput(relationship.get(RelationshipClassifier.Relationships.COPARENT)));
    relationshipBoards.add("\n"
        + BoardOutputter.createBoardOutput(relationship.get(RelationshipClassifier.Relationships.DISCONNECTED)));
    Approvals.verifyAll("Boards", relationshipBoards);
  }

  public List<Cell> getListOfCellsToClassify(Cell cell) {
    int maxOneDimensionalDistance = 3;
    List<Cell> cellsToClassify = new ArrayList<Cell>();

    for (int x = cell.x - maxOneDimensionalDistance; x <= cell.x + maxOneDimensionalDistance; x++) {
      for (int y = cell.y - maxOneDimensionalDistance;
           y <= cell.y + maxOneDimensionalDistance;
           y++) {
        cellsToClassify.add(new Cell(x, y));
      }
    }

    return cellsToClassify;
  }
}
