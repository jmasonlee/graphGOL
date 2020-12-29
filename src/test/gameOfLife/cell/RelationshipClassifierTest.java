package gameOfLife.cell;

import gameOfLife.TestUtils.CellCoverage;
import org.approvaltests.Approvals;
import org.junit.Test;
import gameOfLife.output.BoardOutputter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RelationshipClassifierTest {

  @Test
  public void testCellClassification() {
    List<Cell> cellsToClassify = getListOfCellsToClassify(new Cell(0, 0));

    Map<Relationships, List<Cell>> relationship =
      RelationshipClassifier.classify(new Cell(0, 0), cellsToClassify).getRelationships();

    List<String> relationshipBoards = new ArrayList<>();
    relationshipBoards.add("\n"
        + BoardOutputter.createBoardOutput(relationship.get(Relationships.SELF)));
    relationshipBoards.add("\n"
        + BoardOutputter.createBoardOutput(relationship.get(Relationships.NEIGHBOUR)));
    relationshipBoards.add("\n"
        + BoardOutputter.createBoardOutput(relationship.get(Relationships.COPARENT)));
    relationshipBoards.add("\n"
        + BoardOutputter.createBoardOutput(relationship.get(Relationships.DISCONNECTED)));
    Approvals.verifyAll("Boards", relationshipBoards);
  }

  public List<Cell> getListOfCellsToClassify(Cell cell) {
    int maxOneDimensionalDistance = 3;

    Cell lowerLeftCell = new Cell(cell.x - maxOneDimensionalDistance, cell.y - maxOneDimensionalDistance);
    Cell upperRightCell = new Cell(cell.x  + maxOneDimensionalDistance + 1, cell.y + maxOneDimensionalDistance + 1);
    List<Cell> cellsToClassify = CellCoverage.generateAllPossibleCellsBetweenTwoCells(lowerLeftCell, upperRightCell);

    return cellsToClassify;
  }
}
