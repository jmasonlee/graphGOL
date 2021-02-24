package gameOfLife.output;

import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CoordinateSquareDrawerTest {
  @Test
  public void testWillFillInSquaresWithSpacesToMatchWidestCoordinate() {
    List<Integer> testRange = IntStream.rangeClosed(-9, 99).boxed().collect(Collectors.toList());
    String[] coordinates = testRange.stream()
        .map(c -> CoordinateSquareDrawer.drawCoordinateSquare(c.toString()))
        .toArray(String[]::new);

    Approvals.verifyAll("COORDINATE:", coordinates);
  }
}