package gameOfLife.cell;

import gameOfLife.TestUtils.CellCoverage;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.List;

public class RelatedCellsTest {

  @Test
  public void testGetRelationshipsByType() {


    StringBuilder testOutput = fetchCategorizationOfAllRelationshipTypes(3, 3);

    Approvals.verify(testOutput);
  }

  private StringBuilder fetchCategorizationOfAllRelationshipTypes(Integer xValue, Integer yValue) {
    Cell centreCell = new Cell(0, 0);
    StringBuilder testOutput = new StringBuilder();
    Cell lowerLeftCell = new Cell(xValue*-1, yValue*-1);
    Cell upperRightCell = new Cell(xValue, yValue);

    List<Cell> fullCellRelationshipTestSet =
        CellCoverage.generateAllPossibleCellsBetweenTwoCells(lowerLeftCell, upperRightCell);

    RelatedCells relatedCells =
        RelationshipClassifier.classify(centreCell, fullCellRelationshipTestSet);

    for (Relationships relationship : Relationships.values()) {
      testOutput.append(relationship)
          .append(" => ")
          .append(relatedCells.getCellsOfRelationshipType(relationship))
          .append("\n");
    }
    return testOutput;
  }
}
