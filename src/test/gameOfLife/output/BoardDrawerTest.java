package gameOfLife.output;

import gameOfLife.TestUtils.CellCoverage;
import gameOfLife.cell.Cell;
import org.approvaltests.combinations.CombinationApprovals;
import org.junit.Before;
import org.junit.Test;
import org.lambda.functions.Function3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardDrawerTest {

  private Map<String, Function3<Integer, Integer, Cell, Cell>> axisModifiers;
  private Map<String, Integer> coordLengthModifiers;
  private Map<String, Integer> signModifiers;

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

    signModifiers = new HashMap<>();
    signModifiers.put("positive", 1);
    //signModifiers.put("negative", -1);
  }

  @Test
  public void testWillAdjustHeaderRowColumnSizeToMatchLongestCoordinates() {
    CombinationApprovals.verifyAllCombinations(this::storyboardDrawingHeaderRow,
      signModifiers.keySet().toArray(new String[0]),
      coordLengthModifiers.keySet().toArray(new String[0]),
      axisModifiers.keySet().toArray(new String[0]));
  }

  private StringBuilder storyboardDrawingHeaderRow(String signModifierKey, String coordLengthModifierKey, String axisModifier) {

    Coordinates coordinates = getCoordinates(signModifierKey, coordLengthModifierKey, axisModifier);

    List<String> headerRow = BoardDrawer.drawHeaderRow(coordinates);

    return createStoryBoard(coordinates, headerRow);
  }

  private Coordinates getCoordinates(String signModifierKey, String coordLengthModifierKey, String axisModifier) {
    Cell[] baseCoordinates = new Cell[]{new Cell(2, 4), new Cell(5, 9)};

    int signModifier = signModifiers.get(signModifierKey);
    int coordLengthModifier = coordLengthModifiers.get(coordLengthModifierKey);

    for (Cell baseCoordinate : baseCoordinates) {
      axisModifiers.get(axisModifier).call(signModifier, coordLengthModifier, baseCoordinate);
    }
    return createCoordinates(baseCoordinates[0], baseCoordinates[1]);
  }

  private Cell applyModifiersToXCoord(int signModifier, int coordLengthModifier, Cell cell) {
    cell.x = applyModifiers(signModifier, coordLengthModifier, cell.x);
    return cell;
  }

  private Cell applyModifiersToYCoord(int signModifier, int coordLengthModifier, Cell cell) {
    cell.y = applyModifiers(signModifier, coordLengthModifier, cell.y);
    return cell;
  }

  private int applyModifiers(int signModifier, int coordLengthModifier, int coord) {
    return (coord + coordLengthModifier);
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