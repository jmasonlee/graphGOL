package output;

import gameOfLife.cell.Cell;
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
        this::createBoardWithTwoCells, coordinateNumbers, coordinateNumbers, coordinateNumbers, coordinateNumbers);
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

  private String createBoardWithTwoCells(Integer cell1x, Integer cell1y, Integer cell2x, Integer cell2y) {
    return "\n"+BoardOutputter.createBoardOutput(Arrays.asList(new Cell(cell1x,cell1y), new Cell(cell2x, cell2y)));
  }
}
