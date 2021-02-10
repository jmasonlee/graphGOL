package gameOfLife.output;

import gameOfLife.TestUtils.CellCoverage;
import gameOfLife.cell.Cell;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoardDrawerTest {
  @Test
  public void testWillAdjustHeaderRowColumnSizeToMatchLongestCoordinates() {
    //We account for sign
    Map<String, Integer> signModifiers = Stream.of(new Object[][]{
        {"positive", 1}, {"negative", -1}
    }).collect(Collectors.toMap(sm -> (String)sm[0], sm -> (Integer)sm[1]));
    //As the numbers increase in length, the square size expands.
      //Square size starts at 3, regardless of coordinate length
      //Square starts to grow after coordinate length > 3
    Map<String, Integer> coordLengthModifiers = Stream.of(new Object[][]{
        {"one digit", 0},
        {"two digits", 10},
        {"three digits", 100},
        {"four digits", 1000}
    }).collect(Collectors.toMap(
         d -> (String) d[0],
         d -> (Integer) d[1]));

    //Square size grows regardless of if longest coordinate is an x or y value
    Map<String, Integer> axisModifier = Stream.of(new Object[][]{
        {"Longer X", 0 },
        {"Longer Y", 1 }
    }).collect(Collectors.toMap(
        d -> (String) d[0],
        d -> (Integer) d[1]));

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