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
    Integer[] coordinateNumbers = new Integer[]{-3,-2, -1, 0, 1, 2, 3};

    CombinationApprovals.verifyAllCombinations(
        this::createBoardWithCellInLocation, coordinateNumbers, coordinateNumbers);
  }

  private String createBoardWithCellInLocation(Integer x, Integer y) {
    return "\n"+BoardOutputter.createBoardOutput(Arrays.asList(new Cell(0,0), new Cell(x, y)));
  }
}
