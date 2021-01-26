package gameOfLife.output;

import gameOfLife.TestUtils.CellCoverage;
import gameOfLife.cell.Cell;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BoardBoundsTest {

  @Test
  public void testCreateBoundariesForBoard() {
    Cell lowerLeftCell = new Cell(-23, 4);
    Cell upperRightCell = new Cell(-11, 6);
    List<Cell> cells = CellCoverage.generateAllPossibleCellsBetweenTwoCells(lowerLeftCell, upperRightCell);

    BoardBounds boardBounds = new BoardBounds(cells);
    Approvals.verify(boardBounds);
  }
}