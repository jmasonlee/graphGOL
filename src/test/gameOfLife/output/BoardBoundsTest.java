package gameOfLife.output;

import gameOfLife.TestUtils.CellCoverage;
import gameOfLife.cell.Cell;
import org.apache.commons.lang.math.IntRange;
import org.approvaltests.Approvals;
import org.approvaltests.combinations.CombinationApprovals;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BoardBoundsTest {

  @Test
  public void testCreateBoundariesForBoard() {
    IntRange lessThanDefaultApart = new IntRange(2,6);
    IntRange moreThanDefaultApart = new IntRange(-23, -11);
    IntRange defaultApart = new IntRange(-1, 3);

    IntRange[] testRanges = new IntRange[]{lessThanDefaultApart, moreThanDefaultApart, defaultApart};

    CombinationApprovals.verifyAllCombinations(this::createBoardBoundsForTest, testRanges, testRanges);
  }

  private BoardBounds createBoardBoundsForTest(IntRange xvalues, IntRange yvalues) {
    Cell lowerLeftCell = new Cell(xvalues.getMinimumInteger(), yvalues.getMinimumInteger());
    Cell upperRightCell = new Cell(xvalues.getMaximumInteger(), yvalues.getMaximumInteger());
    List<Cell> cells = CellCoverage.generateAllPossibleCellsBetweenTwoCells(lowerLeftCell, upperRightCell);

    return new BoardBounds(cells);
  }
}