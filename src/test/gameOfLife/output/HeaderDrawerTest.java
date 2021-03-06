package gameOfLife.output;

import gameOfLife.TestUtils.CellCoverage;
import gameOfLife.cell.Cell;
import org.approvaltests.combinations.CombinationApprovals;
import org.junit.Before;
import org.junit.Test;
import org.lambda.functions.Function2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeaderDrawerTest extends CoordinateSquareDrawer {

  private Map<String, Function2<Integer, Cell, Cell>> axisModifiers;
  private Map<String, Integer> coordLengthModifiers;

  @Before
  public void setUp() {
    axisModifiers = new HashMap<>();
    axisModifiers.put("applied to X", this::applyModifiersToXCoord);
    axisModifiers.put("applied to Y", this::applyModifiersToYCoord);

    coordLengthModifiers = new HashMap<>();
    coordLengthModifiers.put("one digit", 0);
    coordLengthModifiers.put("two digits", 10);
    coordLengthModifiers.put("three digits", 100);
    coordLengthModifiers.put("four digits", 1000);
  }

  @Test
  public void testWillAdjustHeaderRowColumnSizeToMatchLongestCoordinates() {
    CombinationApprovals.verifyAllCombinations(this::storyboardDrawingHeaderRow,
      coordLengthModifiers.keySet().toArray(new String[0]),
      axisModifiers.keySet().toArray(new String[0]));
  }

  private StringBuilder storyboardDrawingHeaderRow(String coordLengthModifierKey, String axisModifier) {

    Coordinates coordinates = getCoordinates(coordLengthModifierKey, axisModifier);

    List<String> headerRow = HeaderDrawer.drawHeaderRow(coordinates);

    return createStoryBoard(coordinates, headerRow);
  }

  private Coordinates getCoordinates(String coordLengthModifierKey, String axisModifier) {
    Cell[] baseCoordinates = new Cell[]{new Cell(2, 4), new Cell(5, 9)};

    int coordLengthModifier = coordLengthModifiers.get(coordLengthModifierKey);

    for (Cell baseCoordinate : baseCoordinates) {
      axisModifiers.get(axisModifier).call(coordLengthModifier, baseCoordinate);
    }
    return CellCoverage.createCoordinates(baseCoordinates[0], baseCoordinates[1]);
  }

  private Cell applyModifiersToXCoord(int coordLengthModifier, Cell cell) {
    cell.x = applyModifiers(coordLengthModifier, cell.x);
    return cell;
  }

  private Cell applyModifiersToYCoord(int coordLengthModifier, Cell cell) {
    cell.y = applyModifiers(coordLengthModifier, cell.y);
    return cell;
  }

  private int applyModifiers(int coordLengthModifier, int coord) {
    return (coord + coordLengthModifier);
  }

  private StringBuilder createStoryBoard(Coordinates coordinates, List<String> headerRow) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(coordinates);
    stringBuilder.append("\n==CREATE HEADER==\n");
    stringBuilder.append(headerRow.toString());
    return stringBuilder;
  }
}