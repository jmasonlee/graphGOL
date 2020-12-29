package gameOfLife.cell;

import gameOfLife.TestUtils.CellCoverage;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.List;

public class RelatedCellsTest {

  @Test
  public void testGetRelationshipsByType() {
    StringBuilder testOutput = new StringBuilder();

    Cell lowerLeftCell = new Cell(-3, -3);
    Cell upperRightCell = new Cell(3, 3);

    List<Cell> fullCellRelationshipTestSet =
        CellCoverage.generateAllPossibleCellsBetweenTwoCells(lowerLeftCell, upperRightCell);

    Cell testSetCentre = new Cell(0, 0);
    RelatedCells relatedCells =
        RelationshipClassifier.classify(testSetCentre, fullCellRelationshipTestSet);

    for (Relationships relationship : Relationships.values()) {
      testOutput.append(
          relationship + " => " + relatedCells.getCellsOfRelationshipType(relationship) + "\n");
    }

    Approvals.verify(testOutput);
  }
}
