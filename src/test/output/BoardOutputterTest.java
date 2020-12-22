package output;

import cell.Cell;
import org.approvaltests.combinations.CombinationApprovals;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class BoardOutputterTest {
  @Test
  public void testBoardIsGeneratedWithCellsInCorrectLocation() {
    Integer[] coordinateNumbers = new Integer[]{0, 1, 2, 3, 4};

    CombinationApprovals.verifyAllCombinations(
        this::createBoardWithCellInLocation, coordinateNumbers, coordinateNumbers);
  }

  private String createBoardWithCellInLocation(Integer x, Integer y) {
    return "\n"+BoardOutputter.createBoardOutput(Arrays.asList(new Cell(x, y)));
  }
}
