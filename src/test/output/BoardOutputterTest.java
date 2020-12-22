package output;

import cell.Cell;
import org.approvaltests.combinations.CombinationApprovals;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BoardOutputterTest {
  @Test
  public void testBoardIsGeneratedWithCellsInCorrectLocation() {
    Integer[] coordinateNumbers = new Integer[]{0, 1, 2, 3, 4};

    CombinationApprovals.verifyAllCombinations(
        this::createBoardWithCellInLocation, coordinateNumbers, coordinateNumbers);
  }

  @Test
  public void testBoardIsGeneratedWithCellsInCorrectLocationIfCellIsInNegativeSpace() {
    String expectedBoardOutput = "X    \n     \n     \n     \n     ";
    assertEquals(expectedBoardOutput, BoardOutputter.createBoardOutput(Arrays.asList(new Cell(-2, -1))));
  }

  private String createBoardWithCellInLocation(Integer x, Integer y) {
    return "\n"+BoardOutputter.createBoardOutput(Arrays.asList(new Cell(0,0), new Cell(x, y)));
  }
}
