package output;

import cell.Cell;
import org.approvaltests.Approvals;
import org.approvaltests.combinations.CombinationApprovals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class BoardOutputterTest {
  @Test
  public void testBoardIsGeneratedWithCellsInCorrectLocation() {
    Integer[] coordinateNumbers = new Integer[]{-3,-2, -1, 0, 1, 2, 3};

    CombinationApprovals.verifyAllCombinations(
        this::createBoardWithTwoCells, coordinateNumbers, coordinateNumbers);
  }

  @Test
  public void testEmptyBoard() {
    Approvals.verify(BoardOutputter.createBoardOutput(new ArrayList<>()));
  }

  private String createBoardWithTwoCells(Integer x, Integer y) {
    return "\n"+BoardOutputter.createBoardOutput(Arrays.asList(new Cell(-3,3), new Cell(x, y)));
  }
}
