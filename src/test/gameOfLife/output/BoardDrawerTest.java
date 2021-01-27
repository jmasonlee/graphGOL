package gameOfLife.output;

import gameOfLife.TestUtils.CellCoverage;
import gameOfLife.cell.Cell;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BoardDrawerTest {
  @Test
  public void testWillDrawBoard() {
    List<Cell> cells = CellCoverage.generateAllPossibleCellsBetweenTwoCells(new Cell(2, 4), new Cell(5,9));
    BoardBounds boardBounds = new BoardBounds(cells);
    Approvals.verify(BoardDrawer.emptyBoardOfSize(boardBounds));
  }
}