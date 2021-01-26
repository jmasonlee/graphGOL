package gameOfLife.output;

import gameOfLife.TestUtils.CellCoverage;
import gameOfLife.cell.Cell;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CoordinatesTest {

  @Test
  public void testWillCreateCoordinateObject() {
//    List<String> xCoordinates =
//        IntStream.rangeClosed(-106, -95).boxed().map(i -> i.toString()).collect(Collectors.toList());
//    List<String> yCoordinates =
//        IntStream.rangeClosed(12, 22).boxed().map(i -> i.toString()).collect(Collectors.toList());

    List<Cell> cells = CellCoverage.generateAllPossibleCellsBetweenTwoCells(new Cell(-106, 12), new Cell(-95, 22));

    BoardOutputter.BoardBounds boardBounds = new BoardOutputter.BoardBounds(cells);

    Approvals.verify(new Coordinates(boardBounds).toString());
  }
}
