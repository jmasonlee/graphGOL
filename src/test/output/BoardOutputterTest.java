package output;

import cell.Cell;
import org.approvaltests.Approvals;
import org.approvaltests.combinations.CombinationApprovals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class BoardOutputterTest {
  Integer[] coordinateNumbers = new Integer[]{-3,-2, -1, 0, 1, 2, 3};

  @Test
  public void testBoardIsGeneratedWithCellsInCorrectLocation() {
    CombinationApprovals.verifyAllCombinations(
        this::createBoardWithTwoCells, coordinateNumbers, coordinateNumbers);
  }

  @Test
  public void testEmptyBoard() {
    Approvals.verify(BoardOutputter.createBoardOutput(new ArrayList<>()));
  }

  @Test
  public void testBoardWithOneCell() {
    CombinationApprovals.verifyAllCombinations(this::createBoardWithOneCell, coordinateNumbers, coordinateNumbers);
  }

  private String createBoardWithOneCell(Integer x, Integer y) {
    return "\n"+BoardOutputter.createBoardOutput(Arrays.asList(new Cell(x, y)));
  }

  private String createBoardWithTwoCells(Integer x, Integer y) {
    return "\n"+BoardOutputter.createBoardOutput(Arrays.asList(new Cell(-3,3), new Cell(x, y)));
  }
}
