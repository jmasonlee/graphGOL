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
    stringBuilder.append("Input:");
    stringBuilder.append("\n\tLowest X:");
    stringBuilder.append(shortPositiveCoordinates1.x);
    stringBuilder.append("\n\tHighest X:");
    stringBuilder.append(shortPositiveCoordinates2.x);
    stringBuilder.append("\n\tLength of longest Coordinate:");
    stringBuilder.append(coordinates.widestCoordinateString);
    stringBuilder.append("\nOutput:");
    stringBuilder.append("\n\tHeader row:");
    stringBuilder.append(headerRow.toString());

    Approvals.verify(stringBuilder);
  }
}