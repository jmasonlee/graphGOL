package gameOfLife.output;

import gameOfLife.TestUtils.CellCoverage;
import gameOfLife.cell.Cell;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.List;

public class BoardDrawerTest {
  @Test
  public void testWillAdjustHeaderRowColumnSizeToMatchLongestCoordinates() {
    //We account for a negative sign
    //The board does not shrink past 5
    //As the numbers increase in length, the square size expands.
    //Squares don't shrink less than 2

    Cell shortPositiveCoordinates1 = new Cell(2, 4);
    Cell shortPositiveCoordinates2 = new Cell(5, 9);

    Approvals.verify(storyboardDrawingHeaderRow(shortPositiveCoordinates1, shortPositiveCoordinates2));
  }

  private StringBuilder storyboardDrawingHeaderRow(Cell shortPositiveCoordinates1, Cell shortPositiveCoordinates2) {
    Coordinates coordinates = createCoordinates(shortPositiveCoordinates1, shortPositiveCoordinates2);

    List<String> headerRow = BoardDrawer.drawHeaderRow(coordinates);

    return createStoryBoard(coordinates, headerRow);
  }

  private Coordinates createCoordinates(Cell shortPositiveCoordinates1, Cell shortPositiveCoordinates2) {
    List<Cell> cells = CellCoverage.generateAllPossibleCellsBetweenTwoCells(shortPositiveCoordinates1, shortPositiveCoordinates2);
    BoardBounds boardBounds = new BoardBounds(cells);
    return new Coordinates(boardBounds);
  }

  private StringBuilder createStoryBoard(Coordinates coordinates, List<String> headerRow) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(coordinates);
    stringBuilder.append("\n==CREATE HEADER==\n");
    stringBuilder.append(headerRow.toString());
    return stringBuilder;
  }
}