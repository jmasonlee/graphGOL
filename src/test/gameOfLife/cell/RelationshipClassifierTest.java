package gameOfLife.cell;

import gameOfLife.TestUtils.CellCoverage;
import gameOfLife.output.BoardOutputter;
import org.approvaltests.Approvals;
import org.junit.Test;

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
    relationshipBoards.add(
        "\n" + BoardOutputter.createBoardOutput(relationship.get(Relationships.SELF)));
    relationshipBoards.add(
        "\n" + BoardOutputter.createBoardOutput(relationship.get(Relationships.NEIGHBOUR)));
    relationshipBoards.add(
        "\n" + BoardOutputter.createBoardOutput(relationship.get(Relationships.COPARENT)));
    relationshipBoards.add(
        "\n" + BoardOutputter.createBoardOutput(relationship.get(Relationships.DISCONNECTED)));
    Approvals.verifyAll("Boards", relationshipBoards);
  }

  public List<Cell> getListOfCellsToClassify(Cell cell) {
    int maxOneDimensionalDistance = 3;

    Cell lowerLeftCell =
        new Cell(
            relativeLowerLeftLocaltion(maxOneDimensionalDistance, cell.x),
            relativeLowerLeftLocaltion(maxOneDimensionalDistance, cell.y));
    Cell upperRightCell =
        new Cell(
            relativeUpperRightLocation(maxOneDimensionalDistance, cell.x),
            relativeUpperRightLocation(maxOneDimensionalDistance, cell.y));
    List<Cell> cellsToClassify =
        CellCoverage.generateAllPossibleCellsBetweenTwoCells(lowerLeftCell, upperRightCell);

    return cellsToClassify;
  }

  private int relativeUpperRightLocation(int maxOneDimensionalDistance, Integer centralLocation) {
    return centralLocation + maxOneDimensionalDistance + 1;
  }

  private int relativeLowerLeftLocaltion(int maxOneDimensionalDistance, Integer centralLocation) {
    return centralLocation - maxOneDimensionalDistance;
  }
}
