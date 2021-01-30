package gameOfLife.output;

import gameOfLife.TestUtils.CellCoverage;
import gameOfLife.cell.Cell;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.List;

public class BoardDrawerTest {
  @Test
  public void testWillDrawHeaderRow() {
    List<Cell> cells = CellCoverage.generateAllPossibleCellsBetweenTwoCells(new Cell(2, 4), new Cell(5,9));
    BoardBounds boardBounds = new BoardBounds(cells);
    Coordinates coordinates = new Coordinates(boardBounds);
    List<String> headerRow = BoardComponentDrawer.drawHeaderRow(coordinates);
    Approvals.verify(headerRow);
  }
}