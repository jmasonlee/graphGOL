package cell;

import org.approvaltests.Approvals;
import org.junit.Test;
import output.BoardOutputter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RelationshipClassifierTest {
  @Test
  public void testCellClassification() {
    List<Cell> cellsToClassify = getListOfCellsToClassify(new Cell(0, 0));

    Map<RelationshipClassifier.Relationships, List<Cell>> relationship =
        RelationshipClassifier.classify(new Cell(0, 0), cellsToClassify);

    List<String> relationshipBoards = new ArrayList<>();
    relationshipBoards.add("\n"
        + BoardOutputter.createBoardOutput(relationship.get(RelationshipClassifier.Relationships.SELF)));
    relationshipBoards.add("\n"
        + BoardOutputter.createBoardOutput(relationship.get(RelationshipClassifier.Relationships.NEIGHBOUR)));
    Approvals.verifyAll("Boards", relationshipBoards);
  }

  private List<Cell> getListOfCellsToClassify(Cell cell) {
    List<Cell> cellsToClassify = new ArrayList<>();

    for (int x = cell.x - 3; x <= cell.x + 3; x++) {
      for (int y = cell.y - 3; y <= cell.y + 3; y++) {
        cellsToClassify.add(new Cell(x, y));
      }
    }

    return cellsToClassify;
  }
}
