package gameOfLife.output;

import gameOfLife.TestUtils.CellCoverage;
import gameOfLife.cell.Cell;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.List;

public class BoardDrawerTest {
  @Test
  public void testWillDrawHeaderRow() {

    Cell shortPositiveCoordinates1 = new Cell(2, 4);
    Cell shortPositiveCoordinates2 = new Cell(5, 9);

    List<Cell> cells = CellCoverage.generateAllPossibleCellsBetweenTwoCells(shortPositiveCoordinates1, shortPositiveCoordinates2);
    BoardBounds boardBounds = new BoardBounds(cells);
    Coordinates coordinates = new Coordinates(boardBounds);

    List<String> headerRow = BoardDrawer.drawHeaderRow(coordinates);

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(coordinates);
    stringBuilder.append("\n==CREATE HEADER==\n");
    stringBuilder.append(headerRow.toString());

    Approvals.verify(stringBuilder);
  }
}