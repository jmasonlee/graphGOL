package gameOfLife.output;

import org.apache.commons.lang.math.IntRange;
import org.apache.commons.lang.math.Range;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class CoordinateSquareDrawerTest {
  @Test
  public void testWillFillInSquaresWithSpacesToMatchWidestCoordinate() {
    List<Integer> testRange = IntStream.rangeClosed(-9, 99).boxed().collect(Collectors.toList());
    String[] coordinates = testRange.stream()
        .map(c -> CoordinateSquareDrawer.drawCoordinateSquare(c.toString()))
        .toArray(String[]::new);

    Approvals.verifyAll("COORDINATE:",coordinates);
  }
}