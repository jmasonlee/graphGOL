package gameOfLife.output;

import gameOfLife.TestUtils.CellCoverage;
import gameOfLife.cell.Cell;
import org.approvaltests.Approvals;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoardDrawerTest {

  private Map<String, Integer> axisModifiers;
  private Map<String, Integer> coordLengthModifiers;
  private Map<String, Integer> signModifiers;

  @Before
  public void setUp() {
    axisModifiers = Stream.of(new Object[][]{
        {"applied to X", 0},
        {"applied to Y", 1}
    }).collect(Collectors.toMap(
        d -> (String) d[0],
        d -> (Integer) d[1]));

    coordLengthModifiers = Stream.of(new Object[][]{
        {"one digit", 0},
        {"two digits", 10},
        {"three digits", 100},
        {"four digits", 1000}
    }).collect(Collectors.toMap(
        d -> (String) d[0],
        d -> (Integer) d[1]));

    signModifiers = Stream.of(new Object[][]{
        {"positive", 1}, {"negative", -1}
    }).collect(Collectors.toMap(sm -> (String) sm[0], sm -> (Integer) sm[1]));
  }

  @Test
  public void testWillAdjustHeaderRowColumnSizeToMatchLongestCoordinates() {
    Cell lowerLeft = new Cell(2, 4);
    Cell upperRight = new Cell(5, 9);

    Approvals.verify(storyboardDrawingHeaderRow(lowerLeft, upperRight));
  }

  private StringBuilder storyboardDrawingHeaderRow(Cell lowerLeft, Cell upperRight) {
    Coordinates coordinates = createCoordinates(lowerLeft, upperRight);

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