package gameOfLife.cell;

import gameOfLife.TestUtils.CellCoverage;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.List;

public class RelatedCellsTest {

  @Test
  public void testGetRelationshipsByType() {
    Cell centreCell = new Cell(0, 0);

    Cell lowerLeftCell = new Cell(-3, -3);
    Cell upperRightCell = new Cell(3, 3);

    StringBuilder testOutput = new StringBuilder();

    List<Cell> fullCellRelationshipTestSet =
        CellCoverage.generateAllPossibleCellsBetweenTwoCells(lowerLeftCell, upperRightCell);

    RelatedCells relatedCells =
        RelationshipClassifier.classify(centreCell, fullCellRelationshipTestSet);

    for (Relationships relationship : Relationships.values()) {
      testOutput.append(
          relationship + " => " + relatedCells.getCellsOfRelationshipType(relationship) + "\n");
    }

    Approvals.verify(testOutput);
  }
}
