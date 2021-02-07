package gameOfLife.output;

import gameOfLife.TestUtils.CellCoverage;
import gameOfLife.cell.Cell;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.List;

public class BoardDrawerTest {
  @Test
  public void testWillAdjustHeaderRowColumnSizeToMatchLongestCoordinates() {
    //We account for sign
    int[] signModifier = new int[]{-1, 1};
    //As the numbers increase in length, the square size expands.
      //Square size starts at 3, regardless of coordinate length
      //Square starts to grow after coordinate length > 3
    int[] coordLengthModifiers = new int[]{1, 10, 100, 1000};
    //Square size grows regardless of if longest coordinate is an x or y value
    int[] axisModifier = new int[]{0, 1};

    Cell lowerLeft = new Cell(2, 4);
    Cell upperRight = new Cell(5, 9);

    Approvals.verify(storyboardDrawingHeaderRow(lowerLeft, upperRight));
  }

  //The board does not shrink past 5
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